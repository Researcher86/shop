package com.tanat.shop.util;

import com.tanat.shop.model.*;
import com.tanat.shop.service.CartService;
import com.tanat.shop.service.CategoryService;
import com.tanat.shop.service.ClientService;
import com.tanat.shop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Компонент для иницыализации БД
 * Created by Tanat on 16.11.2015.
 */
@Component
public class InitDatabase {
    private final CategoryService categoryService;

    private final GoodsService goodsService;

    private final ClientService clientService;

    private final CartService cartService;

    @Autowired
    public InitDatabase(CategoryService categoryService, GoodsService goodsService, ClientService clientService, CartService cartService) {
        this.categoryService = categoryService;
        this.goodsService = goodsService;
        this.clientService = clientService;
        this.cartService = cartService;
    }


    @PostConstruct
    public void init() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        Client client = new Client("Test", "Test", "Test", "test@test.com", "test");
        clientService.save(client);

        loadGoodsFromXml();

        addCommentForGoods(client);
    }

    private void addCommentForGoods(Client client) {
        Comment comment1 = new Comment("Достоинства:  Цена и качество соответствует товару. Нареканий никаких нет, работает машинка на ура. Рекомендую.\n" +
                                       "Недостатки: Не выявил. Не люблю китайскую технику, но тут рискнул и не прогадал.\n" +
                                       "Товар приобретался в компании Юлмарт.Заказ пришел очень быстро, не разобрался до конца с системой такса, какая скидка? " +
                                       "пол таксы? Не совсем понятно, единственное что понятно надо заплатить за эту скидку, а так опять же все устроило. " +
                                       "Товар новый, придраться не к чему.", client);

        Comment comment2 = new Comment("Достоинства:  Тонкий, легкий. Клавиатура приятная. Пластик корпуса хороший.\n" +
                                        "Недостатки: " +
                                        "1. Кнопка включения на левом торце снизу. да как можно было такое придумать? чтобы просто ноут подвинуть приходится следить чтоб случайно не нажать.\n" +
                                        "2. Гнездо зарядки ровно по центру. дико неудобно как по мне.\n" +
                                        "3. Углы обзора по вертикали ужасные. Яркость экрана низкая.\n" +
                                        "4. Расположение кнопок на дополнительной цифровой клавиатуре вызывает удивление. лучше бы вообще не делали ее.\n" +
                                        "5. Touchpad маленький, жесты понимает плоховато. \n" +
                                        "6. Дисковод. Кнопка большая, нажимается при каждом удобном случае. Да и вообще непонятно для чего дисковод нужен в настоящее время. Атавизм.", client);
        comment1.setActive(true);
        comment2.setActive(true);

        goodsService.addCommentForGoods(comment1, 1L);
        goodsService.addCommentForGoods(comment2, 1L);
    }

    private void loadGoodsFromXml() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false); // не делать проверку валидации
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(ResourceUtils.getFile("classpath:goods/goods.xml"));

        NodeList categoryList = doc.getDocumentElement().getChildNodes().item(1).getChildNodes();

        for (int i = 0; i < categoryList.getLength(); i++) {

            if (categoryList.item(i) instanceof Element) {
                Element elementCategory = (Element) categoryList.item(i);

                Category category = new Category(elementCategory.getAttributes().getNamedItem("title").getNodeValue());
                categoryService.save(category);

                NodeList goodsList = elementCategory.getChildNodes();
                for (int j = 0; j < goodsList.getLength(); j++) {

                    if (goodsList.item(j) instanceof Element) {
                        Element elementGoods = (Element) goodsList.item(j);

                        String title = elementGoods.getAttributes().getNamedItem("title").getNodeValue();
                        int price = Integer.parseInt(elementGoods.getAttributes().getNamedItem("price").getNodeValue());
                        String description = elementGoods.getAttributes().getNamedItem("description").getNodeValue();
                        Image image = Image.load(elementGoods.getAttributes().getNamedItem("image").getNodeValue());

                        Goods goods = new Goods(title,
                                price,
                                description,
                                image);

                        goods.setCategory(category);

                        goodsService.save(goods);
                    }
                }
            }
        }
    }
}

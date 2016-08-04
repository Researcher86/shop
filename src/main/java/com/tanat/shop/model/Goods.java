package com.tanat.shop.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "goods")
public class Goods {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Укажите наименование товара")
    private String name;

    @NotNull(message = "Некорректно указана цена товара")
    @Min(value = 1, message = "Некорректно указана цена товара")
    private Integer price;

    @NotBlank(message = "Укажите краткое описание товара")
    @Length(max = 10000)
    private String description;

    @NotNull(message = "Некорректно указана категоря товара")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "goods")
    private List<Comment> comments = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;

    public Goods() {
    }

    public Goods(String name, int price, String description, Image image) {
        this();
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void addComments(Comment comment) {
        comment.setGoods(this);
        comments.add(comment);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public static Goods createSimple() {
        return new Goods("Ручка", 5, "Обычная ручка", null);
    }
}

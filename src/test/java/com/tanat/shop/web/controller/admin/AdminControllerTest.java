package com.tanat.shop.web.controller.admin;

import com.tanat.shop.exception.AppException;
import com.tanat.shop.model.Category;
import com.tanat.shop.service.CategoryService;
import com.tanat.shop.web.controller.AbstractControllerTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Тестируем контроллер админки
 * Created by Tanat on 10.12.2015.
 */
public class AdminControllerTest extends AbstractControllerTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void showCategories() throws Exception {
        mockMvc.perform(get("/admin/categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("admin/template"))
                .andExpect(model().attributeExists("categories"));
    }

    @Test
    public void categoryForm() throws Exception {
        mockMvc.perform(get("/admin/categories/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("admin/template"))
                .andExpect(model().attributeExists("category"))
                .andExpect(model().attribute("content", "category.jsp"));
    }

    @Test
    public void categorySave() throws Exception {
        mockMvc.perform(post("/admin/categories/1")
                .param("name", "Test")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/categories/1"));

        Category category = categoryService.getById(1L);

        assertEquals("Test", category.getName());
        assertEquals(12, category.getGoodsList().size());
    }

    @Test(expected = AppException.class)
    public void deleteCategoryEmptyGoodsList() throws Exception {
        Category category = new Category("Test");
        categoryService.save(category);

        mockMvc.perform(delete("/admin/categories/" + category.getId())).andDo(print()).andExpect(status().isOk());

        categoryService.getById(category.getId());
    }

    @Test
    public void deleteCategoryNoEmptyGoodsList() throws Exception {
        mockMvc.perform(delete("/admin/categories/1")).andDo(print()).andExpect(status().isBadRequest());
    }
}
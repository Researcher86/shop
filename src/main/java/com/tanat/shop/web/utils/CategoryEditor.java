package com.tanat.shop.web.utils;

import com.tanat.shop.model.Category;
import com.tanat.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

/**
 * Created by Tanat on 25.11.2015.
 */
@Component
public class CategoryEditor extends PropertyEditorSupport {

    @Autowired
    private CategoryService categoryService;

    @Override
    public void setAsText(String text) {
        Category category = categoryService.getById(Long.valueOf(text));
        super.setValue(category);
    }
}

package com.tanat.shop.dao;

import com.tanat.shop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Tanat on 12.11.2015.
 */
public interface CategoryDao extends JpaRepository<Category,Long> {

}

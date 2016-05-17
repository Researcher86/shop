package com.tanat.shop.dao;

import com.tanat.shop.model.Category;
import com.tanat.shop.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface GoodsDao extends JpaRepository<Goods, Long> {

    List<Goods> findByNameLike(String name);

    List<Goods> findByCategory(Category category);
}

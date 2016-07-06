package com.tanat.shop.repository;

import com.tanat.shop.model.Category;
import com.tanat.shop.model.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Long> {

    List<Goods> findByNameLike(String name);

    List<Goods> findByCategory(Category category);

    @Query("select g from Goods g where g.category.id = :categoryId")
    Page<Goods> findGoodsByCategory(@Param("categoryId") Long categoryId, Pageable pageable);
}

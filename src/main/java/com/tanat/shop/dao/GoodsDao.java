package com.tanat.shop.dao;

import com.tanat.shop.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Tanat on 12.11.2015.
 */
public interface GoodsDao extends JpaRepository<Goods, Long> {

    List<Goods> findByNameLike(String name);
}

package com.tanat.shop.dao;

import com.tanat.shop.model.Client;
import com.tanat.shop.model.Comment;
import com.tanat.shop.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Tanat on 12.11.2015.
 */
public interface CommentDao extends JpaRepository<Comment, Long> {
    List<Comment> findByGoods(Goods goods);

    List<Comment> findByClient(Client client);
}

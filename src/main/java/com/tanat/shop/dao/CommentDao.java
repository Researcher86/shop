package com.tanat.shop.dao;

import com.tanat.shop.model.Client;
import com.tanat.shop.model.Comment;
import com.tanat.shop.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao extends JpaRepository<Comment, Long> {
    List<Comment> findByGoods(Goods goods);

    List<Comment> findByClient(Client client);
}

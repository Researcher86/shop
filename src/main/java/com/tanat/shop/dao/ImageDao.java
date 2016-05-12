package com.tanat.shop.dao;

import com.tanat.shop.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageDao extends JpaRepository<Image, Long> {
}

package com.tanat.shop.dao;

import com.tanat.shop.model.Client;
import com.tanat.shop.model.Comment;
import com.tanat.shop.model.Goods;
import com.tanat.shop.model.Image;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.*;

/**
 * Тестируем слой DAO изображений
 * Created by Tanat on 12.11.2015.
 */
public class ImageDaoTest extends AbstractDaoTest {

    @Autowired
    private ImageDao imageDao;

    @Test
    public void save() throws Exception {
        File file = null;
        byte[] fileArray = new byte[0];

        try {
            file = ResourceUtils.getFile("classpath:bumaga.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fileArray = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image image = new Image(fileArray, StringUtils.getFilenameExtension(file.getPath()));

        imageDao.saveAndFlush(image);

        assertEquals(1, imageDao.findAll().size());
    }
}
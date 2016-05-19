package com.tanat.shop.util;

import com.tanat.shop.model.Image;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

public class LoadImage {
    public static Image load(String name) {
        File file = null;
        byte[] fileArray = new byte[0];

        try {
            file = ResourceUtils.getFile("classpath:img/" + name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fileArray = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Image(fileArray, StringUtils.getFilenameExtension(file.getPath()));
    }
}

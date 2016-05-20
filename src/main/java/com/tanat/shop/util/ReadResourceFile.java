package com.tanat.shop.util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

public class ReadResourceFile {
    public static byte[] read(String fileName) {
        try {
            File file = ResourceUtils.getFile("classpath:img/" + fileName);
            return Files.readAllBytes(file.toPath());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error: file " + fileName + " not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Error: read file " + fileName, e);
        }
    }
}

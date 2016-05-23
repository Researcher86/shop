package com.tanat.shop.util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ReadResourceFile {
    public static byte[] read(String fileName) throws IOException {
        File file = ResourceUtils.getFile("classpath:img/" + fileName);
        return Files.readAllBytes(file.toPath());
    }
}

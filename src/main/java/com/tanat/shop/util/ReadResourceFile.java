package com.tanat.shop.util;

import com.tanat.shop.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

public class ReadResourceFile {
    private static final Logger LOG = LoggerFactory.getLogger(ReadResourceFile.class);

    private ReadResourceFile() {
    }

    public static byte[] read(String fileName) {
        File file;
        try {
            file = ResourceUtils.getFile("classpath:img/" + fileName);
            return Files.readAllBytes(file.toPath());
        } catch (FileNotFoundException e) {
            LOG.error("File not found", e);
        } catch (IOException e) {
            LOG.error("Error reading file", e);
        }

        throw new AppException("Error read resource");
    }
}

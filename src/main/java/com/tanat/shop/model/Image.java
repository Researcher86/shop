package com.tanat.shop.model;

import com.tanat.shop.exception.ImageLoadException;
import com.tanat.shop.util.ReadResourceFile;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Base64;

@Entity
@Table(name = "images")
public class Image {
    private static final int MB = 1024 * 1024;

    @Id
    @GeneratedValue
    private Long id;

    @Lob
    @Column(length = MB)
    private byte[] data;

    private String ext;

    public Image() {
    }

    public Image(byte[] data, String ext) {
        this();
        this.data = Arrays.copyOf(data, data.length);
        this.ext = ext;
    }

    public Long getId() {
        return id;
    }

    public byte[] getData() {
        return Arrays.copyOf(data, data.length);
    }

    public String getExt() {
        return ext;
    }

    public String getBase64() {
        return Base64.getEncoder().encodeToString(this.data);
    }

    public static Image load(String fileName) {
        byte[] data = ReadResourceFile.read(fileName);

        if (data == null) {
            throw new ImageLoadException("Error load image " + fileName);
        }

        return new Image(data, StringUtils.getFilenameExtension(fileName));
    }
}

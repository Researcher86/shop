package com.tanat.shop.model;

import com.tanat.shop.util.ReadResourceFile;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Base64;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue
    private Long id;

    @Lob
    @Column(length = 1000000)
    private byte[] data;

    private String ext;

    public Image() {
    }

    public Image(byte[] data, String ext) {
        this.data = data;
        this.ext = ext;
    }

    public Long getId() {
        return id;
    }

    public byte[] getData() {
        return data;
    }

    public String getExt() {
        return ext;
    }

    public String getBase64() {
        return Base64.getEncoder().encodeToString(this.data);
    }

    public static Image load(String fileName) {
        return new Image(ReadResourceFile.read(fileName), StringUtils.getFilenameExtension(fileName));
    }
}

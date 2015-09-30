package com.tanat.shop.domain;

/**
 * Created by Tanat on 30.09.2015.
 */
public class Goods {
    private String name;
    private int price;
    private CategoryOfGoods categoryOfGoods;
    private String shortDescription;
    private String fullDescription;
    private byte[] picture;

    public Goods() {
    }

    public Goods(String name, int price, String shortDescription, String fullDescription, byte[] picture) {
        this.name = name;
        this.price = price;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public CategoryOfGoods getCategoryOfGoods() {
        return categoryOfGoods;
    }

    public void setCategoryOfGoods(CategoryOfGoods categoryOfGoods) {
        this.categoryOfGoods = categoryOfGoods;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}

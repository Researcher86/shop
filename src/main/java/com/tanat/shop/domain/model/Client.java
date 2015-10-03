package com.tanat.shop.domain.model;

/**
 * Created by Tanat on 30.09.2015.
 */
public class Client {
    private String fio;
    private String phone;
    private String shippingAddress;

    public Client() {
    }

    public Client(String fio, String phone, String shippingAddress) {
        this.fio = fio;
        this.phone = phone;
        this.shippingAddress = shippingAddress;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}

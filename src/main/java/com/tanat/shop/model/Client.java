package com.tanat.shop.model;

/**
 * Created by Tanat on 30.09.2015.
 */
public class Client {
    private String fio;
    private String phone;
    private String address;

    public Client() {
    }

    public Client(String fio, String phone, String address) {
        this.fio = fio;
        this.phone = phone;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

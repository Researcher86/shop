package com.tanat.shop.model;

/**
 * Created by Tanat on 30.09.2015.
 */
public class Client extends AbstractModel {
    private String fio;
    private String phone;
    private String address;
    private String email;

    public Client() {
    }

    public Client(String fio, String phone, String address, String email) {
        this.fio = fio;
        this.phone = phone;
        this.address = address;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

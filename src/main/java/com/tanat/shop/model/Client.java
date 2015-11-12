package com.tanat.shop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Tanat on 30.09.2015.
 */
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue
    private Long id;

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

    public Long getId() {
        return id;
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

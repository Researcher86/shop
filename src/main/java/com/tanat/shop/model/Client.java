package com.tanat.shop.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "clients")
public class Client implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Не заполнено поле Ф.И.О.")
    @Column(nullable = false)
    private String fio;

    @NotBlank(message = "Не заполнено поле телефон")
    @Column(nullable = false)
    private String phone;

    @NotBlank(message = "Не заполнено поле адрес")
    @Column(nullable = false)
    private String address;

    @NotBlank(message = "Не заполнено поле почтовый ящик")
    @Email(message = "Некорректный адрес электронной почты")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Не заполнено поле пароль")
    @Column(nullable = false)
    private String password;

    public Client() {
    }

    public Client(String fio, String phone, String address, String email, String password) {
        this.fio = fio;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Client createSimple() {
        return new Client("Альпенов Танат Маратович", "87011520885", "Дощанова 133б", "researcher86@mail.ru", "123456");
    }
}

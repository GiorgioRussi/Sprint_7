package ru.praktikum.scooter.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderModel {
    public String firstName;
    public String lastName;
    public String address;
    public Integer metroStation;
    public String phone;
    public Integer rentTime;
    public String deliveryDate;
    public String comment;
    public List<String> color;
}

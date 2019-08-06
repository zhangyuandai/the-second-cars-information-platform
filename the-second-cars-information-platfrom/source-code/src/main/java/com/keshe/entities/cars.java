package com.keshe.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class cars {

    @Id
    @GeneratedValue
    private Long id;
    private String carname;
    private String price;
    private String city;
    private String phonenumber;
    private String tupian;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getTupian() {
        return tupian;
    }

    public void setTupian(String tupian) {
        this.tupian = tupian;
    }

    public cars(String carname, String price, String city, String phonenumber, String tupian) {
        this.carname = carname;
        this.price = price;
        this.city = city;
        this.phonenumber = phonenumber;
        this.tupian = tupian;
    }

    public cars() {
    }

    @Override
    public String toString() {
        return "cars{" +
                "id=" + id +
                ", carname='" + carname + '\'' +
                ", price='" + price + '\'' +
                ", city='" + city + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", tupian='" + tupian + '\'' +
                '}';
    }
}

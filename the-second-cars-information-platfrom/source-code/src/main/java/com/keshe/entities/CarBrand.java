package com.keshe.entities;

import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CarBrand {

    @Id
    @GeneratedValue
    private Long id;
    private String brandname;
    private String tupian;

    public CarBrand(String brandname, String tupian) {
        this.brandname = brandname;
        this.tupian = tupian;
    }

    public CarBrand() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public String getTupian() {
        return tupian;
    }

    public void setTupian(String tupian) {
        this.tupian = tupian;
    }

    @Override
    public String toString() {
        return "CarBrand{" +
                "id=" + id +
                ", brandname='" + brandname + '\'' +
                ", tupian='" + tupian + '\'' +
                '}';
    }
}

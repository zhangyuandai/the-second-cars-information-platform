package com.keshe.entities;

public class CarType {
    private Integer id;
    private String name;
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CarType(Integer id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public CarType(){

    }

    @Override
    public String toString() {
        return "CarType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

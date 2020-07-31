package kr.co.fastcampus.Eatgo.domain;

import lombok.Data;

public class Restaurant {

    private final String name;

    private String address;

    private Long id;

    public Restaurant(String name){
        this.name = name;
    }

    public Restaurant(Long id, String name, String address){
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getInformation(){
        return name+" in "+address;
    }
}

package com.example.hw2;

import org.springframework.stereotype.Component;

@Component
public class MyBook {
    static int counter;

    private final int id;
    private String name;

    public MyBook(){
        this.id = counter;
        this.name = "Book#" + this.id;
        counter++;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyBook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

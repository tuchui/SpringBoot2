package com.mao.springweb.jsonTest;

import java.io.Serializable;

public class Book implements Serializable {

    public String getName() {
        return name;
    }

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    private String name;
    private String author;

}

package com.example.sergei.testkilogramfirst.model;

/**
 * Created by sergei on 06.11.2015.
 */
public class Music {

    String id;
    String name;
    String author;

    public Music(String id, String author, String name) {
        this.id = id;
        this.author = author;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
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
}

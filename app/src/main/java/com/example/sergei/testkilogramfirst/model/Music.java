package com.example.sergei.testkilogramfirst.model;

/**
 * Created by sergei on 06.11.2015.
 */
public class Music {

    int id;
    int version;
    String label;
    String author;

    public Music(int id, String author, String label) {
        this.id = id;
        this.author = author;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String name) {
        this.label = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}

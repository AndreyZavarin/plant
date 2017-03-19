package com.demo.models;


import java.time.LocalDateTime;

//todo
public class Photo {

    public Long id;

    public Image image;

    public Client client;

    public AppUser uploader;

    public LocalDateTime dateCreated = LocalDateTime.now();

}

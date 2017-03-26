package com.demo.models.todo;


import com.demo.models.AppUser;
import com.demo.models.Client;

import java.time.LocalDateTime;

public class Photo {

    public Long id;

    public Image image;

    public Client client;

    public AppUser uploader;

    public LocalDateTime dateCreated = LocalDateTime.now();

}

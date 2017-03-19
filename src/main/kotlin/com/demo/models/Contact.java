package com.demo.models;

import com.demo.models.enums.ContactType;

import javax.persistence.*;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    public Long id;

    @Column
    public ContactType type;

    @Column(nullable = false)
    public String identifier;

}

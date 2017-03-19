package com.demo.models;

import com.demo.models.enums.Gender;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    public Long id;

    @Column(name = "first_name", nullable = false)
    public String firstName;

    @Column(name = "last_name", nullable = false)
    public String lastName;

    @Column(name = "middle_name")
    public String middleName;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    public Gender gender;

    @Column(name = "birth_date", nullable = false)
    public LocalDate birthDate;

    @Column(name = "date_created")
    public LocalDateTime dateCreated = LocalDateTime.now();

//    @OneToMany(fetch = FetchType.LAZY)
//    public List<Contact> contacts = new ArrayList<>();

    @ManyToOne //because OneToOne is for the wicked
    public Details details;

    protected Client() {
    }

    public Client(String firstName, String lastName, String middleName, Gender gender, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.gender = gender;
        this.birthDate = birthDate;
    }
}

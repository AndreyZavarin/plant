package com.demo.models;

import com.demo.models.enums.SubState;

import javax.persistence.*;
import java.time.LocalDateTime;

public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    public Long id;

    @Column
    @Enumerated(EnumType.ORDINAL)
    public SubState state = SubState.idle;

    @Column
    public Integer number;

    @Column
    public String name;

    @Column
    public LocalDateTime startDate;

    @Column
    public LocalDateTime endDate;

    @Column
    public Integer balance;

    @Column
    public String description;

    @ManyToOne
    public Client client;

    @ManyToOne
    public Tariff tariff;

}

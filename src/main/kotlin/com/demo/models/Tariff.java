package com.demo.models;

import com.demo.models.enums.TariffType;

import javax.persistence.*;
import java.math.BigDecimal;

public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    public Long id;

    @Column(name = "name")
    public String name;

    @Column
    @Enumerated(EnumType.ORDINAL)
    public TariffType type;

    @Column
    public BigDecimal cost;

    @Column
    public Integer quantity;

    @Column
    public String description;

    @Column
    public Long lifetime; //todo в чем считаем? millis ?

}

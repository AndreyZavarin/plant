package com.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "client_details")
public class Details {

    private static final int DESCRIPTION_SIZE = 500;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    public Long id;

    @Column(length = DESCRIPTION_SIZE)
    String experience;

    @Column(length = DESCRIPTION_SIZE)
    String achievements;

    @Column(length = DESCRIPTION_SIZE)
    String targets;

    @Column(length = DESCRIPTION_SIZE)
    String lastCold;

    @Column(length = DESCRIPTION_SIZE)
    String contraindications;

    @Column(length = DESCRIPTION_SIZE)
    String injuries;

    @Column(length = DESCRIPTION_SIZE)
    String referrer;

    @Column()
    boolean crossFit;

    @Column(name = "rules_accepted")
    boolean rulesAccepted;


}

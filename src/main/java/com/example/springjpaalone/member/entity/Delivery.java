package com.example.springjpaalone.member.entity;

import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "DELIVERY_ID")
    private Long id;


    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Embedded
    private Address address;
}

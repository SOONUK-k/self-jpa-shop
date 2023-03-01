package com.example.springjpaalone.member.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ITEM_ID")
    //PERSIST, REMOVE
    private Item item;
    //단방향

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private Order order;
    //양방향


    private int orderPrice;
    //주문가격

    private int count;
    //주문 수량


}

package com.example.springjpaalone.member.entity;


public enum Status {
    ORDER(1, "주문 준비중"),
    DELIVERY(2, "배송 중"),

    CANCEL(3, "취소 완료");

    private int statusNumber;
    private String description;

    Status(int statusNumber, String description) {
        this.statusNumber = statusNumber;
        this.description = description;
    }
}

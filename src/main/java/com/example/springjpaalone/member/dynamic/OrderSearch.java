package com.example.springjpaalone.member.dynamic;

import com.example.springjpaalone.member.entity.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {

    private String memberName;

    private Status status;
}

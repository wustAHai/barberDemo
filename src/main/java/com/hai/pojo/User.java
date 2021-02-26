package com.hai.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created by chenz at 21:52 on 2021/2/26
 */
@Data
public class User {
    private int id;
    private String name;
    private String password;
    private int role;
    private double balance;
    private Date addTime;
}

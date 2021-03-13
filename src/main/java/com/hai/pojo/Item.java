package com.hai.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created by chenz at 9:50 on 2021/2/27
 */
@Data
public class Item {
    int id;
    String name;
    double cost;
    String des;
    int type; //0-消费 1-充值
    Date  addTime;
    int consumer_id;
}

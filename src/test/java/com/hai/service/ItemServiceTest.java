package com.hai.service;

import com.hai.pojo.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by chenz at 1:39 on 2021/3/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Test
    public void addXiaoFeiItem() {
        Item item = new Item();
        item.setName("test1");
        item.setDes("剪头发");
        item.setConsumer_id(4);
        item.setCost(20);
        itemService.addItem(item);
    }

    @Test
    public void addChongzhiItem() {
        Item item = new Item();
        item.setName("test1");
        item.setDes("充值");
        item.setType(1);
        item.setConsumer_id(4);
        item.setCost(200);
        itemService.addItem(item);
    }
}
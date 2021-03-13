package com.hai.mapper;

import com.hai.pojo.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by chenz at 0:40 on 2021/3/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemMapperTest {

    @Autowired
    ItemMapper itemMapper;
    @Test
    public void addItem() {
        Item item = new Item();
        item.setName("test1");
        item.setDes("剪头发");
        item.setConsumer_id(4);
        itemMapper.addItem(item);
    }

    @Test
    public void getItemByName(){
        String name = "test1";
        List<Item> itemByName = itemMapper.getItemByName(name);
        for (Item item :
                itemByName) {
            System.out.println(item);
        }
    }
    @Test
    public void getItemCount(){
        String name="";
        System.out.println(itemMapper.getItemCount(2,name));
    }

    @Test
    public void findPageOrders(){
        List<Item> items = itemMapper.findPageOrders(0, "1", 0, 8);
        for (Item item :
                items) {
            System.out.println(item);
        }
    }
}
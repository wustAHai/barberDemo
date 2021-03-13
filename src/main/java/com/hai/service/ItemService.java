package com.hai.service;

import com.hai.mapper.ItemMapper;
import com.hai.mapper.UserMapper;
import com.hai.pojo.Item;
import com.hai.pojo.PageHelper;
import com.hai.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chenz at 0:31 on 2021/3/12
 */
@Service
public class ItemService {
    @Autowired
    ItemMapper itemMapper;
    @Autowired
    UserMapper userMapper;
    //0-消费 1-充值
    @Transactional
    public void addItem(Item item) {
        if (item.getType()==0){
            userMapper.balanceOut(item.getCost(), item.getConsumer_id());
        }else {
            userMapper.balanceIn(item.getCost(), item.getConsumer_id());
        }
        itemMapper.addItem(item);
    }

    public PageHelper<Item> getPageHelper(String name, int type, String pageNum) {
        PageHelper<Item> pageHelper = new PageHelper<>();
        int current = 1;
        try {
            current=Integer.parseInt(pageNum);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (name==null){
            name="";
        }
        pageHelper.setItemCount(itemMapper.getItemCount(type,name));
        pageHelper.setPageCount((pageHelper.getItemCount()+ pageHelper.getItemsPerPage()-1)/ pageHelper.getItemsPerPage());
        pageHelper.setCurrentPage(current);
        pageHelper.setItems(itemMapper.findPageOrders(type,name,(current-1)*pageHelper.getItemsPerPage(),pageHelper.getItemsPerPage()));
        return pageHelper;
    }


    public Object getPageHelperByName(String name, int type, String pageNum) {
        PageHelper<Item> pageHelper = new PageHelper<>();
        int current = 1;
        try {
            current=Integer.parseInt(pageNum);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (name==null){
            name="";
        }
        pageHelper.setItemCount(itemMapper.getItemCountByName(type,name));
        pageHelper.setPageCount((pageHelper.getItemCount()+ pageHelper.getItemsPerPage()-1)/ pageHelper.getItemsPerPage());
        pageHelper.setCurrentPage(current);
        pageHelper.setItems(itemMapper.findPageOrdersByName(type,name,(current-1)*pageHelper.getItemsPerPage(),pageHelper.getItemsPerPage()));
        return pageHelper;
    }
}

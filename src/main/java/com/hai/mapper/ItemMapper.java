package com.hai.mapper;

import com.hai.pojo.Item;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by chenz at 0:32 on 2021/3/12
 */
@Mapper
public interface ItemMapper {

    @Insert("Insert into item(name,cost,des,type,consumer_id) value(#{name},#{cost},#{des},#{type},#{consumer_id})")
    public void addItem(Item item);

    @Select("select * from item where name=#{name}")
    public List<Item> getItemByName(String name);

    @Select({"<script>",
            "select count(*) from item",
            "where 1=1",
            "AND name like CONCAT('%',#{name},'%')",
            "<when test='type!=2'>",
            "AND type = #{type}",
            "</when>",
            "</script>"})
    public int getItemCount(int type,String name);

    @Select({"<script>",
            "select * from item",
            "where 1=1",
            "AND name like CONCAT('%',#{name},'%')",
            "<when test='type!=2'>",
            "AND type = #{type}",
            "</when>",
            "order by id desc",
            "limit #{offset},#{length}",
            "</script>"})
    List<Item> findPageOrders(int type, String name, int offset, int length);

    @Select({"<script>",
            "select count(*) from item",
            "where 1=1",
            "AND name = #{name}",
            "<when test='type!=2'>",
            "AND type = #{type}",
            "</when>",
            "</script>"})
    public int getItemCountByName(int type,String name);


    @Select({"<script>",
            "select * from item",
            "where 1=1",
            "AND name = #{name}",
            "<when test='type!=2'>",
            "AND type = #{type}",
            "</when>",
            "order by id desc",
            "limit #{offset},#{length}",
            "</script>"})
    List<Item> findPageOrdersByName(int type, String name, int offset, int length);


}

package com.hai.mapper;

import com.hai.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by chenz at 21:59 on 2021/2/26
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    User findUserById(int id);

    @Select("select * from user where name=#{username}")
    List<User> findUserByUsername(String username);

    @Insert("insert into user(name,password) value(#{name},#{password})")
    void addUser(User user);

    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where role=#{role}")
    List<User> findByRole(int role);

    @Update("update user set balance=balance-#{out} where id=#{id}")
    void balanceOut(double out,int id);

    @Update("update user set balance=balance+#{in} where id=#{id}")
    void balanceIn(double in,int id);

    @Select("select * from user where name like CONCAT('%',#{name},'%') and role=2")
    List<User> findUserLikeName(String name);

    @Select("select count(*) from user where name like CONCAT('%',#{name},'%') and role=2")
    int findUserNum(String name);

    @Select("select * from user where name like CONCAT('%',#{name},'%') and role=2 limit #{offset},#{length} ")
    List<User> findPageUsers(String name,int offset,int length);

    @Select("select role from user where name =#{name}")
    int queryUserRole(String name);

    @Select("select count(*) from user where name = #{name} and role=2")
    int findUserNumByName(String name);


    @Select("select * from user where name  = #{name} and role=2 limit #{offset},#{length} ")
    List<User> findPageUsersByName(String name,int offset,int length);
}

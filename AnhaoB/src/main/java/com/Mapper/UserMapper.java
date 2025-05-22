package com.Mapper;

import com.people.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE username = #{username} AND password = #{password}")
    User findUser(@Param("username") String username, @Param("password") String password);
    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(@Param("username") String username);
    @Insert("INSERT INTO users(username,password,phone)VALUES (#{username},#{password},#{phone})")
    void insertUser(User user);
    @Insert("INSERT INTO ${fanTable} (username, avatar, phone) VALUES (#{username}, #{avatar}, #{phone})")
    int addUserToFansTable(@Param("username") String username, @Param("avatar") String avatar, @Param("phone") String phone, @Param("fanTable") String fanTable);
    @Update("UPDATE users SET phone = #{phone}, password = #{password}, avatar = #{avatar} WHERE id = #{id}")
    void updateUser(User user);
    @Update("UPDATE users SET avatar = #{avatar} WHERE username = #{username}")
    void updateAvatar(@Param("username") String username, @Param("avatar") String avatar);
}

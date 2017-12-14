package com.me.tking.dao;

import com.me.tking.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    @Select("select * from User u where u.id = #{userId}")
    User getUserById(@Param("userId") int userId);
}

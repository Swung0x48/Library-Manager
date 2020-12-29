package com.swung0x48.librarymanager.repository;

import com.swung0x48.librarymanager.domain.LibUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserRepository {
    @Select("Select * from libusers where userID=#{id}")
    LibUser selectUserByID(Integer id);

    @Select("Select * from libusers")
    List<LibUser> selectAllUser();

    @Select("Select * from libusers where userName=#{name}")
    LibUser selectUserByName(String name);

    @Update("update libusers set role='banned' where userId=#{userId}")
    void banUser(Integer userId);

    @Update("update libusers set role='vip0' where userId=#{userId}")
    void unbanUser(Integer userId);

    @Insert("insert into libusers(userName,password,role)" +
            "values(#{userName},#{password},#{role})")
    void addUser(LibUser libUser);

    @Delete("delete from libusers where userId=#{userId}")
    void deleteUser(Integer userId);
}

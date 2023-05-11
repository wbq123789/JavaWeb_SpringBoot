package com.example.mapper;

import com.example.cache.MybatisRedisCache;
import com.example.entity.User;
import org.apache.ibatis.annotations.*;
@CacheNamespace(implementation = MybatisRedisCache.class)
@Mapper
public interface UserMapper {
    @Select("select * from users where name = #{username}")
    User getPasswordByUsername(String username);
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    @Insert("insert into users(name,role,password) values(#{name},#{role},#{password})")
    int registerUser(User user);
    @Insert("insert into student(uid,name,sex,grade) values(#{uid},#{name},#{sex},#{grade})")
    int addStudentInfo(@Param("uid") int uid,@Param("name") String name,@Param("sex") String sex,@Param("grade") String grade);
    @Select("select sid from student where uid=#{id}")
    Integer getSidById(int id);
    @Select("select count(*) from student")
    int getStudentsCount();
}

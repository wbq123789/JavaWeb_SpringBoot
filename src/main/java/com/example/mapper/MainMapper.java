package com.example.mapper;

import com.example.cache.MybatisRedisCache;
import com.example.entity.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@CacheNamespace(implementation = MybatisRedisCache.class)
@Mapper
public interface MainMapper {

    @Select("select * from users where name=#{name}")
    User findUserByName(String name);
}

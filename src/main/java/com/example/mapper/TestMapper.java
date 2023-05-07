package com.example.mapper;

import com.example.cache.MybatisRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

//只需要修改缓存实现类implementation为我们的RedisMybatisCache即可
@CacheNamespace(implementation = MybatisRedisCache.class)
@Mapper
public interface TestMapper {

    @Select("select name from student where sid = 1")
    String getSid();
}
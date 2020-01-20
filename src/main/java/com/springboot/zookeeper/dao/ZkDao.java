package com.springboot.zookeeper.dao;

import com.springboot.zookeeper.pojo.Teacher;

import java.util.List;

/**
 * @Author fr
 * @Date 2020-01-20 13:55
 */
public interface ZkDao {

    public List<Teacher> findAll();
}

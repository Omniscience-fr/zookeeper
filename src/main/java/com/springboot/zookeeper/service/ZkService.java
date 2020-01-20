package com.springboot.zookeeper.service;

import com.springboot.zookeeper.pojo.Teacher;

import java.util.List;

/**
 * @Author fr
 * @Date 2020-01-20 13:54
 */
public interface ZkService {

    public List<Teacher> findAll();
}

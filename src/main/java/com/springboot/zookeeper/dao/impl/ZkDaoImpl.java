package com.springboot.zookeeper.dao.impl;

import com.springboot.zookeeper.dao.ZkDao;
import com.springboot.zookeeper.pojo.Teacher;
import com.springboot.zookeeper.repository.ZkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author fr
 * @Date 2020-01-20 13:56
 */
@Repository
public class ZkDaoImpl implements ZkDao {
    @Autowired
    private ZkRepository repository;

    @Override
    public List<Teacher> findAll() {
        return repository.findAll();
    }
}

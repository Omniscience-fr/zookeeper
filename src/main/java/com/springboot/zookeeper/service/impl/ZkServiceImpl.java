package com.springboot.zookeeper.service.impl;

import com.springboot.zookeeper.dao.ZkDao;
import com.springboot.zookeeper.pojo.Teacher;
import com.springboot.zookeeper.service.ZkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author fr
 * @Date 2020-01-20 13:55
 */
@Service
public class ZkServiceImpl implements ZkService {
    @Autowired
    private ZkDao zkDao;

    @Override
    public List<Teacher> findAll() {
        return zkDao.findAll();
    }
}

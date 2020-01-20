package com.springboot.zookeeper.controller;

import com.springboot.zookeeper.config.ZkDiscovery;
import com.springboot.zookeeper.config.ZkRegistry;
import com.springboot.zookeeper.pojo.Teacher;
import com.springboot.zookeeper.service.ZkService;
import com.springboot.zookeeper.utils.ZkConnection;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author fr
 * @Date 2020-01-20 13:52
 */
@Controller
@RequestMapping("/zk")
public class ZkController {
    @Value("${zookeeper.address}")
    private String address;
    @Value("${zookeeper.timeout}")
    private String timeout;

    @Autowired
    private ZkService zkService;


    @RequestMapping("/save")
    @ResponseBody
    public void saveZkNode(){
        List<Teacher> all = zkService.findAll();
        ZkConnection zkConnection = new ZkConnection();
        ZooKeeper zooKeeper = zkConnection.connectionServer(address, timeout);
        ZkRegistry zkRegistry = new ZkRegistry();
        zkRegistry.registry(all,zooKeeper,"/Class","/teacher");
    }

    @RequestMapping("findZk")
    @ResponseBody
    public List<Teacher> findZkNode(){
        ZkConnection zkConnection = new ZkConnection();
        ZooKeeper zooKeeper = zkConnection.connectionServer(address, timeout);
        ZkDiscovery discovery = new ZkDiscovery(zooKeeper,"/Class");
        List<Teacher> discover = discovery.discover();
        return discover;
    }

}

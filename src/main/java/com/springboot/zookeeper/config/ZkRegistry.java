package com.springboot.zookeeper.config;

import com.alibaba.fastjson.JSONObject;
import com.springboot.zookeeper.pojo.Teacher;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Author fr
 * @Date 2020-01-19 13:38
 */

/**
 * zookeeper 注册并创建节点
 */
public class ZkRegistry {

    private Logger logger = LoggerFactory.getLogger(ZkRegistry.class);






    public ZooKeeper registry(List<Teacher> date, ZooKeeper zooKeeper,String rootpath,String nodepath){

        if(date != null){
            if(zooKeeper !=null){
                zooKeeper= createNode(zooKeeper, date,rootpath,nodepath);
            }
        }
        return zooKeeper;
    }



    /**
     * zookeeper 创建节点
     * @param zooKeeper
     * @param date
     * @param rootpath
     * @param nodepath
     */
    private ZooKeeper createNode(ZooKeeper zooKeeper, List<Teacher> date, String rootpath, String nodepath){
        String createpath = null;
        try {
            Stat exists = zooKeeper.exists(rootpath, true);
            if(exists == null){
                zooKeeper.create(rootpath,null, ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            }else {
               createpath = zooKeeper.create(rootpath + nodepath, JSONObject.toJSONBytes(date), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            }
            logger.info("创建zookeeper node (date:{},createpath:{})",date,createpath);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return zooKeeper;
    }




}

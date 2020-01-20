package com.springboot.zookeeper.config;

/**
 * @Author fr
 * @Date 2020-01-19 14:04
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springboot.zookeeper.pojo.Teacher;
import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * zookeeper 服务发现
 */
public class ZkDiscovery {

    private Logger logger = LoggerFactory.getLogger(ZkDiscovery.class);


    private volatile List<Teacher> AddressList = new ArrayList<>();




    public ZkDiscovery(ZooKeeper zooKeeper,String rootpath){
        if(zooKeeper !=null){
            WatchNode(zooKeeper,rootpath);
        }
    }


    public List<Teacher> discover(){
        int size = AddressList.size();
        if(size > 0){
           return AddressList;
        }
        return null;
    }


    private void WatchNode(ZooKeeper zooKeeper, String rootpath){
        try {
            List<String> children = zooKeeper.getChildren(rootpath, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    //监控节点是否发生变化，若是，则递归一次
                    //有个问题就是若是节点持续发生变化，那么将无限递归？
                    if(event.getType() == Event.EventType.NodeChildrenChanged){
                            WatchNode(zooKeeper, rootpath);
                    }
                }
            });

            List<Teacher> datalist = new ArrayList<>();
            List<Teacher> teachers = null;
            for(String node:children){
                byte[] data = zooKeeper.getData(rootpath +"/"+node, false, null);
                teachers = JSONArray.parseArray(new String(data), Teacher.class);
            }

            for (Teacher teacher:teachers) {
                datalist.add(teacher);
            }
            logger.info("node data：{}",datalist);
            this.AddressList = datalist;

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteNode(ZooKeeper zooKeeper,String path){
        try {
            zooKeeper.delete(path,0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

}

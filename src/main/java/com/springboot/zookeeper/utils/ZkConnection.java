package com.springboot.zookeeper.utils;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Author fr
 * @Date 2020-01-20 11:24
 */
@Component
public class ZkConnection {



    /**
     设置递减计数器,用于限制每次允许多少个线程并发连接zk
     */
    private CountDownLatch latch = new CountDownLatch(1);


    /**
     * zookeeper 创建连接
     * @return
     */
    public ZooKeeper connectionServer(String address,String timeout){
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper(address,Integer.valueOf(timeout).intValue(), new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if(event.getState() == Event.KeeperState.SyncConnected){
                        latch.countDown();
                    }
                }
            });
            latch.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return zk;
    }
}

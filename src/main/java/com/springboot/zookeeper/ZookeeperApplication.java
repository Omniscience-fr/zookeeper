package com.springboot.zookeeper;

import com.springboot.zookeeper.config.ZkDiscovery;
import com.springboot.zookeeper.config.ZkRegistry;
import com.springboot.zookeeper.utils.ZkConnection;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;

@SpringBootApplication
/**
 * implements CommandLineRunner  接口 用于类启动后加载，类似于PostConstruct注解
 */
public class ZookeeperApplication {

    @Value("${zookeeper.address}")
    private String address;

    @Value("${zookeeper.timeout}")
    private String timeout;

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        ZkConnection connection = new ZkConnection();
//        ZooKeeper zooKeeper = connection.connectionServer(address,timeout);
//        ZkRegistry zkRegistry = new ZkRegistry();
//        String ip = InetAddress.getLocalHost().getHostAddress();
//        ZooKeeper registry = zkRegistry.registry(ip,zooKeeper);
////        ZkDiscovery zkDiscovery = new ZkDiscovery(registry);
////        String dscover = zkDiscovery.dscover();
////        System.out.println(dscover);
////        zkDiscovery.deleteNode(registry,"/TestZookeeper");
//    }
}

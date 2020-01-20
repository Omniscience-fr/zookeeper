package com.springboot.zookeeper.repository;

import com.springboot.zookeeper.pojo.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author fr
 * @Date 2020-01-20 13:56
 */
public interface ZkRepository extends JpaRepository<Teacher,Long> {
}

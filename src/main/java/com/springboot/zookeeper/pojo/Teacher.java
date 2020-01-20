package com.springboot.zookeeper.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author fr
 * @Date 2020-01-20 10:05
 */
@Entity
@Table(name = "teacher")
@Data
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String teacherName;
    private String studentName;
    private String studentSex;
    private int studentAge;

}

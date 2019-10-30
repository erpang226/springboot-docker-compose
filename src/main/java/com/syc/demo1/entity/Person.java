package com.syc.demo1.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Description:
 *
 * @version V1.0
 * date: 2019/10/30
 * author: song yong chang
 */
@Data
@Entity
@Table
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private Integer age;

}

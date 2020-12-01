package com.kindustry.jpa.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlType;

/**
 * 用户信息，用于远程调用传输，必须实现Serializable接口
 * 
 * @author andy
 *
 */
@Entity
@Table(name = "User")
@XmlType(name = "user")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private int userid;

  private String name;

  private int age;

  public int getUserid() {
    return userid;
  }

  public void setUserid(int userid) {
    this.userid = userid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "name : " + this.name + ", age : " + this.age;
  }
}
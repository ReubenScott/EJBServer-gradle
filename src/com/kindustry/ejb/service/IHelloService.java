package com.kindustry.ejb.service;

import java.util.Date;

import javax.ejb.Remote;

import com.kindustry.jpa.model.Gurupu;

@Remote
public interface IHelloService {
  public String echo(String msg);

  public Date getTime();

  /**
   * 
   * @param user
   * @return
   *         @
   */
  public Gurupu updateUser(Gurupu user);
}

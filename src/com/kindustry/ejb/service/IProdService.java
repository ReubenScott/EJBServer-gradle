package com.kindustry.ejb.service;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.Local;

@Local
public interface IProdService extends Serializable {

  public String echo(String msg);

  public Date getTime();
}

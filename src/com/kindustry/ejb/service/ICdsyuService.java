package com.kindustry.ejb.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import com.kindustry.jpa.model.Cdsyu;

@Remote
public interface ICdsyuService extends Serializable {

  public Cdsyu findCdsyu(String cdsyu);

  public List<Cdsyu> queryCdsyu(String cdsyusetumei);

  public List<Cdsyu> queryCdsyuBysetumei(String cdsyusetumei);

  public Date getTime();
}

package com.kindustry.ejb.service;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Remote;

import com.kindustry.jpa.model.Nyukin;

@Remote
public interface INyukinService {

  public List<Nyukin> queryNyukin(String nyukintime);

  public List<Nyukin> queryNyukin(Timestamp nyukintime);
}

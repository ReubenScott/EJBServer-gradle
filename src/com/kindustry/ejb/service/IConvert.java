package com.kindustry.ejb.service;

import java.io.Serializable;
import java.util.List;

import com.kindustry.jpa.model.Gurupu;

public interface IConvert extends Serializable {

  void delGurupuByKey(int gurupucd);

  void delGurupuByCondition(int gurupucd);

  boolean insertGurupu(Gurupu gurupu);

  Gurupu updateGurupu(Gurupu gurupu);

  Gurupu addGurupu(Gurupu gurupu);

  List<Gurupu> listGurupu(int gurupucd);

  List<Gurupu> findGurupuByMei(String gurupumei);
}
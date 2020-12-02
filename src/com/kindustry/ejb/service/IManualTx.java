package com.kindustry.ejb.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Remote;

import com.kindustry.jpa.model.Gurupu;

// 定义远程接口
@Remote
public interface IManualTx extends Serializable {

  void delGurupuByKey(int gurupucd);

  void delGurupuByCondition(int gurupucd);

  Gurupu updateGurupu(Gurupu gurupu);

  Gurupu addGurupu(Gurupu gurupu);

  List<Gurupu> listGurupu(int gurupucd);

}
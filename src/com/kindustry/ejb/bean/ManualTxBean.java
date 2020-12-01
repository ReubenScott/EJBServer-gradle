package com.kindustry.ejb.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.transaction.UserTransaction;

import com.kindustry.ejb.service.ContainerService;
import com.kindustry.ejb.service.IManualTx;
import com.kindustry.jpa.model.Gurupu;

// 状态定义实列Bean 提供远程JNDI
@Stateless(name = "ConvertBean", mappedName = "OperationBeanRemote")
@TransactionManagement(TransactionManagementType.BEAN)
public class ManualTxBean implements IManualTx {

  private static final long serialVersionUID = 1L;

  @PersistenceContext(unitName = "mysqlDS")
  private EntityManager em;

  @Resource
  private UserTransaction ut;

  @Resource
  private SessionContext sessCtx;

  @EJB
  private ContainerService service;

  @Transactional(value = TxType.REQUIRED)
  public void delGurupuByKey(int gurupucd) {
    service.getTime();
    Gurupu entity = em.find(Gurupu.class, gurupucd);
    if (entity != null) {
      em.remove(entity);
    }
  }

  @Transactional(value = TxType.REQUIRED)
  public void delGurupuByCondition(int gurupucd) {
    Query query = em.createQuery("Delete Gurupu g where g.gurupucd > :gurupucd ").setParameter("gurupucd", gurupucd);
    query.executeUpdate();
  }

  public Gurupu addGurupu(Gurupu gurupu) {
    System.out.println("-------------- 客户端发送的user为" + gurupu.toString());
    try {
      ut.begin();
      // gurupu.setGurupumei("andy2");
      // gurupu.setGurupucd(30);
      em.persist(gurupu);
      System.out.println("服務器端執行成功:保存姓名" + gurupu.getGurupumei());
      ut.commit();
    } catch (Exception e) {
      try {
        e.printStackTrace();
        ut.rollback();
      } catch (Exception e1) {
        e1.printStackTrace();
      }

    }
    return gurupu;
  }

  @TransactionAttribute(TransactionAttributeType.SUPPORTS)
  public Gurupu updateGurupu(Gurupu gurupu) {
    System.out.println("-------------- 客户端发送的为 " + gurupu.toString());
    try {
      ut.begin();
      em.merge(gurupu);
      System.out.println("服務器端執行成功:保存 " + gurupu.toString());
      ut.commit();
    } catch (Exception e) {
      try {
        e.printStackTrace();
        ut.rollback();
      } catch (Exception e1) {

        e1.printStackTrace();
      }

    }
    return gurupu;
  }

  @Override
  @Transactional(value = TxType.REQUIRED)
  public List<Gurupu> listGurupu(int gurupucd) {
    System.out.println("listGurupu start : " + gurupucd);
    List<Gurupu> result = new ArrayList<Gurupu>();
    Query query = em.createQuery("FROM Gurupu g where g.gurupucd = :gurupucd order by g.gurupucd desc", Gurupu.class).setParameter("gurupucd", gurupucd);
    result = query.getResultList();
    em.flush();
    System.out.println("listGurupu end : " + result.size());

    return result;
  }

}
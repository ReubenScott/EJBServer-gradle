package com.kindustry.ejb.bean;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.kindustry.ejb.service.ICdsyuService;
import com.kindustry.jpa.model.Cdsyu;

@Stateless(mappedName = "Cmt2")
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CdsyuServiceBean implements ICdsyuService {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  // @PersistenceContext(unitName = "mysqlDS")
  private EntityManager em;

  @Override
  public Cdsyu findCdsyu(String cdsyu) {
    return em.find(Cdsyu.class, cdsyu);
  }

  public List<Cdsyu> queryCdsyuBysetumei(String cdsyusetumei) {
    Query query = em.createQuery("FROM Cdsyu g where g.cdsyusetumei like '%'||:cdsyusetumei||'%' ", Cdsyu.class).setParameter("cdsyusetumei", cdsyusetumei);
    return query.getResultList();
  }

  public List<Cdsyu> queryCdsyu(String cdsyusetumei) {
    Query query = em.createQuery("FROM Cdsyu g where g.cdsyusetumei = :cdsyusetumei  ", Cdsyu.class).setParameter("cdsyusetumei", cdsyusetumei);
    return query.getResultList();
  }

  @Override
  public Date getTime() {
    System.out.println("-------- getTime ------ 客户端发送");
    return null;
  }

}

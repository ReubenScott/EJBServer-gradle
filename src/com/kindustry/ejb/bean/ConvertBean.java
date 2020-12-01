package com.kindustry.ejb.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.sql.DataSource;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.transaction.UserTransaction;

import com.kindustry.ejb.service.ICdsyuService;
import com.kindustry.ejb.service.IConvert;
import com.kindustry.jpa.model.Gurupu;

// 状态定义实列Bean 提供远程JNDI
@Stateless(name = "ConvertBean", mappedName = "OperationBeanRemote")
// 定义远程接口
@Remote
@TransactionManagement(TransactionManagementType.BEAN)
public class ConvertBean implements IConvert {

  private static final long serialVersionUID = 1L;

  // @PersistenceContext(unitName = "mysqlDS")
  private EntityManager em;

  @Resource
  private UserTransaction ut;

  @Resource(mappedName = "java:jboss/datasources/mysqlDS")
  private DataSource ds;

  @Resource
  private SessionContext sessCtx;

  @EJB
  private ICdsyuService service;

  @Transactional(value = TxType.NEVER)
  public boolean insertGurupu(Gurupu gurupu) {
    boolean flag = false;
    try {
      ut.begin();
      String sql = "insert into GURUPU values( ? ,  ?)";
      Connection conn = ds.getConnection();
      // Statement stmt = conn.createStatement();
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1, gurupu.getGurupucd());
      stmt.setString(2, gurupu.getGurupumei());

      stmt.execute();
      stmt.close();
      conn.close();
      ut.commit();
      flag = true;
    } catch (SQLException | NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
        | HeuristicRollbackException e) {
      e.printStackTrace();
      try {
        ut.rollback();
      } catch (IllegalStateException e1) {
        e1.printStackTrace();
      } catch (SecurityException e1) {
        e1.printStackTrace();
      } catch (SystemException e1) {
        e1.printStackTrace();
      }
    }
    return flag;
  }

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
    List<Gurupu> result = new ArrayList<Gurupu>();
    Query query = em.createQuery("FROM Gurupu g where g.gurupucd = :gurupucd order by g.gurupucd desc", Gurupu.class).setParameter("gurupucd", gurupucd);
    result = query.getResultList();
    em.flush();
    System.out.println(result.size());

    return result;
  }

  @Override
  @Transactional(value = TxType.NEVER)
  public List<Gurupu> findGurupuByMei(String gurupumei) {
    List<Gurupu> result = new ArrayList<Gurupu>();

    try {
      ut.begin();
      Connection conn = ds.getConnection();

      // String sql = "select * FROM Gurupu g where g.gurupumei  =  " +
      // gurupumei;
      // System.out.println(sql);
      // Statement stmt = conn.createStatement();
      // ResultSet rs = stmt.executeQuery(sql);

      String sql = "select * FROM Gurupu g where g.gurupumei = ? ";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, gurupumei);
      ResultSet rs = stmt.executeQuery();

      // create string with id`s values to delete organisations references
      while (rs.next()) {
        int cd = rs.getInt("gurupucd");
        String mei = rs.getString("gurupumei");
        Gurupu gurupu = new Gurupu();
        gurupu.setGurupucd(cd);
        gurupu.setGurupumei(mei);
        result.add(gurupu);
      }

      rs.close();
      stmt.close();
      conn.close();
      ut.commit();
    } catch (Exception e) {
      e.printStackTrace();
      try {
        e.printStackTrace();
        ut.rollback();
      } catch (Exception e1) {

        e1.printStackTrace();
      }
    }
    System.out.println(result.size());

    return result;
  }

}
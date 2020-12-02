package com.kindustry.ejb.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.transaction.UserTransaction;

import com.kindustry.ejb.service.JdbcService;
import com.kindustry.jpa.model.Gurupu;
import com.kindustry.jpa.model.Nyukin;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JdbcServiceBean implements JdbcService {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Resource(mappedName = "java:jboss/datasources/mysqlDS")
  private DataSource ds;

  @Resource
  private UserTransaction ut;

  public List<Nyukin> queryNyukin(String nyukintime) {
    List<Nyukin> result = new ArrayList<Nyukin>();

    try {
      Connection conn = ds.getConnection();

      String sql = "select * FROM nyukin g where g.nyukintime = ? ";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, nyukintime);
      ResultSet rs = stmt.executeQuery();

      // create string with id`s values to delete organisations references
      while (rs.next()) {
        Nyukin nyukin = new Nyukin();
        nyukin.setKanjano(rs.getInt("kanjano"));
        nyukin.setHkkumi(rs.getShort("hkkumi"));
        nyukin.setSinryodate(rs.getDate("sinryodate"));
        nyukin.setRaiinkaisu(rs.getShort("raiinkaisu"));
        nyukin.setNyukindate(rs.getDate("nyukindate"));
        nyukin.setHutankin(rs.getLong("hutankin"));
        nyukin.setJihikin(rs.getLong("jihikin"));
        nyukin.setNyukingaku(rs.getLong("nyukingaku"));
        nyukin.setTyoseikin(rs.getLong("tyoseikin"));
        nyukin.setJibaiseikyukbn(rs.getShort("jibaiseikyukbn"));
        nyukin.setTeiseinyukinkbn(rs.getShort("teiseinyukinkbn"));
        nyukin.setOpkbn(rs.getShort("opkbn"));
        nyukin.setNyukincomm(rs.getString("nyukincomm"));
        nyukin.setKousintime(rs.getTimestamp("kousintime"));
        nyukin.setRosaiseikyukbn(rs.getShort("rosaiseikyukbn"));
        nyukin.setNyukintime(rs.getTimestamp("nyukintime"));

        result.add(nyukin);
      }

      rs.close();
      stmt.close();
      conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(result.size());

    return result;
  }

  public List<Nyukin> queryNyukin(Timestamp nyukintime) {
    List<Nyukin> result = new ArrayList<Nyukin>();

    try {
      Connection conn = ds.getConnection();

      String sql = "select * FROM nyukin g where g.nyukintime = ? ";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setTimestamp(1, nyukintime);
      ResultSet rs = stmt.executeQuery();

      // create string with id`s values to delete organisations references
      while (rs.next()) {
        Nyukin nyukin = new Nyukin();
        nyukin.setKanjano(rs.getInt("kanjano"));
        nyukin.setHkkumi(rs.getShort("hkkumi"));
        nyukin.setSinryodate(rs.getDate("sinryodate"));
        nyukin.setRaiinkaisu(rs.getShort("raiinkaisu"));
        nyukin.setNyukindate(rs.getDate("nyukindate"));
        nyukin.setHutankin(rs.getLong("hutankin"));
        nyukin.setJihikin(rs.getLong("jihikin"));
        nyukin.setNyukingaku(rs.getLong("nyukingaku"));
        nyukin.setTyoseikin(rs.getLong("tyoseikin"));
        nyukin.setJibaiseikyukbn(rs.getShort("jibaiseikyukbn"));
        nyukin.setTeiseinyukinkbn(rs.getShort("teiseinyukinkbn"));
        nyukin.setOpkbn(rs.getShort("opkbn"));
        nyukin.setNyukincomm(rs.getString("nyukincomm"));
        nyukin.setKousintime(rs.getTimestamp("kousintime"));
        nyukin.setRosaiseikyukbn(rs.getShort("rosaiseikyukbn"));
        nyukin.setNyukintime(rs.getTimestamp("nyukintime"));

        result.add(nyukin);
      }

      rs.close();
      stmt.close();
      conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(result.size());

    return result;
  }

  @Override
  @Transactional(value = TxType.NEVER)
  public List<Gurupu> findGurupuByMei(String gurupumei) {
    List<Gurupu> result = new ArrayList<Gurupu>();
    System.out.println("findGurupuByMei start : " + gurupumei);

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

    System.out.println("findGurupuByMei end : " + result.size());
    return result;
  }

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

}

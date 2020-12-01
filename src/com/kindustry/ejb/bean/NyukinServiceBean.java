package com.kindustry.ejb.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;

import com.kindustry.ejb.service.INyukinService;
import com.kindustry.jpa.model.Nyukin;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class NyukinServiceBean implements INyukinService {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  // @Resource(mappedName = "java:jboss/datasources/mysqlDS")
  private DataSource ds;

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
}

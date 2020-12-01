package com.kindustry.jpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 用户信息，用于远程调用传输，必须实现Serializable接口
 * 
 * @author andy
 *
 */
@Entity
@Table(name = "CDSYU")
public class Cdsyu implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private String cdsyu;

  private String cdsyusetumei;

  @Temporal(TemporalType.DATE)
  // 时间精确到天
  private Date startdate;

  @Temporal(TemporalType.DATE)
  // 时间精确到天
  private Date enddate;

  public String getCdsyu() {
    return cdsyu;
  }

  public void setCdsyu(String cdsyu) {
    this.cdsyu = cdsyu;
  }

  public String getCdsyusetumei() {
    return cdsyusetumei;
  }

  public void setCdsyusetumei(String cdsyusetumei) {
    this.cdsyusetumei = cdsyusetumei;
  }

  public Date getStartdate() {
    return startdate;
  }

  public void setStartdate(Date startdate) {
    this.startdate = startdate;
  }

  public Date getEnddate() {
    return enddate;
  }

  public void setEnddate(Date enddate) {
    this.enddate = enddate;
  }

}
package com.kindustry.jpa.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlType;

/**
 * 用户信息，用于远程调用传输，必须实现Serializable接口
 * 
 * @author andy
 *
 */
@Entity
@Table(name = "Gurupu")
@XmlType(name = "Gurupu")
public class Gurupu implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int gurupucd;

	private String gurupumei;

	public int getGurupucd() {
		return gurupucd;
	}

	public void setGurupucd(int gurupucd) {
		this.gurupucd = gurupucd;
	}

	public String getGurupumei() {
		return gurupumei;
	}

	public void setGurupumei(String gurupumei) {
		this.gurupumei = gurupumei;
	}

	@Override
	public String toString() {
		return "gurupucd : " + this.gurupucd + ", gurupumei : " + this.gurupumei;
	}
}
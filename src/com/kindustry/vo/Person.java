package com.kindustry.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Person")
public class Person {

	private String name;
	private String id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}

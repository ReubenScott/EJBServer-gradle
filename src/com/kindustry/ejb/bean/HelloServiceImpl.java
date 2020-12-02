package com.kindustry.ejb.bean;

import java.util.Date;

import com.kindustry.ejb.service.IHelloService;
import com.kindustry.jpa.model.Gurupu;

@javax.ejb.Stateless
@javax.ws.rs.Path("/organization/")
public class HelloServiceImpl implements IHelloService {

	/**
   * 
   */
	private static final long serialVersionUID = 1L;

	@javax.ws.rs.GET
	@javax.ws.rs.Produces("text/plain")
	public String echo(String msg) {
		return "echo: " + msg + " from ";
	}

	public Date getTime() {
		return new Date();
	}

	public Gurupu updateUser(Gurupu user) {
		System.out.println("-------------- 客户端发送的user为" + user.toString());
		user.setGurupumei("andy2");
		user.setGurupucd(30);
		return user;
	}
}

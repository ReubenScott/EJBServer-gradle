package com.kindustry.webservice;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import com.kindustry.jpa.model.User;

@Stateless
@WebService(serviceName = "soapWebService", targetNamespace = "http://com.kindustry.api/ws/")
@SOAPBinding(style = Style.DOCUMENT)
public class SoapWebService {


  @Resource
  WebServiceContext wsctx;

  // 认证代码
  private boolean isValid() {
    MessageContext mctx = wsctx.getMessageContext();
    // 获取Head
    Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);

    List<String> userList = (List<String>) http_headers.get("Username");
    List<String> passList = (List<String>) http_headers.get("Password");
    String username = "";
    String password = "";
    if (userList != null) {
      username = userList.get(0);
    }
    if (passList != null) {
      password = passList.get(0);
    }

    if (username.equals("test") && password.equals("123")) {
      return true;

    } else {
      return false;
    }
  }

  @WebMethod
  @WebResult(name = "result")
  public String equalTest(@WebParam(name = "first") String fstring, @WebParam(name = "second") String sstring) {
    System.out.println(fstring + " " + sstring);
    if (!isValid()) { return "auth failed"; }
    if (fstring.equals(sstring)) { return "equal ok"; }
    return "equal error";
  }

  @WebMethod
  public User updateUser(User user) {
    System.out.println("-------------- 客户端发送的user为" + user.toString());
    user.setName("andy2");
    user.setAge(30);
    return user;
  }

  @WebMethod
  public String echo(String msg) {
    if (!isValid()) { return "auth failed"; }
    return "echo: " + msg;
  }

  @WebMethod
  public Date getTime() {
    return new Date();
  }
}
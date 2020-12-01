package com.kindustry.config;


/**
 * 'services', '/services', or '/services/*' is all the same. Jersey will change
 * it to be '/services/*' <==> <servlet-mapping>
 * <servlet-name>RestApplication</servlet-name>
 * <url-pattern>/services/*</url-pattern> </servlet-mapping>
 * <p>
 * Here with the @ApplicationPath, it's just like if we configured the servlet
 * mapping in the web.xml
 */
@javax.ws.rs.ApplicationPath("rest")
public class RestApplication extends javax.ws.rs.core.Application {

}

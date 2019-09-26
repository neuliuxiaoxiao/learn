package nio;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

/**
 * @Title Tomcat
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/16 10:15
 **/
public class TomcatTest {

     public static void main(String[] args) throws LifecycleException {
         Tomcat tomcat = new Tomcat();
         Connector connector = new Connector("HTTP/1.1");
         connector.setPort(8080);
         tomcat.setConnector(connector);
         tomcat.start();
         tomcat.getServer().await();
      }

}

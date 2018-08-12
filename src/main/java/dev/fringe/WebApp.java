package dev.fringe;

import java.io.File;

import org.apache.catalina.startup.Tomcat;

/**
 * @author dev-fringe
 * t
 */
public class WebApp{
	public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.setBaseDir(System.getProperty("java.io.tmpdir"));
        tomcat.addWebapp("/", new File(".").getAbsolutePath());
        tomcat.start();
        tomcat.getServer().await();
	}
}

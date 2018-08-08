package dev.fringe;

import java.io.File;

import org.apache.catalina.startup.Tomcat;

/**
 * @author dev-fringe
 */
public class WebApp{
	public static void main(String[] args) throws Exception {
		String webappDirLocation = "";
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(80);
		tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());
		System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());
		tomcat.start();
		tomcat.getServer().await();
	}


}

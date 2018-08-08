package dev.fringe.support;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.logging.log4j.core.config.Configurator;

/**
 * @author dev-fringe log4j2 dynamic load from hostname
 */
public abstract class AbstractAppLogging {
	static {
		try {
			if ("/dev-fringe/".indexOf(InetAddress.getLocalHost().getHostName()) != -1) {
				Configurator.initialize(null, "classpath:infrastructure/log4j2-prd.xml");
			} else {
				Configurator.initialize(null, "classpath:infrastructure/log4j2.xml");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}

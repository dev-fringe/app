package dev.fringe.support;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author dev-frine
 */
@ComponentScan(basePackages = { "dev.fringe.repository", "dev.fringe.service",
		"dev.fringe.controller", "dev.fringe.entity.support" }, basePackageClasses = {MustacheAutoConfiguration.class, WebSocketConfig.class})
@ImportResource(value = { "classpath:/infrastructure/context-application.xml"
		//,"classpath:/infrastructure/context-view.xml" 
		})
@EnableWebMvc
public class WebServiet extends AbstractAppLogging implements WebMvcConfigurer {
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/statics/", "classpath:/views/")
				.setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
	}
}

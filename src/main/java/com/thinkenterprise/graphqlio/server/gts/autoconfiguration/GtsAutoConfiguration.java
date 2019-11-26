package com.thinkenterprise.graphqlio.server.gts.autoconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties(GtsAutoConfiguration.class)
@ConfigurationProperties(prefix = "graphqlio.toolssubscribe")
public class GtsAutoConfiguration {

	
	@Autowired
	private GtsProperties gtsProperties;

}

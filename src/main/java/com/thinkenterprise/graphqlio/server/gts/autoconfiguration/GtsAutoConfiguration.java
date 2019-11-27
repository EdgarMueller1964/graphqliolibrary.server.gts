package com.thinkenterprise.graphqlio.server.gts.autoconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.thinkenterprise.graphqlio.server.gts.resolver.GtsSubscriptionResolver;


@Configuration
@EnableConfigurationProperties(GtsAutoConfiguration.class)
@ConfigurationProperties(prefix = "graphqlio.toolssubscribe")
public class GtsAutoConfiguration {

	
	@Autowired
	private GtsProperties gtsProperties;
	
	
    @Bean 
    @ConditionalOnMissingBean
    public GtsSubscriptionResolver subscriptionResolver() {
    	return new GtsSubscriptionResolver();
    }


}

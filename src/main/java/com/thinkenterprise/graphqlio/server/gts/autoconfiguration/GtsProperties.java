package com.thinkenterprise.graphqlio.server.gts.autoconfiguration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "graphqlio.toolssubscribe")
public class GtsProperties {

}

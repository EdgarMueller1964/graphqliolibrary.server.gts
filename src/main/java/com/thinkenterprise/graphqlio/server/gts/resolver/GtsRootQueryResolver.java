package com.thinkenterprise.graphqlio.server.gts.resolver;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;


@Component
public class GtsRootQueryResolver implements GraphQLQueryResolver {

	
	public GtsRootQueryResolver() {
	}
	
    public GtsSubscription _Subscription() {
        return new GtsSubscription();
    } 

    
}
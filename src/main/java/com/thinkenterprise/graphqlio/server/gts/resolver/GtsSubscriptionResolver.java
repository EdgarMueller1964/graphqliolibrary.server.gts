package com.thinkenterprise.graphqlio.server.gts.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;

import org.springframework.stereotype.Component;

@Component
public class GtsSubscriptionResolver implements GraphQLResolver<GtsSubscription> {

    
    public String subscribe(GtsSubscription subscription) {
        return "UUID";
    }

}
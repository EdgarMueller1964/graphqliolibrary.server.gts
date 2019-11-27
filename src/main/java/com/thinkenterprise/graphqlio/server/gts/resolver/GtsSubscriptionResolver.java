package com.thinkenterprise.graphqlio.server.gts.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.thinkenterprise.graphqlio.server.gts.context.GtsContext;
import com.thinkenterprise.graphqlio.server.gts.tracking.GtsScope;
import com.thinkenterprise.graphqlio.server.gts.tracking.GtsScopeState;

import graphql.schema.DataFetchingEnvironment;

public class GtsSubscriptionResolver implements GraphQLResolver<GtsSubscription> {

    public String subscribe(GtsSubscription subscription, DataFetchingEnvironment env) {
    	GtsContext context = env.getContext();
    	GtsScope scope = context.getScope();
    	scope.setScopeState(GtsScopeState.SUBSCRIBED);
        return scope.getScopeId();
    }
    public String unsubscribe(GtsSubscription subscription, DataFetchingEnvironment env) {    	
    	GtsContext context = env.getContext();
    	GtsScope scope = context.getScope();
    	scope.setScopeState(GtsScopeState.UNSUBSCRIBED);
        return scope.getScopeId();
    }
    public String subscriptions(GtsSubscription subscription, DataFetchingEnvironment env) {    	
        return "UUID";
    }
    public String pause(GtsSubscription subscription, DataFetchingEnvironment env) {    	
    	GtsContext context = env.getContext();
    	GtsScope scope = context.getScope();
    	scope.setScopeState(GtsScopeState.UNSUBSCRIBED);
        return scope.getScopeId();
    }
    public String resume(GtsSubscription subscription, DataFetchingEnvironment env) {    	
    	GtsContext context = env.getContext();
    	GtsScope scope = context.getScope();
    	scope.setScopeState(GtsScopeState.SUBSCRIBED);
        return scope.getScopeId();
    }
}
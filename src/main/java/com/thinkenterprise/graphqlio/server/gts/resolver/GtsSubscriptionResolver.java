package com.thinkenterprise.graphqlio.server.gts.resolver;

import java.util.List;
import java.util.UUID;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.thinkenterprise.graphqlio.server.gts.context.GtsContext;
import com.thinkenterprise.graphqlio.server.gts.tracking.GtsScope;
import com.thinkenterprise.graphqlio.server.gts.tracking.GtsScopeState;

import graphql.schema.DataFetchingEnvironment;

public class GtsSubscriptionResolver implements GraphQLResolver<GtsSubscription> {

    public UUID subscribe(GtsSubscription subscription, DataFetchingEnvironment env) {
    	GtsContext context = env.getContext();
    	GtsScope scope = context.getScope();
    	scope.setScopeState(GtsScopeState.SUBSCRIBED);
        return UUID.fromString(scope.getScopeId());
    }
    
    public void  unsubscribe(GtsSubscription subscription, String sid, DataFetchingEnvironment env) {    	
    	GtsContext context = env.getContext();
    	GtsScope scope = context.getScope();
    	scope.setScopeState(GtsScopeState.UNSUBSCRIBED);
    	return;
    }
    public List<UUID> subscriptions(GtsSubscription subscription, DataFetchingEnvironment env) {    	
        return null;
    }
    public void  pause(GtsSubscription subscription, String sid, DataFetchingEnvironment env) {    	
    	GtsContext context = env.getContext();
    	GtsScope scope = context.getScope();
    	scope.setScopeState(GtsScopeState.UNSUBSCRIBED);
        return;
    }
    public void resume(GtsSubscription subscription, String sid, DataFetchingEnvironment env) {    	
    	GtsContext context = env.getContext();
    	GtsScope scope = context.getScope();
    	scope.setScopeState(GtsScopeState.SUBSCRIBED);
        return;
    }
}
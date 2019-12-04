package com.thinkenterprise.graphqlio.server.gts.resolver;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.thinkenterprise.graphqlio.server.gts.context.GtsContext;
import com.thinkenterprise.graphqlio.server.gts.exceptions.GtsScopeStateException;
import com.thinkenterprise.graphqlio.server.gts.tracking.GtsScope;
import com.thinkenterprise.graphqlio.server.gts.tracking.GtsScopeState;

import graphql.schema.DataFetchingEnvironment;

public class GtsSubscriptionResolver implements GraphQLResolver<GtsSubscription> {

	private final Logger logger = LoggerFactory.getLogger(GtsSubscriptionResolver.class);
		
    public UUID subscribe(GtsSubscription subscription, DataFetchingEnvironment env) {
    	GtsContext context = env.getContext();
    	GtsScope scope = context.getScope();
    	scope.setScopeState(GtsScopeState.SUBSCRIBED);
        return UUID.fromString(scope.getScopeId());
    }
    
    public void  unsubscribe(GtsSubscription subscription, String sid, DataFetchingEnvironment env) {    	
    	GtsContext context = env.getContext();
    	GtsScope scope = context.getScope();
    	
    	if ( !scope.getScopeId().equals(sid) || (scope.getScopeState() != GtsScopeState.SUBSCRIBED &&
    			scope.getScopeState() !=  GtsScopeState.PAUSED )) {
    		logger.warn("GtsSubscriptionResolver.unsubscribe: unexpected scope state");
    		throw new GtsScopeStateException("GtsSubscriptionResolver.unsubscribe: unexpected scope state");
    	}
    	
    	scope.setScopeState(GtsScopeState.UNSUBSCRIBED);
    	return;
    }
    
	// Todo: implementation
    public List<UUID> subscriptions(GtsSubscription subscription, DataFetchingEnvironment env) {
        return null;
    }
    
    public void  pause(GtsSubscription subscription, String sid, DataFetchingEnvironment env) {    	
    	GtsContext context = env.getContext();
    	GtsScope scope = context.getScope();
    	
    	if ( !scope.getScopeId().equals(sid) || scope.getScopeState() != GtsScopeState.SUBSCRIBED ) {
    		logger.warn("GtsSubscriptionResolver.pause: unexpected scope state");
    		throw new GtsScopeStateException("GtsSubscriptionResolver.pause: unexpected scope state");
    	}
    	    	
    	scope.setScopeState(GtsScopeState.PAUSED);
        return;
    }
    
    public void resume(GtsSubscription subscription, String sid, DataFetchingEnvironment env) {    	
    	GtsContext context = env.getContext();
    	GtsScope scope = context.getScope();
    	
    	if ( !scope.getScopeId().equals(sid) || scope.getScopeState() != GtsScopeState.PAUSED ) {
    		logger.warn("GtsSubscriptionResolver.resume: unexpected scope state");
    		throw new GtsScopeStateException("GtsSubscriptionResolver.resume: unexpected scope state");
    	}
    	
    	scope.setScopeState(GtsScopeState.SUBSCRIBED);
        return;
    }
}
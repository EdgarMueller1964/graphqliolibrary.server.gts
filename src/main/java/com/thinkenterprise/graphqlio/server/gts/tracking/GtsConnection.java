package com.thinkenterprise.graphqlio.server.gts.tracking;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.WebSocketSession;

/**
 * WebSocketConnection
 */
public class GtsConnection {

    private String connectionId;

    //// don't need a CopyOnWriteArrayList as scopes are not removed from connection!!!
    //// Question: should we provide a concept of "closing/destroying" scopes?
    private List<GtsScope> scopes = new ArrayList<>();

    public GtsConnection(Builder builder) {
        this.connectionId=builder.connectionId;
    }
    
    public String getConnectionId() {
        return this.connectionId;
    }

    public void addScope(GtsScope scope) {
        this.scopes.add(scope);
    }

    public List<GtsScope> scopes() {
        return this.scopes;
    }
    
    public GtsScope getScopeById(String scopeId) {
        for (GtsScope scope : scopes) {
            if (scope.getScopeId().equals(scopeId)) {
                return scope;
            }
        }
        
        // no scope with scopeId in list
        return null;
    }

    public static Builder builder() {
		return new Builder();
	}

    public static final class Builder {

        private String connectionId;
        private WebSocketSession session;
             
		private Builder() {
        }

		public Builder fromSession(WebSocketSession session) {
            this.session=session;
			return this;
        }
        
		public GtsConnection build() {
            this.connectionId=session.getId();
            return new GtsConnection(this);
		} 

	}



}
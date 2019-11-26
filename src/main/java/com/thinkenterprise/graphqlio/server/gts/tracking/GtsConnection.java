package com.thinkenterprise.graphqlio.server.gts.tracking;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.WebSocketSession;

/**
 * WebSocketConnection
 */
public class GtsConnection {

    private String connectionId;
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
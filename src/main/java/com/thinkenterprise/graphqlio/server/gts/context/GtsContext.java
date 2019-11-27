package com.thinkenterprise.graphqlio.server.gts.context;

import org.springframework.web.socket.WebSocketSession;

import com.thinkenterprise.graphqlio.server.gts.tracking.GtsScope;

import graphql.schema.GraphQLSchema;

public class GtsContext {

	private WebSocketSession webSocketSession;
	private GraphQLSchema graphQLSchema;
	private GtsScope scope;

	private GtsContext(Builder builder) {
		this.webSocketSession=builder.webSocketSession;
		this.graphQLSchema=builder.graphQLSchema;
		this.scope=builder.scope;
	}

	public GtsScope getScope() {
		return this.scope;
	}

	public WebSocketSession getWebSocketSession() {
		return webSocketSession;
	}

	public GraphQLSchema getGraphQLSchema() {
		return this.graphQLSchema;
	}

	public static Builder builder() {
		return new Builder();
	} 

	public static final class Builder {

		private WebSocketSession webSocketSession;
		private GraphQLSchema graphQLSchema;
		private GtsScope scope;

		private Builder() {

		}

		public Builder webSocketSession(WebSocketSession webSocketSession) {
			this.webSocketSession = webSocketSession;
			return this;
		}

		public Builder graphQLSchema(GraphQLSchema graphQLSchema) {
			this.graphQLSchema = graphQLSchema;
			return this;
		}


		public Builder scope(GtsScope scope) {
			this.scope=scope;
			return this;
		}

		public GtsContext build() {
			return new GtsContext(this);
		}

	}

}

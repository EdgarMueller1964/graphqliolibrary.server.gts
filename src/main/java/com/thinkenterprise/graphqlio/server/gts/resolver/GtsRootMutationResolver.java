package com.thinkenterprise.graphqlio.server.gts.resolver;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

@Component
public class GtsRootMutationResolver implements GraphQLMutationResolver {

	public String defaultMutation() {
		return "defaultMutation";
	}
}

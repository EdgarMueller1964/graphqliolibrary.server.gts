package com.thinkenterprise.graphqlio.server.gts;


import java.lang.annotation.*;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(GraphQLIOLibraryGtsConfiguration.class)
@Configuration
public @interface EnableGraphQLIOGtsLibraryModule {
}
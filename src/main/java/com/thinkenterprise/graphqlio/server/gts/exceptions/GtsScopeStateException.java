package com.thinkenterprise.graphqlio.server.gts.exceptions;

import java.util.Collections;
import java.util.List;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphQLException;
import graphql.language.SourceLocation;

public class GtsScopeStateException extends GraphQLException implements GraphQLError {

	private static final long serialVersionUID = 1L;
    private List<SourceLocation> sourceLocations;

	public GtsScopeStateException() {
	}

	public GtsScopeStateException(String message) {
	    super(message);
	}
	
    public GtsScopeStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public GtsScopeStateException(String message, Throwable cause, SourceLocation sourceLocation) {
        super(message, cause);
        this.sourceLocations = Collections.singletonList(sourceLocation);
    }

    public GtsScopeStateException(Throwable cause) {
        super(cause);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return sourceLocations;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.ValidationError;
    }
}


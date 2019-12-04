package com.thinkenterprise.graphqlio.server.gts.exceptions;

import java.util.Collections;
import java.util.List;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphQLException;
import graphql.language.SourceLocation;

public class GtsRecordFormatException extends GraphQLException implements GraphQLError {

	private static final long serialVersionUID = 1L;
    private List<SourceLocation> sourceLocations;

	public GtsRecordFormatException() {
	}

	public GtsRecordFormatException(String message) {
		super(message);
	}

	public GtsRecordFormatException(Throwable cause) {
		super(cause);
	}

	public GtsRecordFormatException(String message, Throwable cause) {
		super(message, cause);
	}

    public GtsRecordFormatException(String message, Throwable cause, SourceLocation sourceLocation) {
        super(message, cause);
        this.sourceLocations = Collections.singletonList(sourceLocation);
    }
	
	@Override
	public List<SourceLocation> getLocations() {
        return sourceLocations;
	}

	@Override
	public ErrorClassification getErrorType() {
        return ErrorType.ValidationError;
	}

}

package com.thinkenterprise.graphqlio.server.gts.exceptions;

public class GtsSubscriptionTypeException extends RuntimeException {

	
	private static final String defaultMessage = 
			"Expect type {Query} for subscription message {subscribe} and type {Mutation} for subscription methods {unsubscribe, pause, resume}";
	
    private static final long serialVersionUID = 1L;

	public GtsSubscriptionTypeException() {
		super(defaultMessage);
	}
	public GtsSubscriptionTypeException(String message) {
		super(message);
	}

}

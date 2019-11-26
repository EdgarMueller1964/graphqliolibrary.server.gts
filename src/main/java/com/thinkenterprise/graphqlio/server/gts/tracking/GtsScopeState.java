package com.thinkenterprise.graphqlio.server.gts.tracking;

public enum GtsScopeState {
	SUBSCRIBED {
		@Override
        public String toString() {
            return "subscribed";
        }
    },
    UNSUBSCRIBED {
        @Override
        public String toString() {
            return "unsubscribed";
        }
    }
    
}

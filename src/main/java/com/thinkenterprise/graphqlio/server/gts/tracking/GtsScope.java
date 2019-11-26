package com.thinkenterprise.graphqlio.server.gts.tracking;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * GtsScope
 */
public class GtsScope {

    private String scopeId;
    private String connectionId = null;			//// default no connection `${this.uuid}:none`  ????
    private String query;
    private String variables;
    private GtsScopeState scopeState;
    private List<GtsRecord> records = new ArrayList<>();

    public GtsScope(Builder builder) {
        this.scopeId=builder.scopeId;
        this.connectionId=builder.connectionId;
        this.scopeState=builder.scopeState;
        this.query=builder.query;
        this.variables=builder.variables;
        this.addRecord(GtsRecord.builder().stringified(query).build());
    }

    public String getScopeId() {
        return this.scopeId;
    }

    public String getConnectionId() {
        return this.connectionId;
    }

    public GtsScopeState getScopeState() {
        return this.scopeState;
    }

    public List<GtsRecord> getRecords() {
        return this.records;
    }
    
    public void addRecord(GtsRecord record) {
        this.records.add(record);
    }

    public List<String> getStringifiedRecords() {
    	List<String> stringifiedRecords = new ArrayList<>(); 
    	this.records.forEach(r -> stringifiedRecords.add(r.stringify()));
    	return stringifiedRecords;
    }
    
    
    
    public static Builder builder() {
		return new Builder();
	}

    public static final class Builder {

        private String scopeId = null;
        private String connectionId = null;		//// default no connection `${this.uuid}:none`  ????
        private GtsScopeState scopeState = GtsScopeState.UNSUBSCRIBED;
        private String query;
        private String variables;
             
		private Builder() {
        }

		public Builder withQuery(String query) {
            this.query=query;
			return this;
        }
        public Builder withVariables(String variables) {
            this.variables=variables;
			return this;
		}

        public Builder withScopeId(String scopeId) {
            this.scopeId=scopeId;
			return this;
		}
        
        public Builder withConnectionId(String connectionId) {
            this.connectionId=connectionId;
			return this;
		}
        
        public Builder withState(GtsScopeState scopeState) {
        	this.scopeState = scopeState;
        	return this;
        }
		public GtsScope build() {
            // Build Process 
			
			if (this.scopeId == null) {
	            // scopeId = .... from query and variables and some other stuff 
				///           this.scopeId = this.variables + this.query;
	            this.scopeId = UUID.randomUUID().toString();
			}
			return new GtsScope(this);
		} 

	}
    
}
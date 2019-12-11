/*******************************************************************************
 * *
 * **  Design and Development by msg Applied Technology Research
 * **  Copyright (c) 2019-2020 msg systems ag (http://www.msg-systems.com/)
 * **  All Rights Reserved.
 * ** 
 * **  Permission is hereby granted, free of charge, to any person obtaining
 * **  a copy of this software and associated documentation files (the
 * **  "Software"), to deal in the Software without restriction, including
 * **  without limitation the rights to use, copy, modify, merge, publish,
 * **  distribute, sublicense, and/or sell copies of the Software, and to
 * **  permit persons to whom the Software is furnished to do so, subject to
 * **  the following conditions:
 * **
 * **  The above copyright notice and this permission notice shall be included
 * **  in all copies or substantial portions of the Software.
 * **
 * **  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * **  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * **  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * **  IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * **  CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * **  TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * **  SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * *
 ******************************************************************************/
package com.thinkenterprise.graphqlio.server.gts.tracking;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.thinkenterprise.graphqlio.server.gts.actuator.GtsCounter;
import com.thinkenterprise.graphqlio.server.gts.tracking.GtsConnection.Builder;

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
	private GtsCounter gtsCounter = null;


    public GtsScope(Builder builder) {
        this.scopeId=builder.scopeId;
        this.connectionId=builder.connectionId;
        this.scopeState=builder.scopeState;
        this.query=builder.query;
        this.variables=builder.variables;
        this.gtsCounter = builder.gtsCounter;        
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

    public void  setScopeState(GtsScopeState scopeState) {
        this.scopeState = scopeState;
    }

    public List<GtsRecord> getRecords() {
        return this.records;
    }
    
    public void addRecord(GtsRecord record) {
    	if (gtsCounter != null)
    		this.gtsCounter.incrementRecordCounter();
        this.records.add(record);
    }

    public List<String> getStringifiedRecords() {
    	List<String> stringifiedRecords = new ArrayList<>(); 
    	this.records.forEach(r -> stringifiedRecords.add(r.stringify()));
    	return stringifiedRecords;
    }
    
    public void onDestroy() {
    	
    	if (gtsCounter != null) {
    		gtsCounter.decrementScopeCounter();
    		gtsCounter.decrementRecordCounter(this.records.size());
    		
    	}
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
        private GtsCounter gtsCounter = null;     
             
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
        
		public Builder withGtsCounter(GtsCounter gtsCounter) {
			this.gtsCounter = gtsCounter;
			return this;
		}
        
		public GtsScope build() {
            // Build Process 
			
			if (this.scopeId == null) {
	            // scopeId = .... from query and variables and some other stuff 
				///           this.scopeId = this.variables + this.query;
	            this.scopeId = UUID.randomUUID().toString();
			}
            if (this.gtsCounter != null) 
            	this.gtsCounter.incrementScopeCounter();

            return new GtsScope(this);
		} 

	}
    
}

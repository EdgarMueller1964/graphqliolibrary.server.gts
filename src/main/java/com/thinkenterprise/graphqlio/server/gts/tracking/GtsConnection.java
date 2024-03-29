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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.WebSocketSession;

import com.thinkenterprise.graphqlio.server.gts.actuator.GtsCounter;
import com.thinkenterprise.graphqlio.server.gts.actuator.GtsCounterNames;

/**
 * WebSocketConnection
 *
 * @author Michael Schäfer
 */

public class GtsConnection {
	
	private GtsCounter gtsCounter = null;
	
    private String connectionId;
    
    //// don't need a CopyOnWriteArrayList as scopes are not removed from connection!!!
    //// Question: should we provide a concept of "closing/destroying" scopes?
    private List<GtsScope> scopes = new ArrayList<>();

    public GtsConnection(Builder builder) {
        this.connectionId=builder.connectionId;
        this.gtsCounter = builder.gtsCounter;
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
   
    public void onClose () {        
    	
        /// "destroy" all scopes for connection if connection is closed
    	this.scopes.forEach(scope -> scope.onDestroy()); 

    	this.scopes.clear();  
        if (gtsCounter != null) {
            gtsCounter.modifyCounter(GtsCounterNames.SCOPES, 1L);        	
        }
    }
    
    public GtsScope getScopeById(String scopeId) {
        for (GtsScope scope : scopes) {
            if (scope.getScopeId().equals(scopeId)) {
                return scope;
            }
        }
        
        // no scope with scopeId in list
        return null;
    }

    public static Builder builder() {
		return new Builder();
	}

    public static final class Builder {

        private String connectionId;
        private WebSocketSession session;
        private GtsCounter gtsCounter = null;     

        private Builder() {
        }

		public Builder fromSession(WebSocketSession session) {
            this.session=session;
			return this;
        }
		
		public Builder withGtsCounter(GtsCounter gtsCounter) {
			this.gtsCounter = gtsCounter;
			return this;
		}
        
		public GtsConnection build() {
            this.connectionId=session.getId();
            if (this.gtsCounter != null) 
            	this.gtsCounter.modifyCounter(GtsCounterNames.CONNECTIONS, 1L);;
            return new GtsConnection(this);
		} 

	}



}

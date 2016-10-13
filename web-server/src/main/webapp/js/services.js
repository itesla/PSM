'use strict';

/* services */

var psmMessagesUrl = (window.location.protocol === 'http:' ? 'ws:' : 'wss:') + '//' + window.location.host + '/psm/messages';

var psmResourcesUrl = window.location.protocol + '//' + window.location.host+ '/psm/resources';

var services = angular.module('psmServices', []);

services.factory('PSMWorkflowService', ['$rootScope', '$http', '$log',
	function($rootScope, $http, $log) {
	
	    var wsUrl = psmMessagesUrl + '/psm/workflow';
	
	    var ws = new WebSocket(wsUrl);
	
	    ws.onopen = function(event) {
	    	$log.debug('WebSocket \'' + wsUrl + '\' opened');
	
	        $http.post(psmResourcesUrl + '/psm/workflow/notifyListeners')
	                   .error(function(data) { $log.error(data); });
	    };
	
	    ws.onclose = function(event) {
	        $log.debug('WebSocket \'' + wsUrl + '\' closed');
	    };
	    
	    var catalogs;
	    ws.onmessage = function(event) 
	    {
	    	$log.debug('onMessage ' + event.data);
	        var msg = angular.fromJson(event.data);
	        $log.debug('MessageType ' + msg.type);
	        switch (msg.type)
	        {
		       	case "session":
		             $rootScope.$broadcast('connection', [msg.body]);
		             break;

	       		case "connection":
		             $rootScope.$broadcast('connection', [msg.body]);
		             break;
		             
            	case "status":           		
            		$rootScope.$broadcast('status', [msg.body]);
            		break;

                case "workflows":
                	$log.debug('workflows message received ');
                  	$rootScope.$broadcast('workflows', [msg.body]);
            		break;

                default:
	                $log.error("Unknown message type " + msg.type);
	        }
	    };
	    
	    return {
	    	
	    	close: function() { ws.close();},
	    	        	
	    	create: function() { 
	    		return $http.get(psmResourcesUrl + '/psm/workflow/create');
	    	},
	    	
		    start: function(parameters) {
	            $http.post(psmResourcesUrl + '/psm/workflow/start', parameters)
	                .error(function(data) { $log.error(data); });
	        },
	
		    cancel: function(workflowId) {
	            $http.post(psmResourcesUrl + '/psm/workflow/cancel', { id : workflowId})
	                .error(function(data) { $log.error(data); });
	        },
	
	        stop: function(workflowId) {
	            $http.post(psmResourcesUrl + '/psm/workflow/stop', { id : workflowId})
	                .error(function(data) { $log.error(data); });
	        },
	        
	        getNameCatalogs: function() { 
	        	return $http.get(psmResourcesUrl + '/psm/workflow/namecatalogs');
	        },
	
	        getNameCases: function(catalogName) { 
	        	return $http.get(psmResourcesUrl + '/psm/workflow/namecase/' + catalogName);
	        },

	        getNameDDRs: function(catalogName) { 
		       	return $http.get(psmResourcesUrl + '/psm/workflow/nameddrs/' + catalogName);
		    },

		    getCatalogs: function() { 
	        	return $http.get(psmResourcesUrl + '/psm/workflow/catalogs');
	        },
	
	        getCases: function(catalogName) { 
	        	return $http.get(psmResourcesUrl + '/psm/workflow/case/' + catalogName);
	        },

	        getDDRs: function(catalogName) { 
		       	return $http.get(psmResourcesUrl + '/psm/workflow/ddrs/' + catalogName);
		    },
		       	
		    getWorkflows: function() { 
		       	return $http.get(psmResourcesUrl + '/psm/workflow/workflows');
		    },
		       	
		    getWorkflowStatus: function(wfId) { 
		       	return $http.get(psmResourcesUrl + '/psm/workflow/status/'+wfId);
		    },

		    getWorkflowData: function(wfId) { 
		       	return $http.get(psmResourcesUrl + '/psm/workflow/results/'+wfId);
		    },
		    
		    isShinyToolsVisible: function() {
		    	return $http.get(psmResourcesUrl + '/psm/workflow/shinyTools');
		    }
	    };
	}]);

services.factory('UserService', ['$rootScope', '$http', '$log',
	function($rootScope, $http, $log) {
		return {
			getCurrentUser: function() {
				return $http.get(psmResourcesUrl + '/psm/workflow/currentUser');
			},
			logout: function() {
				$http.post(psmResourcesUrl + '/psm/workflow/logout');
			}
		};
                               	
	}]);

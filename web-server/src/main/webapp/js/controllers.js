'use strict';

/* Controllers */

var controllers = angular.module('psmControllers', ['ngCookies','ngSanitize']);

controllers.controller('NavController', ['UserService', 'PSMWorkflowService', '$scope', '$http', '$log', '$location', '$window', '$route', '$cookieStore',
    function(UserService, PSMWorkflowService, $scope, $http, $log, $location, $window, $route, $cookieStore) {
	    $scope.isActive = function (viewLocation) {
	        return $location.path().indexOf(viewLocation) != -1;
	    };
	
	    UserService.getCurrentUser()
	    	.success(function(data) { 
	    		$scope.currentUser = data;
	    		$scope.session = true;})
	        .error(function(data) { 
	        	$scope.currentUser=null;
	        	$scope.session = false;});
	    
	    $scope.logout = function() { 
		    UserService.logout();    
		    PSMWorkflowService.close();
		    $scope.currentUser=null;
		    $cookieStore.remove("JSESSIONID");
		    window.top.location.href='/psm/loggedOut.html';
		    $location.path('/psm/loggedOut.html');
		    $route.reload();
	    };

	    $scope.$on('timeoutLogout', function(events, args) {
	    	$scope.logout();
	    })
	}]);

controllers.controller('PSMCatalogCtrl', ['PSMWorkflowService', '$scope', '$log', '$routeParams',
	function(PSMWorkflowService, $scope, $log, $routeParams) {

		$scope.catalogName= $routeParams.catalogName;
	
        if(angular.isUndefined($scope.catalogName)) {
			$scope.catalogName= 'Reference cases';
			$scope.remarkedCatalog= 'Reference cases';
	    }

		$scope.remarkCatalog = function (catalog) {
	    	$scope.remarkedCatalog = catalog;
	    	$scope.getCatalogItems($scope.remarkedCatalog);
	    }

        $scope.remarkCase = function (remarkedCase) {
        	$scope.remarkedCase= remarkedCase;
        }

        $scope.remarkDDR = function (ddr) {
        	$scope.remarkedDDR= ddr;
        }

	    PSMWorkflowService.getCatalogs()
	        .success(function(data) {
        		if (typeof data === "string")
        			$scope.$emit('timeoutLogout', null);
        		else if(data != null && data != "")
        			$scope.catalogs=angular.fromJson(data).body.catalogs;})
	        .error(function(data) { $log.error(data); });

	    $scope.getCatalogItems = function (catalogName) {
	       	PSMWorkflowService.getCases(catalogName)
		        .success(function(data) {
		        	if (typeof data === "string")
	        			$scope.$emit('timeoutLogout', null);
	        		else if(data != null && data != "")
	        			$scope.cases=angular.fromJson(data).body.cases;})
		        .error(function(data) { $log.error(data); });

	       	PSMWorkflowService.getDDRs(catalogName)
		        .success(function(data) {
		        	if (typeof data === "string")
	        			$scope.$emit('timeoutLogout', null);
	        		else if(data != null && data != "")
	        			$scope.ddrs=angular.fromJson(data).body.ddrs;})
		        .error(function(data) { $log.error(data); });
	    }
	    
       	$scope.$on('connection', function(event, args) {
            $scope.$apply(function() {
            	$log.debug("connection : "+args[0]);
            	$scope.connected = args[0];
            		
            	if ($scope.connected) {
            	    $scope.getCatalogItems($scope.catalogName);
            	}
            });
        });

	    $scope.getCatalogItems($scope.remarkedCatalog);
	}]);

controllers.controller('PSMWorkflowsCtrl', ['PSMWorkflowService', '$scope', '$log', '$routeParams',
	function(PSMWorkflowService, $scope, $log, $routeParams) {

	
		$scope.create = function() { 
	    	PSMWorkflowService.create()
	    		.success(function(data) { 
	    			$log.info(data); 
	    			$scope.parameters = angular.fromJson(data).body;
	    			$scope.currentWorkflow = $scope.parameters.created;
	            	$scope.parameters.catalogName = 'Reference cases';
	    			$scope.catalogCandidate = 'Reference cases';
	    		    $scope.getCatalogNameItems($scope.parameters.catalogName);})
	    		.error(function(data) { $scope.currentWorkflow = null });
	    };
	    
	    $scope.start = function() { 
	  	    PSMWorkflowService.start($scope.parameters);
	  	    $scope.currentWorkflow = null;  
	    };
	  	
	    $scope.cancel = function() { 
	  	    PSMWorkflowService.cancel($scope.currentWorkflow);
	  	    $scope.currentWorkflow = null;  
	    };
	  	
	    $scope.selectLoadflow = function (selectedLoadflow) {
	    	$log.info(selectedLoadflow);
	    	$scope.parameters.engineLoadflow = parseInt(selectedLoadflow);
	    }

	    $scope.setMainConnectedComponentOnly = function (mainConnectedComponentOnly) {
	    	$log.info(mainConnectedComponentOnly);
	    	$scope.parameters.mainConnectedComponentOnly = mainConnectedComponentOnly;
	    }
    
	    $scope.selectDS = function (selectedDS) {
	    	$log.info(selectedDS);
	    	$scope.parameters.engineDS = parseInt(selectedDS);
	    }
	    
	    $scope.addEvent = function (event) {
	    	if (event == null) return;
	    	
	    	$log.info(event);
	    	var e = {};
	    	e.action = event.action;
	    	e.element = event.element
	    	if (e.action==0) {
	    		e.actionText='OPEN';
	    	}
	    	else if (event.action==1) {
	    		e.actionText='CLOSE';
	    	}
	    	else if (event.action==2) {
   				e.actionText='MODIFY';
	    	}
	    	event = { action: "" , element: ""};
	    	$scope.parameters.events.push(e);
	    }
	    
	    $scope.removeEvent = function (events) {
	    	if (events == null) return;
	    	
	    	events.forEach( function(event) {
	    		var id = $.inArray(event, $scope.parameters.events); 
	    		$scope.parameters.events.splice(id,1);
	    	});
	    }

	    $scope.selectCatalog = function () {
	    	$scope.parameters.catalogName = $scope.catalogCandidate;
        	
        	$scope.getCatalogNameItems($scope.parameters.catalogName);
        }

        $scope.selectCatalogCandidate = function (catalog) {
        	$log.info("selected catalog " + catalog);
        	$scope.catalogCandidate = catalog;
        }

        $scope.selectCase = function () {
        	$scope.caseName = $scope.caseCandidate;
	    	$scope.parameters.caseName = $scope.caseCandidate;
        }

        $scope.selectCaseCandidate = function (caseName) {
        	$log.info("selected case " + caseName);
        	$scope.caseCandidate = caseName;
        }

        $scope.selectDDR = function () {
        	$scope.ddrName = $scope.ddrCandidate;
	    	$scope.parameters.ddrName = $scope.ddrCandidate;
        }

        $scope.selectDDRCandidate = function (ddr) {
        	$log.info("selected ddr " + ddr);
        	$scope.ddrCandidate = ddr;
        }

        PSMWorkflowService.getNameCatalogs()
	        .success(function(data) {
	        	if (typeof data === "string")
        			$scope.$emit('timeoutLogout', null);
        		else if(data != null && data != "") 
        			$scope.namecatalogs=angular.fromJson(data).body.list;})
	        .error(function(data) { $log.error(data); });

        $scope.getCatalogNameItems = function (catalogName) {
		   	PSMWorkflowService.getNameCases(catalogName)
		        .success(function(data) {
		        	if (typeof data === "string")
	        			$scope.$emit('timeoutLogout', null);
	        		else if(data != null && data != "")
        				$scope.namecases=angular.fromJson(data).body.list;})
		        .error(function(data) { $log.error(data); });
	
		   	PSMWorkflowService.getNameDDRs(catalogName)
		        .success(function(data) {
		        	if (typeof data === "string")
	        			$scope.$emit('timeoutLogout', null);
	        		else if(data != null && data != "")
						 $scope.nameddrs=angular.fromJson(data).body.list;})
		        .error(function(data) { $log.error(data); });
	    }
	    
	    PSMWorkflowService.getWorkflows()
			.success(function(data){
				if (typeof data === "string")
        			$scope.$emit('timeoutLogout', null);
        		else if(data != null && data != "") {
					$scope.workflows=angular.fromJson(data).body;
					if (Object.keys($scope.workflows).length <= 0)
						$scope.workflows = null;
				}})
			.error(function(data){ 
				$scope.workflows=null;});
    
       	$scope.$on('connection', function(event, args) {
            $scope.$apply(function() {
            	$log.debug("connection : "+args[0]);
            	$scope.connected = args[0];
            });
        });

    	$scope.$on('status', function(event, args) {
            $scope.$apply(function() {
            	
            	$scope.workflows[args[0].workflowId].status=args[0].status;
            	if(args[0].workflowId == $scope.activeWorkflowId)
            		$scope.workflowStatus = args[0].status;
            });
        });
    	
    	$scope.$on('workflows', function(event, args) {
    	    $log.debug(" workflows ");
    	    $scope.$apply(function() {
    	    		
    	    	$scope.workflows = args[0];
    	    });
    	});
	}]);

controllers.controller('PSMWorkflowCtrl', ['PSMWorkflowService', '$scope', '$log', '$routeParams',
	function(PSMWorkflowService, $scope, $log, $routeParams) {
	
		$scope.activeWorkflowId= $routeParams.wfId;
		
		PSMWorkflowService.isShinyToolsVisible()
			.success(function(data) {
				$scope.showShinyTools = angular.fromJson(data);
			})
			.error(function(data) {
				$scope.showShinyTools = false;
			});
		
	    $scope.stop = function() { 
		    PSMWorkflowService.stop($scope.activeWorkflowId);
	  	    $scope.currentWorkflow = null;  
	    };
	    
        if(!angular.isUndefined($scope.activeWorkflowId))
        {
        	PSMWorkflowService.getWorkflowStatus($scope.activeWorkflowId)
        		.success(function(data) { $scope.workflowStatus = parseInt(data); })
                .error(function(data) { $log.error(data); });

        	PSMWorkflowService.getWorkflowData($scope.activeWorkflowId)
	    		.success(function(data) { 
	    			var results = angular.fromJson(data).body.results;
	    			d3Svg.setData(results);
	    		})
	            .error(function(data) { $log.error(data); });
        }
	    
    	$scope.$on('status', function(event, args) {
            $scope.$apply(function() {
            	
            	$scope.workflows[args[0].workflowId].status=args[0].status;
            	if(args[0].workflowId == $scope.activeWorkflowId)
            		$scope.workflowStatus = args[0].status;
            });
        });
    	
    	d3Svg.lineUpMarey();
}]);

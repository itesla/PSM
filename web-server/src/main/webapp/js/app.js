'use strict';

/* App Module */

var app = angular.module('psmApp', [
    'ngRoute',
    'psmControllers',
    'psmServices'
]);

app.config(['$routeProvider',
    function($routeProvider) {
	    $routeProvider.
		    when('/cases', {
		        templateUrl: 'partials/cases.html',
		        controller: 'PSMCatalogCtrl'
		    }).
		    when('/ddr', {
		        templateUrl: 'partials/ddr.html',
		        controller: 'PSMCatalogCtrl'
		    }).
		    when('/workflows', {
		        templateUrl: 'partials/workflows.html',
		        controller: 'PSMWorkflowsCtrl'
		    }).
		    when('/workflow', {
		        templateUrl: 'partials/workflow.html',
		        controller: 'PSMWorkflowCtrl'
		    }).
		    otherwise({
		        redirectTo: '/workflows'
		    });
    }]);


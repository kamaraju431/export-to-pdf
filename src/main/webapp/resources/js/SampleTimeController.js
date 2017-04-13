var app = angular.module('myApp', [ 'ui.bootstrap' ]);

app.controller('UserCtrl', function($scope, $http, $uibModal) {
	/*
	 * The $http.get method is used to retrieve information from the given
	   server using a given URI. 
	 */
	$http.get('list4?').then(function(response) {
		$scope.names = response.data;
	});
	
});



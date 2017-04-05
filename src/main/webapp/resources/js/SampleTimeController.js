var app = angular.module('myApp', [ 'ui.bootstrap' ]);

app.controller('UserCtrl', function($scope, $http, $uibModal) {
	$http.get('list4?').then(function(response) {
		$scope.names = response.data;
	});
	
});



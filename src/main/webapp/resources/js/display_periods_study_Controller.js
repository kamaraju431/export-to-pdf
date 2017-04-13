var app = angular.module('periodsStudyApp', [], function($locationProvider) {
	$locationProvider.html5Mode(true);
});

app.controller('periodsStudyController', function($scope, $http, $log, $window,
		$location) {
	// $http.post('/add_study').then(function(response){
	// });

	/*
	 * $location service provides many useful methods for parsing and changing
	 * the URL in the browser's address bar. One of these methods, search(),
	 * provides a getter and setter for query string parameters. We will use
	 * this power to update the address bar in real time.
	 */

	var studyId = $location.search().id;
	/*
	 * The $http.get method is used to retrieve information from the given
	   server using a given URI. 
	 */

	    $http.get('/Sample/study?id=' + studyId).then(function(response) {
		console.log('VEDHA response', response.data);
		$scope.study = response.data;
		$scope.periodsCount = $scope.study.periods.length;

		/*
		 * This is where the $watch function comes in. $watch provides us with a
		 * way to keep calculated values up to date when the values that they
		 * depend on change.
		 */

		$scope.$watch("periodsCount", function(newValue, oldValue) {
			var periodsLength = $scope.study.periods.length;
			var newLength = parseInt(newValue);

			if (periodsLength === newLength) {
				return;
			}
			if (periodsLength < newLength) {
				for (var i = 0; i < (newLength - periodsLength); i++)
					$scope.study.periods.push("");
			} else {
				for (var i = 0; i < (periodsLength - newLength); i++)
					$scope.study.periods.pop("");
			}
		});

	});

});

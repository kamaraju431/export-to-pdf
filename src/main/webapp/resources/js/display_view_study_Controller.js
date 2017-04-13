var app = angular.module('viewStudyApp', [], function($locationProvider) {
	$locationProvider.html5Mode(true);
});

app.controller('viewStudyController', function($scope, $http, $location) {

	/*
	 * $location service provides many useful methods for parsing and changing
	 * the URL in the browser's address bar. One of these methods, search(),
	 * provides a getter and setter for query string parameters. We will use
	 * this power to update the address bar in real time.
	 */

	var studyId = $location.search().id;

	$http.get('/Sample/study?id=' + studyId).then(
			function(response) {

				console.log('VEDHA response', response.data);

				$scope.study = response.data;

				$scope.periodsCount = $scope.study.periods;

				$scope.periodsArr = new Array($scope.study.periods);
				console.log('VEDHA periods count', $scope.periodsCount,
						$scope.periodsArr, $scope.periodsArr);
				$scope.currentPeriod = 1;
				$scope.onClick = function(currentPeriod) {
					console.log('hello', currentPeriod)
					$scope.currentPeriod = currentPeriod;
					$scope.study = response.data;
					console.log('Responce in current page', currentPeriod,
							$scope.study);
				};
			});

});

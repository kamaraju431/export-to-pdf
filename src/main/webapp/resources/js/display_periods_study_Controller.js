var app = angular.module('periodsStudyApp', [], function($locationProvider) {
    $locationProvider.html5Mode(true);
});

app.controller('periodsStudyController', function($scope, $http, $log, $window, $location) {
	// $http.post('/add_study').then(function(response){
	// });
	var studyId = $location.search().id;
	$http.get('/Sample/study?id=' + studyId).then(function(response) {
		console.log('VEDHA response', response.data);
		$scope.study = response.data;
		$scope.periodsCount = $scope.study.periods.length;
	
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

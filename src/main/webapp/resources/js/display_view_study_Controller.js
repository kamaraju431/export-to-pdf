var app = angular.module('viewStudyApp', [], function($locationProvider) {
	$locationProvider.html5Mode(true);
});

app.controller('viewStudyController', function($scope, $http, $location) {

	var studyId = $location.search().id;
	var currentPeriod = parseInt($location.search().period);
	
	$http.get('/aizantit/study?id=' + studyId).then(function(response) {
		
		console.log('VEDHA response', response.data);
	
		$scope.study = response.data;
		
		$scope.periodsCount = $scope.study.periods;
		
		$scope.periodsArr = new Array($scope.study.periods);
		console.log('VEDHA periods count', $scope.periodsCount, $scope.periodsArr, $scope.periodsArr);
		$scope.currentPeriod = currentPeriod || 1;
	});
		
	$scope.onClick = function(currentPeriod) {
		console.log('hello',currentPeriod)
		$scope.currentPeriod=currentPeriod;	
	};
	
	$scope.hasStarted = function(studyVolunteer) {
		var samplesForPeriod = studyVolunteer.bloodSampleCollection.filter(function(sample) {
			return sample.period === $scope.currentPeriod;
		});
		return !!samplesForPeriod.length;
	}
});
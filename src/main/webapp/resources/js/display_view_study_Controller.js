var app = angular.module('viewStudyApp', [], function($locationProvider) {
	$locationProvider.html5Mode(true);
});

app.controller('viewStudyController', function($scope, $http, $location) {

	var studyId = $location.search().id;

	$http.get('/Sample/study?id=' + studyId).then(function(response) {
		console.log('VEDHA response', response.data);
		$scope.study = response.data;
		$scope.periodsCount = $scope.study.periods;
		$scope.periodsArr = new Array($scope.study.periods);
		console.log('VEDHA periods count', $scope.periodsCount, $scope.periodsArr, $scope.periodsArr);
	});

});
 

var app = angular.module('viewStudyApp', [], function($locationProvider) {
    $locationProvider.html5Mode(true);
});

app.controller('viewStudyController', function($scope, $http, $log, $window, $location) {
	// $http.post('/add_study').then(function(response){
	// });
	var studyId = $location.search().id;
	$http.get('/Sample/study?id=' + studyId).then(function(response) {
		console.log('VEDHA response', response.data);
		$scope.study = response.data;

	});

});

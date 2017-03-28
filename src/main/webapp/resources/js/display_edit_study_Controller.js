var app = angular.module('editStudyApp', [], function($locationProvider) {
    $locationProvider.html5Mode(true);
});

app.controller('editStudyController', function($scope, $http, $log, $window, $location) {
	// $http.post('/add_study').then(function(response){
	// });
	var studyId = $location.search().id;
	$http.get('/Sample/study?id=' + studyId).then(function(response) {
		console.log('VEDHA response', response.data);
		$scope.study = response.data;
		$scope.sampleCount = $scope.study.sample.length;
		$scope.volunteerCount = $scope.study.studyVolunteers.length;
		$scope.$watch("sampleCount", function(newValue, oldValue) {
			var sampleLength = $scope.study.sample.length;
			var newLength = parseInt(newValue);

			if (sampleLength === newLength) {
				return;
			}
			if (sampleLength < newLength) {
				for (var i = 0; i < (newLength - sampleLength); i++)
					$scope.study.sample.push("");
			} else {
				for (var i = 0; i < (sampleLength - newLength); i++)
					$scope.study.sample.pop("");
			}
		});
		$scope.$watch("volunteerCount", function(newValue, oldValue) {
			var sampleLength = $scope.study.studyVolunteers.length;
			var newLength = parseInt(newValue);

			if (sampleLength === newLength) {
				return;
			}
			if (sampleLength < newLength) {
				for (var i = 0; i < (newLength - sampleLength); i++)
					$scope.study.studyVolunteers.push({
						volunteerId : 'V ID ' + (sampleLength + i),
						volunteerName : 'V NAME' + (sampleLength + i)
					});
			} else {
				for (var i = 0; i < (sampleLength - newLength); i++)
					$scope.study.studyVolunteers.pop("");
			}
		});
	});



	$scope.editStudy = function() {
		$http.post('/Sample/store_study', $scope.study).then(
				function(result)

				{
					console.log('FINISHHHHEEDDDD');
					var url = "http://" + $window.location.host
							+ "/Sample/display_study";
					$log.log(url);
					$window.location.href = url;

				});

	};

});

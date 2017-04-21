var app = angular.module('editStudyApp', ['aizant.directives'], function($locationProvider) {
    $locationProvider.html5Mode(true);
});

app.controller('editStudyController', function($scope, $http, $log, $window, $location) {
	var studyId = $location.search().id;
	$http.get('/aizantit/study?id=' + studyId).then(function(response) {
		console.log('VEDHA response', response.data);
		$scope.study = response.data;
		$scope.sampleCount = $scope.study.sampleTime.length;
		$scope.volunteerCount = $scope.study.studyVolunteers.length;
		
		/*
		 * This is where the $watch function comes in. $watch provides us with a
		 * way to keep calculated values up to date when the values that they
		 * depend on change.
		 */
		
		$scope.$watch("sampleCount", function(newValue, oldValue) {
			var sampleLength = $scope.study.sampleTime.length;
			var newLength = parseInt(newValue);

			if (sampleLength === newLength) {
				return;
			}
			if (sampleLength < newLength) {
				for (var i = 0; i < (newLength - sampleLength); i++)
					$scope.study.sampleTime.push({timePoint:''+(sampleLength+i)});
			} else {
				for (var i = 0; i < (sampleLength - newLength); i++)
					$scope.study.sampleTime.pop("");
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
						registerNumber : 'V ID ' + (sampleLength + i + 1)
				
					});
			} else {
				for (var i = 0; i < (sampleLength - newLength); i++) {
					$scope.study.studyVolunteers.pop("");					
				}
			}
		});
	});



	$scope.editStudy = function() {
		$http.post('/aizantit/store_study', $scope.study).then(
				function(result) {
					console.log('FINISHHHHEEDDDD');
					var url = "http://" + $window.location.host
							+ "/aizantit/display_study";
					$log.log(url);
					$window.location.href = url;

				});

	};

});

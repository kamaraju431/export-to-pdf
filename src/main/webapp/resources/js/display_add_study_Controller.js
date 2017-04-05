var app = angular.module('myApp', []);

app.controller('addStudy', function($scope, $http, $log, $window) {
	// $http.post('/add_study').then(function(response){
	// });
	$scope.sampleCount = "1";
	$scope.volunteerCount = "1";
	$scope.study = {
		name : ' ',
		aliquot : 0,
		sampleCollectionSize_in_ml : 0,
		periods : "1",
		clientStudyId : ' ',
		date : '',
		studyVolunteers : [ {
			volunteerId : ' ',
			volunteerName : ' ',

		}, {
			volunteerId : 'V ID ',
			volunteerName : 'V NAME'
		} ],
		sampleTime:[{
			timePoint:0.0,
			
		},{
			timePoint:''
		}],
		
	};
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
					volunteerId : 'V ID ' + (sampleLength + i),
					volunteerName : 'V NAME' + (sampleLength + i)
				});
		} else {
			for (var i = 0; i < (sampleLength - newLength); i++)
				$scope.study.studyVolunteers.pop("");
		}
	});

	$scope.addStudy = function() {
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

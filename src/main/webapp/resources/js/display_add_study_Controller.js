var app = angular.module('myApp', ['aizant.directives']);

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
		anticoagulant:'',
		dosingTime:'',
		studyType:'selectType',
		studyVolunteers : [{
			registerNumber : 'V ID 1',
		} ],
		sampleTime:[{
			timePoint:0.0,
		}],
	};
	
	/*
	 This is where the $watch function comes in.
	$watch provides us with a way to keep calculated 
	values up to date when the values that they depend on change. 
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
			for (var i = 0; i < (sampleLength - newLength); i++)
				$scope.study.studyVolunteers.pop("");
		}
	});
	

	$scope.addStudy = function() {
		$http.post('/aizantit/store_study', $scope.study).then(
				function(result)

				{
					var url = "http://" + $window.location.host
							+ "/aizantit/display_study";
					$log.log(url);
					$window.location.href = url;
				});

	};

});

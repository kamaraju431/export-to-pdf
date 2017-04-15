var app = angular.module('myApp', [], function($locationProvider) {
	$locationProvider.html5Mode(true);
});

app.controller('BloodSampleCollectionCtrl', function($scope, $http, $location) {
	var id = $location.search().id;
	var currentPeriod = parseInt($location.search().period);
	$scope.bloodSampleCollection = {
		p1 : [],
		p2 : [],
		p3 : [],
		p4 : []
	};

	$scope.onClick = function(period) {

		$scope.selectedPeriod = period;
		if (period === 1) {
			$scope.currentSamples = $scope.bloodSampleCollection.p1;
	
		} else if (period === 2) {
			$scope.currentSamples = $scope.bloodSampleCollection.p2;
		} else if (period === 3) {
			$scope.currentSamples = $scope.bloodSampleCollection.p3;
		} else {
			$scope.currentSamples = $scope.bloodSampleCollection.p4;
		}
	}
	
	$http.get('/aizantit/study_volunteer?id=' + id).then(function(response) {
		console.log('STUDY VOLUNTEER RESPONSE', response.data);
		$scope.response = response.data;
		$scope.studyVolunteer = response.data.studyVolunteer;
		$scope.bloodSample = $scope.studyVolunteer.bloodSampleCollection || [] ;
		
		for (i = 0; i < $scope.bloodSample.length; i++) {

			if ($scope.bloodSample[i].period === 1) {
				$scope.bloodSampleCollection.p1.push($scope.bloodSample[i]);
				console.log('data in p1', $scope.bloodSampleCollection.p1);

			} else if ($scope.bloodSample[i].period === 2) {
				$scope.bloodSampleCollection.p2.push($scope.bloodSample[i]);

			} else if ($scope.bloodSample[i].period === 3) {
				$scope.bloodSampleCollection.p3.push($scope.bloodSample[i]);

			} else {
				$scope.bloodSampleCollection.p4.push($scope.bloodSample[i]);
			}
		}
		$scope.onClick(currentPeriod || 1);
		console.log('data in p1', $scope.currentSamples);		
	});
});
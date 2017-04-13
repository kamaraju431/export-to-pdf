var app = angular.module('myApp', []);
app.controller('BloodSampleCollectionCtrl', function($scope, $http,$location){
	$scope.bloodSampleCollection = {
	  
		p1 : [],
		p2 : [],
		p3 : [],
		p4 : []
	};
	$http.get('list4').then(function(response) {
		$scope.bloodSample = response.data;

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

		$scope.currentSamples = $scope.bloodSampleCollection.p1;
	

	});
	$scope.onClick = function(period) {

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

});

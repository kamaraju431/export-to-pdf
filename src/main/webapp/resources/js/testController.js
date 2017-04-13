var app = angular.module('myApp', []);

app.controller('add_BloodSampleCollection', function($scope, $http, $log, $window) {
	// $http.post('/add_study').then(function(response){
	// });

	$scope.study_Volunteer= {
			volunteerId : ' ',
			volunteerName : '',
			bloodSampleCollection : [{
				date : ' ',
				time : '',
				period:'',
				scanTime:'',
				volunteerId:'',
				comments:'' 
			}, 
			],
	
	};

	$scope.add_BloodSampleCollection = function() {
		$http.post('/Sample/store_studyVolunteer_blood', $scope.study_Volunteer).then(function(result)
				{
					var url = "http://" + $window.location.host
							+ "/Sample/display_study";
					$log.log(url);
					$window.location.href = url;

				});
	};

});

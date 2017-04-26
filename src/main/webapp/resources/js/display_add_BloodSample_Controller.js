var app = angular.module('myApp', ['aizant.directives', 'ngMaterial','ngMessages']);
app.config(function($mdDateLocaleProvider) {
	$mdDateLocaleProvider.formatDate = function(date) {
		return moment(date).format('DD/MMM/YYYY');
	};
});

app.controller('addStudy', function($scope, $http, $log, $window) {	


	$scope.bloodSampleCollection = {
			date : ' ',
			time : 0,
			period : 0,
			aliquot : 1,
			scanTime : ' ',
			comments : '',
			registerNumber:'',
		};
	
	/*
	 This is where the $watch function comes in.
	$watch provides us with a way to keep calculated 
	values up to date when the values that they depend on change. 
	*/
	
	
	
	

	$scope.addBloodSample = function() {
		$scope.bloodSampleCollection.date = moment($scope.bloodSampleCollection.date).format('DD/MMM/YYYY')
		$http.post('/aizantit/store_BloodSampleCollection', $scope.bloodSampleCollection).then(
				function(result)

				{
					var url = "http://" + $window.location.host
							+ "/aizantit/display_study";
					$log.log(url);
					$window.location.href = url;
				});
	 

	};

});

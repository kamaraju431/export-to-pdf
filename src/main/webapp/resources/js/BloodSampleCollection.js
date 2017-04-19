var app = angular.module('myApp', ['ui.bootstrap'], function($locationProvider) {
	$locationProvider.html5Mode(true);
});

var modalTemplate = 
		"<div class='modal-header'>"
		+ "<button ng-click='x()'type='button' class='close' data-dismiss='modal'>x</button>"
		+ "<h4 class='modal-title'>Warning!</h4>"
		+ "</div>"
		+ "<div class='modal-body'>Are you sure You want to Delete{{x.id}}</div>"
		+ "<div class='modal-footer'>"
		+ "<button ng-click='cancel()' type='button' class='btn btn-default' data-dismiss='modal'>Close</button>"
		+

		"<button ng-click='delete()' type='button' class='btn btn-danger' data-dismiss='modal'>Delete</button>	</div>";

var viewTemplate = "<div class='modal-content'>"
	+"<div class='modal-header'>"
	+ "<button ng-click='x()'type='button' class='close' data-dismiss='modal'>x</button>"
	+ "<h4 class='modal-title'>Aliquots</h4>"
	+ "</div>"
	+ "	<div class='modal-body'>"
	+"<table class='table table-hover table-bordered'>"
	+"<tr>"
	+"<th>Aliquot</th>"
    +"<th>Date</th>"
	+"<th>Time</th>"
	+"<th>Period</th>"
	+"<th>Scan Time</th>"

    +"</tr>" +"<tr ng-repeat='x in aliquots'>" +"<td>{{x.aliquot}}</td>" +"<td>{{x.date}}</td>" +"<td>{{x.time}}</td>" +"<td>{{x.period}}</td>" +"<td>{{x.scanTime}}</td>"
    +"</tr>"
 
	+"</table>"

	+"</div>"
	+ "<div class='modal-footer'>"
	+ "<button ng-click='cancel()' type='button' class='btn btn-default' data-dismiss='modal'>Close</button>"
	+"</div";
app.controller('BloodSampleCollectionCtrl', function($scope, $http, $location, $uibModal) {
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
	
	$http.get('/aizantit/study_volunteer?id=' + id + "&showBloodSamples=true").then(function(response) {
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

	$scope.openDeleteModal = function(id) {
		var modalInstance = $uibModal.open({
			template : modalTemplate,
			controller : 'deleteModalController',
			resolve : {
				id : function() {
					return id;
				}
			}

		});
		
	
		
	  };
	  $scope.openViewModal = function(selectedSample) {
		var aliquotsForSample = $scope.currentSamples.filter(function(sample) {
			return selectedSample.time === sample.time;
		});
		
		console.log('VEDHA aliquotsForSample', aliquotsForSample);

		  var modalInstance = $uibModal.open({
				template : viewTemplate,
				controller : 'viewModalController',
				resolve : {
					aliquots: function() {
						return aliquotsForSample;
					}		
				}

			});
			
		
			
		  };

	
});

app.controller('deleteModalController', function($scope, $http, $uibModalInstance, id) {
	console.log('VEDHAAA ID modal', id);

	$scope.id = id;
	$scope.delete = function() {
		var body = { BloodSampleColletionId: $scope.id };
		console.log('VEDHA deleting', body);
		$http.post('deleteBloodSampleCollecion?BloodSampleColletionId=' + $scope.id).then(function(response) {
			console.log('VEDHAAA HEREE DELETED');
			$uibModalInstance.close('deleted');	
			location.reload();
		
			
			
		});
	};
	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');

	};
	$scope.x = function() {
		$uibModalInstance.dismiss('x');
	};
});

app.controller('viewModalController', function($scope, $http, aliquots, $uibModalInstance) {
	$scope.aliquots = aliquots;
	
	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');

	};
	$scope.x = function() {
		$uibModalInstance.dismiss('x');
	};
});


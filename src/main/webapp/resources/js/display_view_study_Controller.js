var app = angular.module('viewStudyApp', ['ui.bootstrap'], function($locationProvider) {
	$locationProvider.html5Mode(true);
});
var modalTemplate = 
	"<div class='modal-header'>"
	+ "<button ng-click='x()'type='button' class='close' data-dismiss='modal'>x</button>"
	+ "<h4 class='modal-title'>Warning!</h4>"
	+ "</div>"
	+ "<div class='modal-body'>Are you sure You want to Delete{{a.id}}</div>"
	+ "<div class='modal-footer'>"
	+ "<button ng-click='cancel()' type='button' class='btn btn-default' data-dismiss='modal'>Close</button>"
	+

	"<button ng-click='delete()' type='button' class='btn btn-danger' data-dismiss='modal'>Delete</button>	</div>";

app.controller('viewStudyController', function($scope, $http, $location, $uibModal) {

	var studyId = $location.search().id;
	var currentPeriod = parseInt($location.search().period);
	
	$http.get('/aizantit/study?id=' + studyId).then(function(response) {
		
		console.log('VEDHA response', response.data);
	
		$scope.study = response.data;
		
		$scope.periodsCount = $scope.study.periods;
		
		$scope.periodsArr = new Array($scope.study.periods);
		console.log('VEDHA periods count', $scope.periodsCount, $scope.periodsArr, $scope.periodsArr);
		$scope.currentPeriod = currentPeriod || 1;
	});
		
	$scope.onClick = function(currentPeriod) {
		console.log('hello',currentPeriod)
		$scope.currentPeriod=currentPeriod;	
	};
	
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
	
	
	$scope.hasStarted = function(studyVolunteer) {
		var samplesForPeriod = studyVolunteer.bloodSampleCollection.filter(function(sample) {
			return sample.period === $scope.currentPeriod && sample.scanTime;
		});
		return !!samplesForPeriod.length;
	}
});
app.controller('deleteModalController', function($scope, $http, $uibModalInstance, id) {
	console.log('VEDHAAA ID modal', id, 153);

	$scope.id = id;
	$scope.delete = function() {
		var body = { id: $scope.id };
		console.log('VEDHA deleting', body);
		$http.post('/aizantit/delete_studyVolunteer?id=' + $scope.id).then(function(response) {
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

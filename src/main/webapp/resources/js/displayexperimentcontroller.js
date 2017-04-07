var app = angular.module('myApp', [ 'ui.bootstrap' ]);

var modalTemplate = 
		"<div class='modal-header'>"
		+ "<button ng-click='x()'type='button' class='close' data-dismiss='modal'>x</button>"
		+ "<h4 class='modal-title'>Warning!</h4>"
		+ "</div>"
		+ "<div class='modal-body'>Are you sure You want to Delete{{s.id}}</div>"
		+ "<div class='modal-footer'>"
		+ "<button ng-click='cancel()' type='button' class='btn btn-default' data-dismiss='modal'>Close</button>"
		+

		"<button ng-click='delete()' type='button' class='btn btn-danger' data-dismiss='modal'>Delete</button>	</div>";



		  
		
app.controller('studyCtrl', function($scope, $http, $uibModal) {
	$http.get('list3?page=1').then(function(response) {
		$scope.names = response.data;
		console.log('Data', response.data)
	});

	$http.get('pageCount3').then(function(response) {
		$scope.pagecount = response.data;
		console.log('Page count', response.data)
		$scope.numlist = new Array($scope.pagecount);
	});
	
	$scope.getLengthFromSamples = function(samplesArray) {
		var length = (samplesArray || []).length;
		return length;
		//return length ? samplesArray[length-1] : 0 ;
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
		
		modalInstance.result.then(function(experimentTypeId) {  
			
			
		})
	};
	
	$scope.goToPage = function(pageNumber) {
		console.log('GOIN TO A NEW PAGE');
		$scope.currentPage = pageNumber;
		$http.get('list3?page=' + pageNumber).then(function(response) {
			$scope.names = response.data;
			$scope.currentPage =pageNumber;
		});
	}
});

app.controller('deleteModalController', function($scope, $http, $uibModalInstance, id) {
	console.log('VEDHAAA ID modal', id, 153);
	$scope.id = id;
	$scope.delete = function() {
		var body = { experimentTypeId: $scope.id };
		console.log('VEDHA deleting', body);
		$http.post('deleteexperiment?experimentTypeId=' + $scope.id).then(function(response) {
			console.log('VEDHAAA HEREE DELETED', response);
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


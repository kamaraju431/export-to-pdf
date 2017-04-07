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

app.controller('UserCtrl', function($scope, $http, $uibModal) {
	$http.get('list?page=1').then(function(response) {
		$scope.names = response.data;
	});

	$http.get('pageCount').then(function(response) {
		$scope.pagecount = response.data;
		console.log('Page count', response.data)
		$scope.numlist = new Array($scope.pagecount);
	
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
	
	  $scope.goToPage = function(pageNumber) {
		  console.log('GOIN TO A NEW PAGE');
			$scope.currentPage = pageNumber;
			$http.get('list?page=' + pageNumber).then(function(response) {
				$scope.names = response.data;
				$scope.currentPage = pageNumber;
			});			
		}
	$scope.search = function(){
		console.log('search.........');
		$scope.filter=$scope.tempfilter;
		console.log('search.........');
			$http.get('list?page=1&filter='+$scope.filter).then(function(response) {
				console.log('ID modal');
				$scope.names = response.data;
				angular.copy($scope.names, response.data);
				console.log('user', $scope.names);
				$scope.currentPage = 1;
			});
			
			$http.get('pageCount').then(function(response) {
				$scope.pagecount = response.data;
				console.log('Page count', response.data)
				angular.copy(
						$scope.numlist,
						new Array($scope.pagecount))
			});
	};
});

app.controller('deleteModalController', function($scope, $http, $uibModalInstance, id) {
	console.log('VEDHAAA ID modal', id, 153);

	$scope.id = id;
	$scope.delete = function() {
		var body = { userId: $scope.id };
		console.log('VEDHA deleting', body);
		$http.post('deleteuser?userId=' + $scope.id).then(function(response) {
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

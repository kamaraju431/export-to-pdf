var app=angular.module('myApp',[]);
app.controller('UserCtrl', function($scope, $http, $uibModal) {
$scope.onClick = function(){
	$scope.filter=$scope.tempfilter;
	
		$http.get('list?page=1&filter='+$scope.filter).then(function(response) {
			$scope.names = response.data;
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


	$scope.goToPage = function(pageNumber) {
		console.log('GOIN TO A NEW PAGE');
		$scope.currentPage = pageNumber;
		$http.get('list?page=' + pageNumber + '&filter=' + $scope.filter).then(function(response) {
			$scope.names = response.data;
		
		});
	}
});


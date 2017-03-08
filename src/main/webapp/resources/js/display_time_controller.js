app.controller('add_experiment',function($scope){
$scope.addexperiment=function(){
	$http.post('/add_experiment');
};
$scope.count=1;
$scope.experiment={
		   name: 'New Experiment',
		   aliquot: 1,
		   sample: [0]
};
$scope.watch('count',function(sub,add)
		{
	System.out.println(sub==add);
		});
		
});
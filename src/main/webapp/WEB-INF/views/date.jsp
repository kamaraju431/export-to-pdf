<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="styles/kendo.common.min.css" />
    <link rel="stylesheet" href="styles/kendo.default.min.css" />
    <link rel="stylesheet" href="styles/kendo.default.mobile.min.css" />

    <script src="js/jquery.min.js"></script>
    <script src="js/angular.min.js"></script>
    <script src="js/kendo.all.min.js"></script>
</head>
<body>
<div id="example" ng-app="KendoDemos">
    <div class="demo-section k-content"ng-controller="MyCtrl">
        <div>
            <h4>Select date:</h4>
            <input kendo-date-picker
             ng-model="dateString"
             k-ng-model="dateObject"
             style="width: 100%;" />
<pre>
dateString: {{ dateString }}
dateObject: {{ dateObject | date:"EEEE, MMMM d, yyyy" }}
typeof dateObject: {{ getType(dateObject) }}
dateObject instanceof Date: {{ isDate(dateObject) }}
</pre>
            
        </div>
        <div style="padding-top: 2em;">
            <h4>Select month:</h4>
            <input kendo-date-picker
             k-options="monthSelectorOptions"
             k-format="'MMMM yyyy'"
             style="width: 100%;" />
        </div>
    </div>
</div>

<script>
  angular.module("KendoDemos", [ "kendo.directives" ])
      .controller("MyCtrl", function($scope){
          $scope.monthSelectorOptions = {
            start: "year",
            depth: "year"
          };
          $scope.getType = function(x) {
            return typeof x;
          };
          $scope.isDate = function(x) {
            return x instanceof Date;
          };
      })
</script>


</body>
</html>
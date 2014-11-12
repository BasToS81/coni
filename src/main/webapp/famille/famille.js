myApp.controller('FamilleCtrl', ['$scope', '$http', 'Auth', function($scope, $http, Auth) {

	$scope.familyName = "TEST";
	
	$scope.logged = function() {
//		alert('ok');
		 $scope.familyName = Auth.getUserData().school;
	}
}
]);
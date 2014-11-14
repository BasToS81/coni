myApp.controller('FamilleCtrl', ['$scope', '$http', 'Auth', '$stateParams', function($scope, $http, Auth, $stateParams) {

	$scope.ecole = "TEST";
	
	$scope.urlParams = $stateParams.identifiant;
	
	$scope.logged = function() {
		 $scope.ecole = Auth.getUserData().nomEcole;
		 return "OK";
	}
}
]);

//http://plnkr.co/edit/z9RQKgNBTRbigyQs7OGW?p=preview

myApp.controller('EcoleCtrl', ['$scope', '$http', 'Auth', '$stateParams', function($scope, $http, Auth, $stateParams) {

	$scope.ecole = "TEST";
	
	$scope.urlParams = $stateParams.identifiant;
	
	$scope.logged = function() {
		 $scope.ecole = Auth.getUserData().nomEtablissement;
		 return "OK";
	}
}
]);
myApp.controller('EcoleCtrl', ['$scope', '$http', 'Auth', '$stateParams', function($scope, $http, Auth, $stateParams) {

	$scope.ecole=Auth.getUserData();
	
}
	
]);
myApp.controller('FamilleCtrl', ['$scope', '$http', 'Auth', '$stateParams', function($scope, $http, Auth, $stateParams) {

	$scope.ecole = "TEST";
	
	$scope.urlParams = $stateParams.identifiant;
	

	$scope.eleves = [];

	$scope.init = function() {

		console.log("logged");
		console.log(Auth.getUserData());
		$scope.ecole = Auth.getUserData().nomEcole;
		$scope.eleves = Auth.getUserData().eleves;

		var arrayLength = $scope.eleves.length;
		for (var i = 0; i < arrayLength; i++) {
			console.log(i + " - " + $scope.eleves[i].identifiantChiffre);
		}
	}
}
]);

//http://plnkr.co/edit/z9RQKgNBTRbigyQs7OGW?p=preview

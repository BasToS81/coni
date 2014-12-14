myApp.controller('FamilleCtrl', ['$scope', '$http', 'Auth', '$stateParams', function($scope, $http, Auth, $stateParams) {

	$scope.nomEcole=Auth.getUserData().nomEcole;
	$scope.eleves = Auth.getUserData().eleves;
	

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

myApp.controller('FamilleCommandesCtrl', ['$scope', '$http', 'Auth', '$stateParams', function($scope, $http, Auth, $stateParams) {
	var identifiant = function() {
		 return $scope.utilisateur.identifiant;
	}
	$http.post('/ws/famille/' + identifiant() + '/commande/getList')
	.success(
			function(data, status, headers, config) {

				$scope.commandes = data;
			})
	.error(
			function(data, status, headers, config) {
				$scope.errorMessage = "Erreur au chargement des données de l'école";
			});

}
]);

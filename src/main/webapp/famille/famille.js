myApp.controller('FamilleCtrl', ['$scope', '$http', 'Auth', '$stateParams', function($scope, $http, Auth, $stateParams) {

	$scope.nomEcole=Auth.getUserData().nomEcole;
	$scope.eleves = Auth.getUserData().eleves;
	
	$scope.logged = function() {
		 $scope.ecole = Auth.getUserData().nomEcole;
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
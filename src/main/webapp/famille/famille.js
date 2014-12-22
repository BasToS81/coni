myApp.controller('FamilleCtrl', ['$scope', '$http', 'Auth', '$stateParams', function($scope, $http, Auth, $stateParams) {

	$scope.nomEcole=Auth.getUserData().nomEcole;
	$scope.eleves = Auth.getUserData().eleves;
}
]);

//http://plnkr.co/edit/z9RQKgNBTRbigyQs7OGW?p=preview

myApp.controller('FamilleCommandesCtrl', [ '$scope', '$http', 'Auth', '$stateParams', function($scope, $http, Auth, $stateParams) {
	$scope.commandes = null;
	$scope.getCommandesList = function() { 
		$http.post('/ws/famille/commande/getList')
		.success(
				function(data, status, headers, config) {
					$scope.commandes = data;
				})
		.error(
				function(data, status, headers, config) {
					$scope.errorMessage = "Erreur au chargement des données de l'école";
				});
	};
		
}]);




myApp.controller('FamilleCommandeEnCoursCtrl', ['$scope', '$http', 'Auth', '$stateParams', function($scope, $http, Auth, $stateParams) {
	$scope.commandeEnCours = '';
	$scope.commandeEleves = '';
	$scope.loadCommande = function() { 
		
		$http.post('/ws/famille/commande/get/00012' )
		.success(
				function(data, status, headers, config) {
					$scope.commandeEnCours = data;
					$scope.commandesEleve = data.commandesEleve;
				})
		.error(
				function(data, status, headers, config) {
					$scope.errorMessage = "Erreur au chargement des données de la commande";
				});
	};
	
	$scope.saveCommande = function() { 
		
		$http.post('/ws/famille/commande/save/00012', { commandesEleve : $scope.commandesEleve} )
		.success(
				function(data, status, headers, config) {
					$scope.commandeEnCours = data;
					$scope.commandesEleve = data.commandesEleve;
				})
		.error(
				function(data, status, headers, config) {
					$scope.errorMessage = "Erreur au chargement des données de la commande";
				});
	};
}]);





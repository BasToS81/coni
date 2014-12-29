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
	$scope.loadOrCreateCommande = function() { 
		
		$http.post('/ws/famille/commande/loadOrCreate' )
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
	$scope.saveCommandeEnCours = function() { 
		
		$http.post('/ws/famille/commande/save/',  $scope.commandeEnCours   )
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
	
	
	$scope.calcul = function( produit ) { 
		
		produit.montant = produit.quantite * produit.produit.prix_parent_ttc;
	};
	$scope.addQuantite = function( produit ) { 
		
		produit.quantite = produit.quantite + 1;
		produit.montant = produit.quantite * produit.produit.prix_parent_ttc;
	};
	$scope.removeQuantite = function( produit ) { 
		if(produit.quantite>0) {
			produit.quantite = produit.quantite - 1;
			produit.montant = produit.quantite * produit.produit.prix_parent_ttc;
		} else {
			produit.quantite = 0;
			produit.montant = 0;
		}
	};
}]);





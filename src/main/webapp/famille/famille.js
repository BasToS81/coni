myApp.controller('FamilleCtrl', ['$scope', '$http', 'Auth', '$stateParams', function($scope, $http, Auth, $stateParams) {

	$scope.nomEcole=Auth.getUserData().nomEcole;
	$scope.eleves = Auth.getUserData().eleves;
}
]);

//http://plnkr.co/edit/z9RQKgNBTRbigyQs7OGW?p=preview

myApp.controller('FamilleCommandesCtrl', [ '$scope', '$http', '$state', 'Auth', '$stateParams', function($scope, $http, $state, Auth, $stateParams) {
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
	$scope.openCommande = function(identifiantCommande) { 
	
		$state.go('generic.visualiser', { identifiant : identifiantCommande });
	};
	$scope.deleteCommande = function(identifiantCommande) { 
		
		var answer = confirm("Etes vous sûr de vouloir supprimer cette commande ?");
        if (answer) {
        	

    		$http.get('/ws/famille/commande/del?identifiant=' + identifiantCommande)
		    		.success(
		    				function(data, status, headers, config) {
		    					$scope.getCommandesList();
		    				})
		    		.error(
		    				function(data, status, headers, config) {
		    					$scope.errorMessage = "Erreur au chargement des données de la commande";
		    				});
        	
        }

		
	};
		
}]);




myApp.controller('FamilleCommandeEnCoursCtrl', ['$scope', '$http', '$state', 'Auth', '$stateParams', function($scope, $http, $state, Auth, $stateParams) {
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
	
	$scope.saveProduitsCommandeEnCours = function() { 
		
		$http.post('/ws/famille/commande/save/produits',  $scope.commandeEnCours   )
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
	$scope.saveModePaiementCommandeEnCours = function() { 
		
		$http.post('/ws/famille/commande/save/modepaiement',  $scope.commandeEnCours   )
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
	$scope.saveModePaiementCommandeEnCoursAndGo = function() { 
		
		$http.post('/ws/famille/commande/save/modepaiement',  $scope.commandeEnCours   )
		.success(
				function(data, status, headers, config) {
					$state.go('generic.validation');
				})
		.error(
				function(data, status, headers, config) {
					$scope.errorMessage = "Erreur au chargement des données de la commande";
				});
	};
	
	$scope.calcul = function( commandeEncours, produit ) { 
		var ancienMontant = produit.montant;
		produit.montant = produit.quantite * produit.produit.prix_parent_ttc;
		var diffMontant = produit.montant - ancienMontant;
		commandeEnCours.montant += diffMontant;
	};
	$scope.addQuantite = function(commandeEnCours, produit ) { 
		var ancienMontant = produit.montant;
		produit.quantite = produit.quantite + 1;
		produit.montant = produit.quantite * produit.produit.prix_parent_ttc;
		var diffMontant = produit.montant - ancienMontant;
		commandeEnCours.montant += diffMontant;
	};
	$scope.removeQuantite = function(commandeEnCours, produit ) { 
		var ancienMontant = produit.montant;
		if(produit.quantite>0) {
			produit.quantite = produit.quantite - 1;
			produit.montant = produit.quantite * produit.produit.prix_parent_ttc;
		} else {
			produit.quantite = 0;
			produit.montant = 0;
		}
		var diffMontant = produit.montant - ancienMontant;
		commandeEnCours.montant += diffMontant;
	};
}
]);


myApp.controller('FamilleCommandeEnCoursValidationCtrl', ['$scope', '$http', '$state', 'Auth', '$stateParams', function($scope, $http, $state, Auth, $stateParams) {
	$scope.commandeEnCours = '';
	$scope.commandeEleves = '';
	$scope.cheque=false;
	$scope.espece=false;
	$scope.internet=false;
	
	$scope.getCommande = function() { 
		$http.get(	
					'/ws/famille/commande/getEnCours'
				 )
		.success(
				function(data, status, headers, config) {
					$scope.commandeEnCours = data;
					$scope.commandesEleve = data.commandesEleve;
					
					if($scope.commandeEnCours.moyenPayement=='ESPECE') {
						$scope.espece=true;
					} else if($scope.commandeEnCours.moyenPayement=='CHEQUE') {
						$scope.cheque=true;
					} else if($scope.commandeEnCours.moyenPayement=='INTERNET') {
						$scope.internet=true;
					}
				})
		.error(
				function(data, status, headers, config) {
					$scope.errorMessage = "Erreur au chargement des données de la commande";
				});
	};
	$scope.validerCommandeEnCours = function() { 
		
		$http.post('/ws/famille/commande/validate',  $scope.commandeEnCours )
		.success(
				function(data, status, headers, config) {
					$scope.commandeEnCours = data;
					$scope.commandesEleve = data.commandesEleve;
					$state.go('generic.payement');
				})
		.error(
				function(data, status, headers, config) {
					$scope.errorMessage = "Erreur au chargement des données de la commande";
				});
	};
}
]);




myApp.controller('FamilleCommandeVisualisationCtrl', ['$scope', '$http', '$state', 'Auth', '$stateParams', function($scope, $http, $state, Auth, $stateParams) {
	$scope.commandeEnCours = Auth.getUserCommandes();
	$scope.commandesEleve = $scope.commandeEnCours.commandesEleve;
	
	
}
]);
myApp.controller('FamilleCtrl', ['$scope', '$http', 'Auth', '$stateParams', function($scope, $http, Auth, $stateParams) {

	$scope.nomEcole=Auth.getUserData().nomEcole;
	$scope.eleves = Auth.getUserData().eleves;
}
]);

//http://plnkr.co/edit/z9RQKgNBTRbigyQs7OGW?p=preview

myApp.controller('FamilleCommandesCtrl', [ '$scope', '$http', '$state', 'Auth', '$stateParams', function($scope, $http, $state, Auth, $stateParams) {
	$scope.commandes = null;
	$scope.panierEnCours = false;
	
	$scope.definePanierEnCours = function(statutCommande) {
		$scope.panierEnCours = (statutCommande=='EN_COURS') || $scope.panierEnCours
	}
	
	$scope.getCommandesList = function() { 
		$http.post('/ws/famille/commande/getList')
		.success(
				function(data, status, headers, config) {

					$scope.panierEnCours = false;
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



myApp.filter('toTTC',  function() {
	  return function(input, scope) {
	    return input * (1 + (scope.tva/100));
	  };
	});



myApp.controller('FamilleCommandeEnCoursCtrl', ['$scope', '$http', '$state', 'Auth', '$stateParams', function($scope, $http, $state, Auth, $stateParams) {
	$scope.commandeEnCours = '';
	$scope.commandeEleves = '';
	$scope.tva = 0;
	$scope.loadOrCreateCommande = function() { 
		
		$http.post('/ws/famille/commande/loadOrCreate' )
		.success(
				function(data, status, headers, config) {
					$scope.commandeEnCours = data;
					$scope.commandesEleve = data.commandesEleve;
					$scope.tva=data.tva;
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
	$scope.saveProduitsCommandeEnCoursAndGo = function() { 
		
		$http.post('/ws/famille/commande/save/produits',  $scope.commandeEnCours   )
		.success(
				function(data, status, headers, config) {
					$state.go('generic.validation');
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
	$scope.calculMontantTTC = function( valeur ) { 
		return valeur * $scope.tva;
	};
	$scope.calcul = function( commandeEnCours, produit ) { 
		if(IsInteger(produit.quantite)&&produit.quantite>=0) {
			var ancienMontant = produit.montantParentHT;
			produit.montantParentHT = parseInt(produit.quantite) * produit.produit.prixParentHT;
			var diffMontant = produit.montantParentHT - ancienMontant;
			commandeEnCours.montantParentHT += diffMontant;
		}
	};
	$scope.addQuantite = function(commandeEnCours, produit ) { 
		var ancienMontant = produit.montantParentHT;

		if( IsInteger(produit.quantite) && produit.quantite >=0 ) {
			produit.quantite = parseInt(produit.quantite) + 1;
		} else {
			produit.quantite = 1;
		}
		produit.montantParentHT = produit.quantite * produit.produit.prixParentHT;
		var diffMontant = produit.montantParentHT - ancienMontant;
		commandeEnCours.montantParentHT += diffMontant;
		
	};
	$scope.removeQuantite = function(commandeEnCours, produit ) { 
		var ancienMontant = produit.montantParentHT;
		if(produit.quantite>0) {
			produit.quantite = parseInt(produit.quantite) - 1;
			produit.montantParentHT = produit.quantite * produit.produit.prixParentHT;
		} else {
			produit.quantite = 0;
			produit.montantParentHT = 0;
		}
		var diffMontant = produit.montantParentHT - ancienMontant;
		commandeEnCours.montantParentHT += diffMontant;
	};
}
]);


myApp.controller('FamilleCommandeEnCoursValidationCtrl', ['$scope', '$http', '$state', 'Auth', '$stateParams', function($scope, $http, $state, Auth, $stateParams) {
	$scope.commandeEnCours = '';
	$scope.commandeEleves = '';
	$scope.cheque=false;
	$scope.espece=false;
	$scope.internet=false;

	$scope.tva = 0;
	
	$scope.getCommande = function() { 
		$http.get(	
					'/ws/famille/commande/getEnCours'
				 )
		.success(
				function(data, status, headers, config) {
					$scope.commandeEnCours = data;
					$scope.commandesEleve = data.commandesEleve;
					$scope.tva=data.tva;
					
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
	$scope.tva=$scope.commandeEnCours.tva;

}
]);
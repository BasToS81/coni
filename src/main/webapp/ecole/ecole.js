

myApp.controller('EcoleCtrl', ['$scope', '$http', 'Auth', '$stateParams', function($scope, $http, Auth, $stateParams) {
	$scope.ecole=Auth.getUserData();
	
}
]);

myApp.controller('EcoleSyntCtrl', ['$scope', '$http', 'Auth', '$stateParams', function($scope, $http, Auth, $stateParams) {
	$scope.ecole=Auth.getUserData();
	$scope.eleves=null;
	$scope.predicate='nom';
	$scope.reverse=false;
	$scope.montantTotal=0;
	$scope.restantTotal=0;
	
	$scope.getSyntheseEleves = function() { 
		$http.get('/ws/ecole/eleve/getSynthese')
		.success(
				function(data, status, headers, config) {
					$scope.eleves = data;
				})
		.error(
				function(data, status, headers, config) {
					$scope.errorMessage = "Erreur au chargement des données de l'école";
				});
	};
	
	$scope.getSyntheseClasses = function() { 
		$http.get('/ws/ecole/classe/getSynthese')
		.success(
				function(data, status, headers, config) {
					$scope.eleves = data;
				})
		.error(
				function(data, status, headers, config) {
					$scope.errorMessage = "Erreur au chargement des données de l'école";
				});
	};
	
	
	$scope.calculTotal = function(montant, restant) {
		$scope.montantTotal=montant+$scope.montantTotal;
		$scope.restantTotal=restant+$scope.restantTotal;
	}
}
]);

//Information des données de synthèse de l'école
myApp.controller('EcoleSyntheseCtrl', [ '$scope', '$http', 'Auth', '$stateParams', function($scope, $http, Auth, $stateParams) {
	$scope.commandes = null;
		
	
	
}]);

myApp.controller('EcoleClasseCtrl', [ '$scope', '$http', 'Auth', '$stateParams', function($scope, $http, Auth, $stateParams) {
	$scope.nomClasse;
	$scope.eleves = null;
	$scope.getSynthese = function() { 
		$scope.nomClasse = $stateParams.nom;
		$http.get('/ws/ecole/loadClasses/' + $stateParams.id)
		.success(
				function(data, status, headers, config) {
					$scope.eleves = data;
				})
		.error(
				function(data, status, headers, config) {
					$scope.errorMessage = "Erreur au chargement des données de la classe";
				});
	};
		
}]);



//Controller des commandes écoles au photographe
myApp.controller('EcoleCommandesCtrl', [ '$scope', '$http', '$state', 'Auth', '$stateParams', function($scope, $http, $state, Auth, $stateParams) {
	$scope.commandes = null;
	$scope.commandesNonPayees = null;
	
	$scope.getCommandesNonPayees = function() {
		$http.get('/ws/ecole/commande/getListNonPaye')
		.success(
				function(data, status, headers, config) {
					$scope.commandesNonPayees = data;
				})
		.error(
				function(data, status, headers, config) {
					$scope.errorMessage = "Erreur au chargement des données de l'école";
				});
	}
	
	$scope.getCommandesList = function() { 
		$http.post('/ws/ecole/commande/getList')
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
	
		$state.go('generic.visualiserEcole', { identifiant : identifiantCommande });
	};
	$scope.deleteCommande = function(identifiantCommande) { 
		
		var answer = confirm("Etes vous sûr de vouloir supprimer cette commande ?");
        if (answer) {
        	

    		$http.get('/ws/ecole/commande/del?identifiant=' + identifiantCommande)
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



// Controller des commandes des élèves par classe
myApp.controller('EcoleClasseCommandeCtrl', ['$scope', '$http', '$state', 'Auth', '$stateParams', function($scope, $http, $state, Auth, $stateParams) {
	$scope.classe = Auth.getUserCommandes();
	$scope.commandesEleves = $scope.classe.commandeEleveSynthese
	$scope.tva=$scope.classe.tva;
	$scope.selectAll = true;

	
	$scope.calcul = function( commandeEnCours, produit ) { 
		
		if(IsInteger(produit.quantite)) {
			
			var ancienMontant = produit.montantParentHT;
			produit.montantParentHT = produit.quantite * produit.produit.prixParentHT;
			var diffMontant = produit.montantParentHT - ancienMontant;
			commandeEnCours.montantTotalParentHT += diffMontant;
		
		}
	};
	
	$scope.selectAllCheckbox = function( ) { 
		
		var count = $scope.commandesEleves.length;

		for(var i = 0; i < count; i++) {
			if($scope.commandesEleves[i].montantTotalParentHT>0) {
				$scope.commandesEleves[i].paiementEffectue = $scope.selectAll;
			}
		}
		
		$scope.selectAll=!$scope.selectAll;
		
	};
	
}]);

myApp.controller('EcoleCtrl', ['$scope', '$http', 'Auth', '$stateParams', function($scope, $http, Auth, $stateParams) {

	$scope.ecole=Auth.getUserData();
	
}
	
]);


myApp.controller('EcoleSyntheseCtrl', [ '$scope', '$http', 'Auth', '$stateParams', function($scope, $http, Auth, $stateParams) {
	$scope.commandes = null;
	$scope.etatActivation = null;
	$scope.getActivation = function() { 
		$http.get('/ws/ecole/activation')
		.success(
				function(data, status, headers, config) {
					$scope.etatActivation = data;
				})
		.error(
				function(data, status, headers, config) {
					$scope.errorMessage = "Erreur au chargement des données de l'école";
				});
	};
		
	
	$scope.getCommandesNonPayees = function() {
		$http.get('/ws/ecole/commande/getListNonPaye')
		.success(
				function(data, status, headers, config) {
					$scope.commandes = data;
				})
		.error(
				function(data, status, headers, config) {
					$scope.errorMessage = "Erreur au chargement des données de l'école";
				});
	}
	
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




myApp.controller('EcoleCommandesCtrl', [ '$scope', '$http', '$state', 'Auth', '$stateParams', function($scope, $http, $state, Auth, $stateParams) {
	$scope.commandes = null;
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

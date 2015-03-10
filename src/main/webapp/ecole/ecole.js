

myApp.controller('EcoleCtrl', ['$scope', '$http', '$state', 'Auth', '$stateParams', '$window', function($scope, $http, $state, Auth, $stateParams, $window) {
	$scope.ecole=Auth.getUserData();
	
	

	$scope.montantTotal=0;
	$scope.restantTotal=0;
	
	$scope.calculTotal = function(montant, restant) {
		$scope.montantTotal=montant+$scope.montantTotal;
		$scope.restantTotal=restant+$scope.restantTotal;
	}
	
	$scope.visualiseClasse = function(classeId) {
		
		Auth.setUserClasseVisualise(classeId);
		$state.go('generic.ecole.classe', {'id' : classeId});
		
	}
	
	$scope.visualiseCommandesNonPayees = function() {

		$state.go('generic.ecole.commandesEleveNonPayees');
		
	}
	
	
	$scope.activerTousLesAcces = function() {
		$http.post('/ws/ecole/eleve/activeAcces')
		.success(
				function(data, status, headers, config) {
					$scope.ecole.etatActivation=1;
				})
		.error(
				function(data, status, headers, config) {
					$scope.errorMessage = "Erreur au chargement des données de l'école";
				});
	}
	
	$scope.desactiverTousLesAcces = function() {
		$http.post('/ws/ecole/eleve/desactiveAcces')
		.success(
				function(data, status, headers, config) {
					$scope.ecole.etatActivation=0;
				})
		.error(
				function(data, status, headers, config) {
					$scope.errorMessage = "Erreur au chargement des données de l'école";
				});
	}
	
}
]);

myApp.controller('EcoleSyntheseCtrl', ['$scope', '$http', 'Auth', '$stateParams', function($scope, $http, Auth, $stateParams) {
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
	
	
	
	
	$scope.calculTotal = function(montant, restant) {
		$scope.montantTotal=montant+$scope.montantTotal;
		$scope.restantTotal=restant+$scope.restantTotal;
	}
}
]);



myApp.controller('EcoleClasseSyntheseCtrl', [ '$scope', '$http', '$state', 'Auth', '$stateParams', "$filter", function($scope, $http, $state, Auth, $stateParams, $filter) {
	$scope.ecole=Auth.getUserData();
	$scope.eleves=Auth.getUserCommandes();
	$scope.predicate='nom';
	$scope.reverse=false;
	$scope.montantTotal=0;
	$scope.restantTotal=0;
	$scope.edition=false;
	$scope.messageInformation="";

	$scope.calculTotal = function(montant, restant) {
		$scope.montantTotal=montant+$scope.montantTotal;
		$scope.restantTotal=restant+$scope.restantTotal;
	}
	
	$scope.visualiserCommandes = function(identifiantEleve) {
		$state.go('generic.ecole.commandesEleve', { id : identifiantEleve });
	}
	
	
	
	$scope.editer = function() {
		$scope.edition=!$scope.edition;
		if($scope.edition) {
			$scope.messageInformation="";
		}
	}
	
	
	$scope.sauver = function() {
		
		$scope.messageInformation="";
		
		angular.forEach($scope.eleves,function(eleve,index) {    
			
			//Convertion de la date
			eleve.newDateLimiteAcces = $filter('date')(eleve.newDateLimiteAcces, "dd/MM/yyyy");
			
            $http.post('/ws/ecole/classe/eleve/edition', eleve)
    		.success(
    				function(data, status, headers, config) {
    					eleve.dateLimiteAcces = eleve.newDateLimiteAcces;
    					eleve.nom = eleve.newNom;
    				}
            )
    		.error(
    				function(data, status, headers, config) {
    					$scope.messageInformation="Erreur durant l'enregistrement, veuillez retentez ultérieurement";
    				});
        })
        if($scope.messageInformation.length==0) {
        	$scope.messageInformation="Modifications enregistrées";
        	$scope.editer();
        } 
		
		
	}
	
	$scope.openedInstances = [];
	$scope.i = 0;
	$scope.format = 'dd/MM/yyyy';
	
	$scope.open = function($event, instance) {
		
	    $event.preventDefault();
	    $event.stopPropagation();

	    $scope.openedInstances[instance] = true;
	};
	
	 // Disable weekend selection
	$scope.disabled = function(date, mode) {
		//return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
		return false;
	};
	$scope.dateOptions = {
		    formatYear: 'yy',
		    startingDay: 1
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

myApp.controller('EcoleEleveCommandesCtrl', ['$scope', '$http', '$state', 'Auth', '$stateParams', function($scope, $http, $state, Auth, $stateParams) {
	$scope.commandes = Auth.getUserCommandes();
	$scope.classe = Auth.getUserClasseVisualise();
	$scope.eleveIdentifiant=0;
	
	
	
	$scope.retourArriere = function() {
		$state.go('generic.ecole.classe', {'id' : $scope.classe});
	}
	
	$scope.openCommande = function(identifiantCommande) { 
		$state.go('generic.ecole.commandesEleve.visualisation', {'idCommande' : identifiantCommande});
	}
	$scope.openCommandeNonPayee = function(identifiantCommande) { 
		$state.go('generic.ecole.commandesEleveNonPayees.visualisation', {'idCommande' : identifiantCommande});
	}
	
	$scope.validerPaiementCommande = function(commande) {
		
		$http.post('/ws/ecole/commande/validatePaiement?identifiant=' + commande.identifiant)
		.success(
				function(data, status, headers, config) {
					commande.statut='EN_ATTENTE_VALID_RESPONSABLE';
				})
		.error(
				function(data, status, headers, config) {
					$scope.errorMessage = "Erreur au chargement des données de la commande";
				});
	}
	
	
	
	$scope.commandeEnCours = Auth.getUserCommandes();
	$scope.commandesEleve = $scope.commandeEnCours.commandesEleve;
}]);

myApp.controller('EcoleCommandeVisualisationCtrl', ['$scope', '$http', '$state', 'Auth', '$stateParams', function($scope, $http, $state, Auth, $stateParams) {
	$scope.commandeEnCours = Auth.getUserCommandes();
	$scope.commandesEleve = $scope.commandeEnCours.commandesEleve;
}]);
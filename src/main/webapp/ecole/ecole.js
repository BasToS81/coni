// #########################################
// ############### ROUTING #################
// #########################################

var loadClasseSynthese = function($q, $http, $stateParams, $timeout, Auth) {

	var deferred = $q.defer();	
	
	$http.get('/ws/ecole/classe/getSynthese?idClasse=' + $stateParams.id)
	.success(function(data, status, headers, config) {
		Auth.setUserCommandes(data);
		$timeout(deferred.resolve, 0);
	});
	
	return deferred.promise;
};

var loadCommandesEleve = function($q, $http, $stateParams, $timeout, Auth) {

	var deferred = $q.defer();	
	
	var url = '/ws/ecole/eleve/commande/getList?identifiant=' + $stateParams.id;
	
	$http.get(url)
	.success(function(data, status, headers, config) {
		Auth.setUserCommandes(data);
		$timeout(deferred.resolve, 0);
	});
	
	return deferred.promise;
};

var loadCommandesEleveNonPayees = function($q, $http, $stateParams, $timeout, Auth) {

	var deferred = $q.defer();	
	
	var url = '/ws/ecole/commande/getListNonPaye'

	$http.get(url)
	.success(function(data, status, headers, config) {
		Auth.setUserCommandes(data);
		$timeout(deferred.resolve, 0);
	});
	
	return deferred.promise;
};

var visualiserCommandeEleve = function($q, $http, $stateParams, $timeout, Auth) {

	var deferred = $q.defer();	
	
	$http.get('/ws/famille/commande/get?identifiant=' + $stateParams.idCommande)
	.success(function(data, status, headers, config) {
		Auth.setUserCommandes(data);
		$timeout(deferred.resolve, 0);
	});
	
	return deferred.promise;
};


var loadCommandeClasse = function($q, $http, $stateParams, $timeout, Auth) {

	var deferred = $q.defer();

	$http.get('/ws/ecole/commande/classe/getList?idClasse=' + $stateParams.id)
	.success(function(data, status, headers, config) {
		Auth.setUserCommandes(data);
		$timeout(deferred.resolve, 0);
	});
	
	return deferred.promise;
};

var loadCommandesEcole = function($q, $http, $stateParams, $timeout, Auth) {

	var deferred = $q.defer();

	$http.get('/ws/ecole/commandes/eleve/getList')
	.success(function(data, status, headers, config) {
		Auth.setUserCommandes(data);
		$timeout(deferred.resolve, 0);
	});
	
	return deferred.promise;
};


var loadCommandeEcole = function($q, $http, $stateParams, $timeout, Auth) {

	var deferred = $q.defer();

	$http.get('/ws/ecole/commande/get?id=' + $stateParams.id)
	.success(function(data, status, headers, config) {
		Auth.setUserCommandes(data);
		$timeout(deferred.resolve, 0);
	});
	
	return deferred.promise;
};


var loadEcoleCommandesClasseEleve = function($q, $http, $stateParams, $timeout, Auth) {

	var deferred = $q.defer();	
	
	var url = '/ws/ecole/commandes/classe/eleve/getList?identifiant=' + $stateParams.idEleve;
	
	$http.get(url)
	.success(function(data, status, headers, config) {
		Auth.setUserCommandes(data);
		$timeout(deferred.resolve, 0);
	});
	
	return deferred.promise;
};
//#########################################
//#########################################


//  ~~~~~~~~~~~~~~~~~~~~~~~~~
//  PAGE ACCUEIL ECOLE
//  ~~~~~~~~~~~~~~~~~~~~~~~~~
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
	
	

	$scope.visualiseCommandesClasse = function(classeId) {
		
		Auth.setUserClasseVisualise(classeId);
		$state.go('generic.ecoleCommandes.classe', {'id' : classeId});
		
	}

	$scope.visualiseCommandes = function() {

		$state.go('generic.ecoleCommandes.liste');
		
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


//~~~~~~~~~~~~~~~~~~~~~~~~~
//PAGE SYNTHESE / CLASSE  (AVEC EDITION)
//~~~~~~~~~~~~~~~~~~~~~~~~~
myApp.controller('EcoleClasseSyntheseCtrl', [ '$scope', '$http', '$state', 'Auth', '$stateParams', "$filter", function($scope, $http, $state, Auth, $stateParams, $filter) {
	$scope.ecole=Auth.getUserData();
	$scope.eleves=Auth.getUserCommandes();
	$scope.predicate='nom';
	$scope.reverse=false;
	$scope.montantTotal=0;
	$scope.restantTotal=0;
	$scope.edition=false;
	$scope.messageInformation="";
	
	$scope.myRegex = /[a-zA-Z0-9]*/;

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



//~~~~~~~~~~~~~~~~~~~~~~~~~
//PAGE VISUALISATION DES COMMANDES ELEVES 
//~~~~~~~~~~~~~~~~~~~~~~~~~

myApp.controller('EcoleEleveCommandesCtrl', ['$scope', '$http', '$state', 'Auth', '$stateParams', 'StatutPaiementCommandeFamille', function($scope, $http, $state, Auth, $stateParams, StatutPaiementCommandeFamille) {
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
					commande.statutPaiement=StatutPaiementCommandeFamille.PAYE;
				})
		.error(
				function(data, status, headers, config) {
					$scope.errorMessage = "Erreur au chargement des données de la commande";
				});
	}
	

	$scope.checkCommandeNonPaye = function(commande) {
		return (commande.statutPaiement==StatutPaiementCommandeFamille.NON_PAYE);
	}
	
	$scope.commandeEnCours = Auth.getUserCommandes();
	$scope.commandesEleve = $scope.commandeEnCours.commandesEleve;
}]);


//~~~~~~~~~~~~~~~~~~~~~~~~~
//PAGE VISUALISATION DU CONTENUT DES COMMANDES ELEVES 
//~~~~~~~~~~~~~~~~~~~~~~~~~
myApp.controller('EcoleCommandeVisualisationCtrl', ['$scope', '$http', '$state', 'Auth', '$stateParams', function($scope, $http, $state, Auth, $stateParams) {
	$scope.commandeEnCours = Auth.getUserCommandes();
	$scope.commandesEleve = $scope.commandeEnCours.commandesEleve;
	$scope.tva = $scope.commandeEnCours.tva;
}]);



//~~~~~~~~~~~~~~~~~~~~~~~~~
//PAGE ACCUEIL commandes écoles au photographe
//~~~~~~~~~~~~~~~~~~~~~~~~~
myApp.controller('EcoleCommandesCtrl', [ '$scope', '$http', '$state', 'Auth', '$stateParams', function($scope, $http, $state, Auth, $stateParams) {
	$scope.commandes = null;
	$scope.commandesNonPayees = null;
	
	
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
	
		$state.go('generic.visualisationEcoleCommandes', { id : identifiantCommande });
	};
	$scope.annulerCommande = function(identifiantCommande) { 
		
		var answer = confirm("Etes vous sûr de vouloir d'annuler cette commande ?");
        if (answer) {
        	

    		$http.get('/ws/ecole/commande/annuler?identifiant=' + identifiantCommande)
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


//~~~~~~~~~~~~~~~~~~~~~~~~~
//PAGE Bordereau par classe
//~~~~~~~~~~~~~~~~~~~~~~~~~
myApp.controller('EcoleClasseCommandeCtrl', ['$scope', '$http', '$state', 'Auth', '$stateParams', function($scope, $http, $state, Auth, $stateParams) {
	$scope.classe = Auth.getUserCommandes();
	$scope.commandesEleves = $scope.classe.commandeEleve
	$scope.tva=$scope.classe.tva;
	$scope.selectAll = true;
	$scope.edition = false;
	
	$scope.montantTotal=[];
	
	
	$scope.calcul = function( commandeEnCours, produit ) { 
		
		if(IsInteger(produit.quantiteNonPaye)&&produit.quantiteNonPaye>=0) {
			var ancienMontant = produit.montantParentHTNonPaye;
			var ancienMontantTotal = $scope.classe.montantTotalParentHTNonPaye;
			produit.montantParentHTNonPaye = parseInt(produit.quantiteNonPaye) * produit.produit.prixParentHT;
			var diffMontant = produit.montantParentHTNonPaye - ancienMontant;
			commandeEnCours.montantParentHTNonPaye += diffMontant;
			$scope.classe.montantTotalParentHTNonPaye += diffMontant;
		}
		
	};
	
	$scope.sauvegarderCommandes = function() {
		
		$http.post('/ws/ecole/commandes/classe/eleve/save', $scope.classe)
		.success(
				function(data, status, headers, config) {
					$http.get('/ws/ecole/commande/classe/getList?idClasse=' + Auth.getUserClasseVisualise())
					.success(function(data, status, headers, config) {
						$scope.classe = data;
						$scope.commandesEleves = $scope.classe.commandeEleve;
					});
				})
		.error(
				function(data, status, headers, config) {
					$scope.errorMessage = "Erreur au chargement des données de la commande";
				});
	}
	
	$scope.commander = function() {
		
		//Sauvegarde au préalable des dernières modifications
		$scope.sauvegarderCommandes();
		
		//Commande au photographe
		$http.post('/ws/ecole/commande/createAndValidate')
		.success(
				function(data, status, headers, config) {
					$state.go('generic', {'type' : 'ecole'});
				})
		.error(
				function(data, status, headers, config) {
					$scope.errorMessage = "Erreur au chargement des données de la commande";
				});
		
		
	}
	

	$scope.validerPaiementCommande = function(commande) {
		
		$http.post('/ws/ecole/commande/validatePaiement?identifiant=' + commande.id)
		.success(
				function(data, status, headers, config) {
					commande.id=null;
					$http.get('/ws/ecole/commande/classe/getList?idClasse=' + Auth.getUserClasseVisualise())
					.success(function(data, status, headers, config) {
						$scope.classe = data;
						$scope.commandesEleves = $scope.classe.commandeEleve;
					});
				})
		.error(
				function(data, status, headers, config) {
					$scope.errorMessage = "Erreur au chargement des données de la commande";
				});
	}
	
	$scope.visualiserCommandes = function(identifiantEleve) {
		$state.go('generic.ecoleCommandes.eleve', { idEleve : identifiantEleve });
	}
	
}]);


//~~~~~~~~~~~~~~~~~~~~~~~~~
//PAGE BORDEREAU : VISUALISATION DES COMMANDES ELEVES 
//~~~~~~~~~~~~~~~~~~~~~~~~~

myApp.controller('EcoleCommandesClasseEleveCtrl', ['$scope', '$http', '$state', 'Auth', '$stateParams', 'StatutPaiementCommandeFamille', function($scope, $http, $state, Auth, $stateParams, StatutPaiementCommandeFamille) {
	$scope.commandes = Auth.getUserCommandes();
	$scope.classe = Auth.getUserClasseVisualise();
	$scope.eleveIdentifiant=0;
	
	
	$scope.retourArriere = function() {
		$state.go('generic.ecoleCommandes.classe', {'id' : $scope.classe});
	}
	
	$scope.openCommande = function(identifiantCommande) { 
		$state.go('generic.ecoleCommandes.eleve.visualisation', {'idCommande' : identifiantCommande});
	}
	
	$scope.validerPaiementCommande = function(commande) {
		
		$http.post('/ws/ecole/commande/validatePaiement?identifiant=' + commande.identifiant)
		.success(
				function(data, status, headers, config) {
					commande.statutPaiement=StatutPaiementCommandeFamille.PAYE;
				})
		.error(
				function(data, status, headers, config) {
					$scope.errorMessage = "Erreur au chargement des données de la commande";
				});
	}
	
	$scope.checkCommandeNonPaye = function(commande) {
		return (commande.statutPaiement==StatutPaiementCommandeFamille.NON_PAYE);
	}
	
	

}]);

//~~~~~~~~~~~~~~~~~~~~~~~~~
//PAGE DES COMMANDES ELEVES POUR CREATION COMMANDE ECOLE
//~~~~~~~~~~~~~~~~~~~~~~~~~
myApp.controller('EcoleCommandesEleveCtrl', ['$scope', '$http', '$state', 'Auth', '$stateParams',  'StatutPaiementCommandeFamille', function($scope, $http, $state, Auth, $stateParams, StatutPaiementCommandeFamille) {
	$scope.commandes = Auth.getUserCommandes();
	$scope.classe = Auth.getUserClasseVisualise();
	$scope.commandeEnCours = Auth.getUserCommandes();
	$scope.commandesEleve = $scope.commandeEnCours.commandesEleve;
	$scope.eleveIdentifiant=0;
	$scope.toutPaye = true;
	
	
	$scope.retourArriere = function() {
		$state.go('generic', {'type' : 'ecole'});
	}
	
	$scope.openCommande = function(identifiantCommande) { 
		$state.go('generic.ecoleCommandes.liste.visualisation', {'idCommande' : identifiantCommande});
	}
	
	
	$scope.validerPaiementCommande = function(commande) {
		
		$http.post('/ws/ecole/commande/validatePaiement?identifiant=' + commande.identifiant)
		.success(
				function(data, status, headers, config) {
					commande.statutPaiement=StatutPaiementCommandeFamille.PAYE;
					$scope.initToutPaye();
				})
		.error(
				function(data, status, headers, config) {
					$scope.errorMessage = "Erreur au chargement des données de la commande";
				});
	}
	
	
	$scope.commander = function() {
		
		$http.post('/ws/ecole/commande/createAndValidate')
		.success(
				function(data, status, headers, config) {
					$state.go('generic', {'type' : 'ecole'});
				})
		.error(
				function(data, status, headers, config) {
					$scope.errorMessage = "Erreur au chargement des données de la commande";
				});
		
		
	}
	

	$scope.checkCommandeNonPaye = function(commande) {
		return (commande.statutPaiement==StatutPaiementCommandeFamille.NON_PAYE);
	}
	
	
	$scope.initToutPaye = function() {
		$scope.toutPaye = true;
		if($scope.commandes.length==0) {
			$scope.toutPaye = false;
		} else {
			for(var i = 0; i < $scope.commandes.length; i++) {
				$scope.toutPaye = $scope.toutPaye && ($scope.commandes[i].statutPaiement!=StatutPaiementCommandeFamille.NON_PAYE);
			}
		}
	}
	
	

}]);


//~~~~~~~~~~~~~~~~~~~~~~~~~
//PAGE VISUALISATION COMMANDE ECOLE 
//~~~~~~~~~~~~~~~~~~~~~~~~~
myApp.controller('EcoleCommandesEcoleCtrl', ['$scope', '$http', '$state', 'Auth', '$stateParams', function($scope, $http, $state, Auth, $stateParams) {
	$scope.commandeEcole = Auth.getUserCommandes();
	$scope.commandes = $scope.commandeEcole.commandesFamilles;
	$scope.classe = Auth.getUserClasseVisualise();
	
	$scope.retourArriere = function() {
		$state.go('generic', {'type' : 'ecole'});
	}
	
	$scope.openCommande = function(identifiantCommande) { 
		$state.go('generic.visualisationEcoleCommandes.visualisation', {'idCommande' : identifiantCommande});
	}
}]);

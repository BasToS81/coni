//https://medium.com/opinionated-angularjs/techniques-for-authentication-in-angularjs-applications-7bbf0346acec
//http://stackoverflow.com/questions/22936865/handling-error-in-ui-routers-resolve-function-aka-statechangeerror-passing-d

myApp.value('urlDataHomeEcole', '/ws/ecole/loadData/');
myApp.value('urlDataHomeFamille', '/ws/famille/loadData/');

myApp.config(function($stateProvider, $urlRouterProvider) {
	// Set default route
	$urlRouterProvider.otherwise('/');

	// Declare states
	// Page login
	$stateProvider.state('home', {
		url : '/',
		views : {
			'content-body@' : {
				templateUrl : 'login.html',
				controller : 'LoginCtrl'
			}
		}
	})
	// ############################
	//Page Accueil après login
	// ############################	
	.state('generic', {
		url : '/{type}',
		views : {
			'content-header@' : {
				templateUrl : 'header.html',
				controller : 'HeaderCtrl',
				resolve : {	additionalData : loadEcoleDescription }
			},
			'content-body@' : {
				templateUrl : function ($stateParams){ return '/' + $stateParams.type + '/profile.html'; },
				controller : function ($stateParams){ return $stateParams.type + 'Ctrl'; },
				resolve : {	additionalData : loadData }
			},
			'content-footer@' : {
				templateUrl : 'footer.html',
			}
		}
	})
	// ############################
	// FAMILLE
	// ############################
	
	// Page Commande 
		.state('generic.commander', {
			url : '/commande',
			views : {
				'content-body@' : {
					
					templateUrl : '/famille/commande/commande.html' 
				}
			}
		})
	    .state('generic.mode', {
	    	url : '/commande/mode',
			views : {
				'content-body@' : {
					
					templateUrl : '/famille/commande/mode.html' 
				}
			}
	    })
	    .state('generic.validation', {
	    	url : '/commande/validation',
			views : {
				'content-body@' : {
					templateUrl : '/famille/commande/validation.html'
				}
			}
	    })
	    .state('generic.payement', {
	        url: '/commande/payement',
	        views : {
				'content-body@' : {
					
					templateUrl : '/famille/commande/payement.html' 
				}
			}
	    })
	    .state('generic.recapitulatif', {
	        url: '/commande/recapitulatif',
	        views : {
				'content-body@' : {
					
					templateUrl : '/famille/commande/recapitulatif.html' 
				}
			}
	    })
	.state('generic.visualiser', {
    	url : '/commande/{identifiant}',
		views : {
			'content-body@' :{
				templateUrl : '/famille/commande/visualisation.html',
				controller : 'FamilleCommandeVisualisationCtrl',
				resolve : {	additionalData : loadCommandeFamille }
			}	
		}
    })
    
	// ############################
	// ECOLE
	// ############################

	// Page élèves par classe 
    	.state('generic.ecole', {
			url : '/liste',
			views : {
				'content-body@' : {
					templateUrl : '/ecole/classes/menu.html' 
				}
			}
		})
			// liste des classes
			.state('generic.ecole.classe', {
				url : '/classe/{id}',
				views : {
					'content-ecole@generic.ecole' : {
						templateUrl : '/ecole/classes/classe.html',
						controller : 'EcoleClasseSyntheseCtrl',
						resolve : {	additionalData : loadClasseSynthese }
					}
				}
			})	
			// - liste des commandes d'un élève
			.state('generic.ecole.commandesEleve', {
				url : '/classe/eleve/{id}',
				views : {
					'content-ecole@generic.ecole' : {
						templateUrl : '/ecole/classes/commandesEleve.html',
						controller : 'EcoleEleveCommandesCtrl',
						resolve : {	additionalData : loadCommandesEleve }
					}
				}
			})	
			// - liste des commandes non payées
			.state('generic.ecole.commandesEleveNonPayees', {
				url : '/commandesNonPayees',
				views : {
					'content-ecole@generic.ecole' : {
						templateUrl : '/ecole/classes/commandesNonPayees.html',
						controller : 'EcoleEleveCommandesCtrl',
						resolve : {	additionalData : loadCommandesEleveNonPayees }
					}
				}
			})
			// - visualisation d'une commande d'un élève
			.state('generic.ecole.commandesEleve.visualisation', {
				url : '/commande/{idCommande}',
				views : {
					'content-ecole-eleve@generic.ecole.commandesEleve' : {
						templateUrl : '/ecole/classes/visualisationCommande.html',
						controller : 'EcoleCommandeVisualisationCtrl',
						resolve : {	additionalData : visualiserCommandeEleve }
					}
				}
			})
			// - visualisation d'une commande non payee d'un élève 
			.state('generic.ecole.commandesEleveNonPayees.visualisation', {
				url : '/commande/{idCommande}',
				views : {
					'content-ecole-eleve@generic.ecole.commandesEleveNonPayees' : {
						templateUrl : '/ecole/classes/visualisationCommande.html',
						controller : 'EcoleCommandeVisualisationCtrl',
						resolve : {	additionalData : visualiserCommandeEleve }
					}
				}
			})
		//   ---------
		// Commandes
		//   ---------
		.state('generic.ecoleCommandes', {
			url : '/commandesEcole',
			views : {
				'content-body@' : {
					templateUrl : '/ecole/commande/menu.html' 
				}
			}
		})
			// - liste des classes
			.state('generic.ecoleCommandes.classe', {
				url : '/classe/{id}',
				views : {
					'content-commande@generic.ecoleCommandes' : {
						templateUrl : '/ecole/commande/creation/commande.html',
						controller : 'EcoleClasseCommandeCtrl',
						resolve : {	additionalData : loadCommandeClasse }
					}
				}
			})	
			// - visualisation des commandes
			.state('generic.ecoleCommandes.liste', {
				url : '/liste',
				views : {
					'content-commande@generic.ecoleCommandes' : {
						templateUrl : '/ecole/commande/creation/commandesFamille.html',
						controller : 'EcoleCommandesEleveCtrl',
						resolve : {	additionalData : loadCommandesEcole }
					}
				}
			})
				// - visualisation d'une commande d'un élève
				.state('generic.ecoleCommandes.liste.visualisation', {
					url : '/{idCommande}',
					views : {
						'content-ecole-commande@generic.ecoleCommandes.liste' : {
							templateUrl : '/ecole/commande/creation/visualisationCommande.html',
							controller : 'EcoleCommandeVisualisationCtrl',
							resolve : {	additionalData : visualiserCommandeEleve }
						}
					}
				})
			// - visualisation des commandes Ecoles
			.state('generic.visualisationEcoleCommandes', {
				url : '/visualisationEcoleCommandes/{id}',
				views : {
					'content-body@' : {
						templateUrl : '/ecole/commande/visualisation/commandesEcole.html',
						controller : 'EcoleCommandesEcoleCtrl',
						resolve : {	additionalData : loadCommandeEcole }
					}
				}
			})	
				// - visualisation d'une commande d'un élève
				.state('generic.visualisationEcoleCommandes.visualisation', {
					url : '/{idCommande}',
					views : {
						'content-ecole-commande@generic.visualisationEcoleCommandes' : {
							templateUrl : '/ecole/commande/visualisation/visualisationCommande.html',
							controller : 'EcoleCommandeVisualisationCtrl',
							resolve : {	additionalData : visualiserCommandeEleve }
						}
					}
				})
		;
});

var loadData = function($q, $http, $stateParams, $timeout, Auth) {

	var deferred = $q.defer();

	// http://stackoverflow.com/questions/22209107/angularjs-ui-router-preload-http-data-before-app-loads
	$http.get('/ws/'+$stateParams.type+'/loadData/')
	.success(function(data, status, headers, config) {
		Auth.setUserData(data);
		$timeout(deferred.resolve, 0);
	});
	return deferred.promise;
};

var loadEcoleDescription = function($q, $timeout, $http, $location, $state, $stateParams, Auth) {

	var deferred = $q.defer();

	// http://stackoverflow.com/questions/22209107/angularjs-ui-router-preload-http-data-before-app-loads
	$http.get('/ws/ecole/loadDescription')
	.success(function(data, status, headers, config) {
		Auth.setUserEcoleData(data);
		$timeout(deferred.resolve, 0);
	});
	return deferred.promise;
};

var loadCommandeFamille = function($q, $http, $stateParams, $timeout, Auth) {

	var deferred = $q.defer();

	$http.get('/ws/famille/commande/get?identifiant=' + $stateParams.identifiant)
	.success(function(data, status, headers, config) {
		Auth.setUserCommandes(data);
		$timeout(deferred.resolve, 0);
	});
	
	return deferred.promise;
};



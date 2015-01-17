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
	//Page Accueil après login
	.state('generic', {
		url : '/{type}',
		views : {
			'content-header@' : {
				templateUrl : 'header.html',
				controller : function ($stateParams){ return $stateParams.type + 'Ctrl'; },
				resolve : {	additionalData : loadData }
			},
			'content-body@' : {
				templateUrl : function ($stateParams){ return '/' + $stateParams.type + '/profile.html'; },
				
			},
			'content-footer@' : {
				templateUrl : 'footer.html',
			}
		}
	})
	// Page famille
	.state('generic.commander', {
		url : '/famille/commande',
		views : {
			'content-body@' : {
				
				templateUrl : '/famille/commande/commande.html' 
			}
		}
	})
	// Page Commande 
	   
	    .state('generic.mode', {
	    	url : '/famille/commande/mode',
			views : {
				'content-body@' : {
					
					templateUrl : '/famille/commande/mode.html' 
				}
			}
	    })
	    .state('generic.validation', {
	    	url : '/famille/commande/validation',
			views : {
				'content-body@' : {
					templateUrl : '/famille/commande/validation.html'
				}
			}
	    })
	    .state('generic.payement', {
	        url: '/famille/commande/payement',
	        views : {
				'content-body@' : {
					
					templateUrl : '/famille/commande/payement.html' 
				}
			}
	    })
	    .state('generic.recapitulatif', {
	        url: '/famille/commande/recapitulatif',
	        views : {
				'content-body@' : {
					
					templateUrl : '/famille/commande/recapitulatif.html' 
				}
			}
	    })
	.state('generic.visualiser', {
    	url : '/famille/commande/{identifiant}',
		views : {
			'content-body@' :{
				templateUrl : '/famille/commande/visualisation.html',
				controller : 'FamilleCommandeVisualisationCtrl',
				resolve : {	additionalData : loadCommandeFamille }
			}	
		}
    })


	// Page ecole - liste des classes
	.state('classes', {
		url : '/ecole/classes/{id}?nom',
		views : {
			'contentInterne@' : {
				templateUrl : '/ecole/classes/list.html',
			}
		}
	})
	//Page Accueil après login
	.state('generic', {
		url : '/{type}',
		views : {
			'content@' : {
				templateUrl : function ($stateParams){  return 'accueil.html'; },
				controller : function ($stateParams){ return $stateParams.type + 'Ctrl'; },
				resolve : {	additionalData : loadData }
			},
			'contentInterne@' : {
				templateUrl : function ($stateParams){ return '/' + $stateParams.type + '/profile.html'; },
				
			}
		}
	});
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


var getCommandeFamille = function($q, $http, $stateParams, $timeout, Auth) {

	var deferred = $q.defer();

	$http.get('/ws/famille/commande/' + $stateParams.idCommande + '/get/')
	.success(function(data, status, headers, config) {
		Auth.setUserCommandes(data);
		$timeout(deferred.resolve, 0);
	});
	
	return deferred.promise;
};


var loadEcoleData = function($q, $timeout, $http, $location, $state, $stateParams, Auth) {

	var deferred = $q.defer();

	$http.get('/ws/ecole/loadData/' + $stateParams.identifiant)
	.success(function(data, status, headers, config) {
		Auth.setUserData(data);
		$timeout(deferred.resolve, 0);
	});
	return deferred.promise;
};

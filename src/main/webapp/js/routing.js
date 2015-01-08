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
			'content@' : {
				templateUrl : 'login.html',
				controller : 'LoginCtrl'
			}
		}
	})
	// Page famille
	.state('commander', {
		url : '/famille/commande',
		views : {
			'contentInterne@' : {
				
				templateUrl : '/famille/commande/commande.html' 
			}
		}
	})
	// Page Commande 
	   
	    .state('mode', {
	    	url : '/famille/commande/mode',
			views : {
				'contentInterne@' : {
					
					templateUrl : '/famille/commande/mode.html' 
				}
			}
	    })
	    .state('validation', {
	    	url : '/famille/commande/validation',
			views : {
				'contentInterne@' : {
					templateUrl : '/famille/commande/validation.html'
				}
			}
	    })
	    .state('payement', {
	        url: '/famille/commande/payement',
	        views : {
				'contentInterne@' : {
					
					templateUrl : '/famille/commande/payement.html' 
				}
			}
	    })
	    .state('recapitulatif', {
	        url: '/famille/commande/recapitulatif',
	        views : {
				'contentInterne@' : {
					
					templateUrl : '/famille/commande/recapitulatif.html' 
				}
			}
	    })
	.state('visualiser', {
    	url : '/famille/commande/{identifiant}',
		views : {
			'contentInterne@' :{
				templateUrl : '/famille/commande/visualisation.html',
				controller : 'FamilleCommandeVisualisationCtrl',
				resolve : {	additionalData : loadCommandeFamille }
			}	
		}
    })
	//Page Accueil après login
	.state('generic', {
		url : '/{type}',
		views : {
			'content@' : {
				templateUrl : function ($stateParams){  return 'banniere.html'; },
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


var loadCommandeFamille = function($q, $http, $stateParams, $timeout, Auth) {

	var deferred = $q.defer();

	$http.get('/ws/famille/commande/get?identifiant=' + $stateParams.identifiant)
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

//https://medium.com/opinionated-angularjs/techniques-for-authentication-in-angularjs-applications-7bbf0346acec
//http://stackoverflow.com/questions/22936865/handling-error-in-ui-routers-resolve-function-aka-statechangeerror-passing-d

myApp.value('urlDataHomeEcole', '/ws/ecole/loadData/');
myApp.value('urlDataHomeFamille', '/ws/famille/loadData/');

myApp.config(function($stateProvider, $urlRouterProvider) {
	// Set default route
	
	
	
	$urlRouterProvider.otherwise('/');

	// Declare states
	$stateProvider.state('home', {
		url : '/',
		views : {
			'content@' : {
				templateUrl : 'login.html',
				controller : 'LoginCtrl'
			}
		}
	}).state('generic', {
		url : '/{type}/{identifiant}',
		views : {
			'content@' : {
				templateUrl : function ($stateParams){
				    return '/' + $stateParams.type + '/profile.html';
				},
				controller : function ($stateParams){
				    return $stateParams.type + 'Ctrl';
				},
				resolve : {
					additionalData : loadData
				}
			}
		}
	});
});

var loadData = function($q, $http, $stateParams, $timeout, Auth) {

	var deferred = $q.defer();

	// http://stackoverflow.com/questions/22209107/angularjs-ui-router-preload-http-data-before-app-loads
	$http.get('/ws/'+$stateParams.type+'/loadData/' + $stateParams.identifiant)
	.success(function(data, status, headers, config) {
		Auth.setUserData(data);
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

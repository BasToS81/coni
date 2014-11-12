myApp.config(function($stateProvider, $urlRouterProvider) {
	// Set default route
	$urlRouterProvider.otherwise('/');

	// Declare states
	$stateProvider.state('home', {
		url : '/',
		 views: {
	            'content@':{
	            	templateUrl : 'login.html', 
	            	controller : 'LoginCtrl'
	            }
		 }
	}).state('familleHome', {
		url : '/famille/',
		views: {
            'content@':{
            	templateUrl : '/famille/profile.html',
				controller : 'FamilleCtrl',
				resolve : {
					familleData : loadFamilleData
				}
            }}
	});
});

var loadFamilleData = function($q, $timeout, $http, $location, $state, Auth) {

	var deferred = $q.defer();

	// http://stackoverflow.com/questions/22209107/angularjs-ui-router-preload-http-data-before-app-loads

	$http.get('/ws/famille/loadData/' + Auth.getUser().name).success(
			function(data, status, headers, config) {
				if (status !== 401) {
					Auth.setUserData(data);
					$timeout(deferred.resolve, 0);
				} else {
					Auth.setUserData(null);
					$timeout(deferred.reject, 0);
					$state.go('home');
				}
			}).error(function(data, status, headers, config) {
		Auth.setUserData(null);
	});

	return deferred.promise;
};

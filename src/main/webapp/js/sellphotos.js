var myApp = angular.module('ShellPhotosAngular', [ 'ui.router','ui.bootstrap'
]);

myApp.controller('GlobalCtrl', [ '$scope', function($scope) {
	$scope.utilisateur = {
		identifiant : "",
		codeAcces : "",
		ecole : "",
		role : ""
	};
}
]);

myApp.factory('myHttpInterceptor', function($q, $location) {
	return {
		response : function(response) {
			return response;
		},
		responseError : function(response) {
			if (response.status == 401) {
				$location.path('/').replace();
			}
			return $q.reject(response);
		}
	};
});

myApp.config([ '$httpProvider', function($httpProvider) {
	$httpProvider.interceptors.push('myHttpInterceptor');
}
]);

myApp.run([ '$rootScope', function($rootScope, $httpProvider) {

	$rootScope.$on('$stateChangeError', function(event, unfoundState, toParams, fromState, fromParams, error) {
		   console.log(unfoundState);
		   console.log(error);
	});
}
]);

myApp.service('Auth', function() {
	var user = window.user;
	var userData = '';
	var userCommandes = '';
	return {
		getUser : function() {
			return user;
		},
		setUser : function(newUser) {
			user = newUser;
		},
		isConnected : function() {
			return !!user;
		},
		getUserData : function() {
			return userData;
		},
		setUserData : function(newUserData) {
			userData = newUserData;
		},
		getUserCommandes : function() {
			return userCommandes;
		},
		setUserCommandes : function(newUserCommandes) {
			userCommandes = newUserCommandes;
		}
	};
});

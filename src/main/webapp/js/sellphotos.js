var myApp = angular.module('ShellPhotosAngular', [ 'ui.router' ]);

myApp.controller('GlobalCtrl', [ '$scope', function($scope) {
	$scope.utilisateur = {
		identifiant  : "", 
		codeAcces : "",
		school : "",
		roles : ""
	};
} ]);


myApp.service('Auth', function() {
	var user = window.user;
	var userData = '';
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
		}
	};
});


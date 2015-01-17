'use strict';

myApp.controller('LoginCtrl', [ '$scope', '$http', '$state', '$timeout', 'Auth', function($scope, $http, $state, $timeout, Auth) {
	
	$scope.username = '';
	$scope.password = '';
	$scope.errorMessage = '';
	
	$scope.login = function() {
		var data = "j_username=" + $scope.username + "&j_password="
				+ $scope.password + "&submit=Login";
		$http.post('/ws/login', data, {
					headers : {
						'Content-Type' : 'application/x-www-form-urlencoded',
					}
				})
				.success(
						function(data, status, headers, config) {

							$scope.utilisateur.identifiant = data.identifiant;
							
							
							var user = {
									role : data.role
							};
							Auth.setUser(user);

							var state = "";
							if (user.role == "ELEVE" ) {
								state = 'famille';
							}
							else if (user.role == "RESPONSABLE") {
								state = 'ecole';
							}

							/*
							 * Appel du bon profil ou rôle inconnu
							 */
							if (state == ""){
								$scope.errorMessage = 'Rôle inconnu : ' + user.role;
							}
							else {
								
								$state.go('generic', {type:state});
								
							}
						}
						)
				.error(
						function(data, status, headers, config) {
							if (status == 401) {
								$scope.errorMessage = 'Accès non autorisé !';
								$timeout(function() {
									$scope.errorMessage = '';
								}, 4000);
								
							}
						});
	};
	
	$scope.logout = function() {
		$http.get('/ws/logout')
		.success(
			function(data, status, headers, config) {
				disconnect(Auth, $state);
			})
		.error(
			function(data, status, headers, config) {
				disconnect(Auth, $state);
			});
	};
} ]);


function disconnect(Auth, $state) {
	Auth.setUserData(null);
	$state.go('home');
}
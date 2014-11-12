'use strict';

myApp.controller('LoginCtrl', [ '$scope', '$http', '$state', '$timeout', 'Auth', function($scope, $http, $state, $timeout, Auth) {
	
	$scope.username = '';
	$scope.password = '';
	$scope.errorMessage = '';
	
	$scope.login = function() {
		
		var data = "j_username=" + $scope.username + "&j_password="
				+ $scope.password + "&submit=Login";
		$http
				.post('/ws/login', data, {
					headers : {
						'Content-Type' : 'application/x-www-form-urlencoded',
					}
				})
				.success(
						function(data, status, headers, config) {
							var user = {
									name : data.name,
									school : data.school,
									roles : data.role
							};
							Auth.setUser(user);
							if (user.roles.indexOf("ROLE_FAMILLE") >= 0) {
								$timeout(function() {
									$state.go('familleHome');
								},0);
							}
						})
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
		$http.get('/ws/logout').success(
				function(data, status, headers, config) {
					console.info('logged out');
				});
	};
} ]);
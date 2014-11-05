var myApp = angular.module('ShellPhotosAngular', []);


function GlobalController($scope, $http, $timeout) {
	$scope.usertest = "";
	$scope.user = {name : "", school : "" };
};

function UserController($scope, $http, $timeout) {
	/*
	 * Lancement de la requête de test
	 */
	$scope.getUser = function() {
		var url = 'rest/user?name=' + $scope.user.name;
		$http.get(url, {
			timeout : 5000
		}).success(function(data, status, headers, config) {
			$scope.user.name = data.name;
			$scope.user.school = data.school
		}).error(function(data, status, headers, config) {
			alert("Impossible de trouver l'utilisateur : " + data)
		});

	};

};
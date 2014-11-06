var myApp = angular.module('ShellPhotosAngular', []);


myApp.controller('GlobalController', ['$scope',  function($scope) {
	$scope.usertest = "";
	$scope.user = {name : "", school : "" };
}
]);

myApp.controller('UserController', ['$scope', '$http', function($scope, $http) {
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

}
]);
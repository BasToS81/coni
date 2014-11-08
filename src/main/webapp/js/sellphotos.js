var myApp = angular.module('ShellPhotosAngular', []);


myApp.controller('GlobalController', ['$scope',  function($scope) {
	$scope.utilisateurtest = "";
	$scope.utilisateur = {identifiant : "", codeAcces : "" };
}
]);

myApp.controller('UtilisateurController', ['$scope', '$http', function($scope, $http) {
	/*
	 * Lancement de la requête de test
	 */
	$scope.getUtilisateur = function() {
		var url = 'rest/utilisateur?identifiant=' + $scope.utilisateur.identifiant;
		$http.get(url, {
			timeout : 5000
		}).success(function(data, status, headers, config) {
			$scope.utilisateur.identifiant = data.identifiant;
			$scope.utilisateur.codeAcces = data.codeAcces;
		}).error(function(data, status, headers, config) {
			alert("Impossible de trouver l'utilisateur : " + data)
		});

	};

}
]);
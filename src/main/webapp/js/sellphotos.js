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
			$scope.utilisateur.identifiant = "OK";
			$scope.utilisateur.codeAcces = "OK";
			
		}).error(function(data, status, headers, config) {
			alert("Impossible de trouver l'utilisateur : " + data)
		});

	};

}
]);

myApp.controller('FamilleController', ['$scope', '$http', function($scope, $http) {
	/*
	 * Lancement de la requête de test
	 */
	$scope.getFamille = function() {
		var url = 'rest/famille?identifiant=toto';
		
		$http.get(url, {
			timeout : 5000
		}).success(function(data, status, headers, config) {
			alert("etape2");
			$scope.utilisateur.identifiant = "famille";
		}).error(function(data, status, headers, config) {
			alert("Impossible de réaliser l'import des données : " + data)
		});

	};

}
]);


myApp.controller('ImportController', ['$scope', '$http', function($scope, $http) {
	/*
	 * Lancement de la requête de test
	 */
	$scope.getDonnnees = function() {
		var url = 'rest/import';
		alert("etape1");
		$http.get(url, {
			timeout : 5000
		}).success(function(data, status, headers, config) {
			alert("etape2");
			$scope.utilisateur.identifiant = "ok";
		}).error(function(data, status, headers, config) {
			alert("Impossible de réaliser l'import des données : " + data)
		});

	};

}
]);
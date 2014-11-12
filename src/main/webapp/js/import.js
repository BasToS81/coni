'use strict';



myApp.controller('ImportController', ['$scope', '$http', function($scope, $http) {
	/*
	 * Lancement de la requête de test
	 */
	$scope.getDonnees = function() {
		var url = 'public/rest/import';	
		
		$http.get(url, {
			timeout : 5000
		}).success(function(data, status, headers, config) {
			$scope.utilisateur.identifiant = "ok";
		}).error(function(data, status, headers, config) {
			alert("Impossible de réaliser l'import des données : " + status + ", data=" + data)
		});

	};

}
]);
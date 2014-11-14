'use strict';



myApp.controller('ImportController', ['$scope', '$http', function($scope, $http) {
	/*
	 * Lancement de la requête de test
	 */
	$scope.statusImport = '';
	
	$scope.getDonnees = function() {
		var url = 'public/rest/import';	
		
		$http.get(url, {
			timeout : 5000
		}).success(function(data, status, headers, config) {
			$scope.statusImport = data.result;
		}).error(function(data, status, headers, config) {
			$scope.statusImport = "Impossible de réaliser l'import des données : " + status + ", data=" + data;
		});

	};

}
]);
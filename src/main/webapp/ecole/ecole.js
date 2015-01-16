myApp.controller('EcoleCtrl', ['$scope', '$http', 'Auth', '$stateParams', function($scope, $http, Auth, $stateParams) {

	$scope.ecole=Auth.getUserData();
	
}
	
]);


myApp.controller('EcoleSyntheseCtrl', [ '$scope', '$http', 'Auth', '$stateParams', function($scope, $http, Auth, $stateParams) {
	$scope.classes = null;
	$scope.getSynthese = function() { 
		$http.get('/ws/ecole/synthese')
		.success(
				function(data, status, headers, config) {
					$scope.classes = data;
				})
		.error(
				function(data, status, headers, config) {
					$scope.errorMessage = "Erreur au chargement des données de l'école";
				});
	};
		
}]);

myApp.controller('EcoleClasseCtrl', [ '$scope', '$http', 'Auth', '$stateParams', function($scope, $http, Auth, $stateParams) {
	$scope.nomClasse;
	$scope.eleves = null;
	$scope.getSynthese = function() { 
		$scope.nomClasse = $stateParams.nom;
		$http.get('/ws/ecole/loadClasses/' + $stateParams.id)
		.success(
				function(data, status, headers, config) {
					$scope.eleves = data;
				})
		.error(
				function(data, status, headers, config) {
					$scope.errorMessage = "Erreur au chargement des données de la classe";
				});
	};
		
}]);




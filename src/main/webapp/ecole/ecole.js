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
		
	
	$scope.openCommande = function(identifiantClasse) {
		alert(idntifiantClasse);
		$state.go('generic.classesCommandes', { id : identifiantClasse });
	}
	
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



myApp.controller('EcoleClasseCommandeCtrl', ['$scope', '$http', '$state', 'Auth', '$stateParams', function($scope, $http, $state, Auth, $stateParams) {
	$scope.classe = Auth.getUserCommandes();
	$scope.commandesEleves = $scope.classe.commandeEleveSynthese
	$scope.tva=$scope.classe.tva;

	
	
	
}]);

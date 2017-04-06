
(function (ng) {
    var mod = ng.module("actividadModule", ['ui.router']);
    
    mod.constant("actividadesContext", "/actividades");
    mod.constant("paseosContext", "api/paseos");
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        var basePath = 'src/modules/actividades/';
        $urlRouterProvider.otherwise("/actividadesList");

            $stateProvider
               .state('actividades', {
                    url: '/actividades',
                    abstract: true,
                    parent: 'paseoDetail',
                    views: {
                        childrenView: {                       
                            resolve: {
                                paseos: ['$http', 'paseosContext', function ($http,paseosContext) {
                                        return $http.get( paseosContext );
                                    }],
                                actividades: ['$scope','$http', 'paseosContext','actividadesContext', function($scope,$http,paseosContext,actividadesContext){
                                        $scope.currentPaseo = paseos.data[$params.paseoId - 1];        
                                        return $http.get(paseosContext + '/'+ $scope.currentPaseo.id + actividadesContext);
                                    }]
                            },
                        templateUrl: basePath + 'actividades.html',
                        controller: ['$scope', 'paseos', 'actividades', '$stateParams', function ($scope, paseos, actividades, $params) {
                                $scope.actividadesRecords = actividades.data;
                            }]
                    }
                }

            })
                    .state('actividadesList', {
                url: '/list',
                parent: 'actividades',
                views: {
                    'listView': {
                        templateUrl: basePath + 'actividades.list.html'
                    }
                }
            })
                    .state('actividadDetail',{
                url: '/{actividadId:int}/detail',
                parent: 'actividades',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'actividades.detail.html',
                        controller: ['$scope', '$stateParams', function($scope, $stateParams) { $scope.currentActividad = $scope.actividadesRecords[$stateParams.actividadId-1]}]
                    }
                }});
                    
        }]);
})(window.angular);

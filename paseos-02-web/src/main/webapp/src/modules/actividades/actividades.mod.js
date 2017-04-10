(function (ng) {
    var mod = ng.module("actividadModule", ['paseosModule', 'ui.router']);

    mod.constant("paseosContext", "api/paseos");

    mod.constant("actividadesContext", "actividades");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/actividades/';
            $urlRouterProvider.otherwise("/actividadesList");

            $stateProvider.state('actividades', {
                url: '/actividades',
                abstract: true,
                parent: 'paseoDetail',
                resolve: {
                    actividades: ['$http', 'paseosContext', 'actividadesContext', '$stateParams', function ($http, paseosContext, actividadesContext, $params) {
                            return $http.get(paseosContext + '/' + $params.paseoId + '/' + actividadesContext);
                        }]
                },
                views: {
                    'childrenView': {
                        templateUrl: basePath + 'actividades.html'
                    }
                }
            }).state('actividadesList', {
                url: '/list',
                parent: 'actividades',
                views: {
                    'listView': {
                        templateUrl: basePath + 'actividades.list.html',
                        controller: ['$scope', 'actividades', function ($scope, actividades) {
                                $scope.actividadesRecords = actividades.data;
                            }]
                    }
                }
            }).state('actividadDetail', {
                url: '/{actividadId:int}/detail',
                parent: 'actividades',
                param: {
                    actividadId: null
                },
                resolve:  {
                    currentActividad: ['$http', 'paseosContext','actividadesContext', '$stateParams', function ($http, paseosContext, actividadesContext, $params) {
                            return $http.get(paseosContext+'/'+$params.paseoId+'/'+ actividadesContext+ '/'+ $params.actividadId);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'actividades.detail.html',
                        controller: ['$scope', 'currentActividad', function ($scope,  currentActividad) {
                                $scope.currentActividad = currentActividad.data;
                        }]
                    },
                    'listView': {
                        templateUrl: basePath + 'actividades.list.html',
                        controller: ['$scope', 'actividades', function ($scope, actividades) {
                                $scope.actividadesRecords = actividades.data;
                            }]
                    }
                }
            });
        }]);
})(window.angular);
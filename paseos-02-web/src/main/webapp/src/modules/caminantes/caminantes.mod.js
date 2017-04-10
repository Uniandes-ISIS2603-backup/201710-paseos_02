(function (ng) {
    var mod = ng.module("caminanteModule", ['ui.router']);

    mod.constant("caminantesContext", "api/caminantes");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            var basePath = 'src/modules/caminantes/';

            $urlRouterProvider.otherwise("/caminantesList");

            $stateProvider.state('caminantes', {
                url: '/caminantes',
                abstract: true,
                resolve: {
                    caminantes: ['$http', 'caminantesContext', function ($http, caminantesContext) {
                            return $http.get(caminantesContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'caminantes.html'
                    }
                }
            }).state('caminantesList', {
                url: '/list',
                parent: 'caminantes',
                views: {
                    'listView': {
                        templateUrl: basePath + 'caminantes.list.html',
                        controller: ['$scope', 'caminantes', function ($scope, caminantes) {
                                $scope.caminantesRecords = caminantes.data;
                            }]
                    }
                }
            }).state('caminanteDetail', {
                url: '/{caminanteId:int}/detail',
                parent: 'caminantes',
                param: {
                    caminanteId: null
                },
                resolve: {
                    currentCaminante: ['$http', 'caminantesContext', '$stateParams', function ($http, caminantesContext, $params) {
                            return $http.get(caminantesContext + '/' + $params.caminanteId);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'caminantes.detail.html',
                        controller: ['$scope', 'currentCaminante', function ($scope, currentCaminante) {
                                $scope.currentCaminante = currentCaminante.data;
                            }]
                    },
                    'listView': {
                        templateUrl: basePath + 'caminantes.list.html',
                        controller: ['$scope', 'caminantes', function ($scope, caminantes) {
                                $scope.caminantesRecords = caminantes.data;
                            }]
                    }
                }
            });
        }]);
})(window.angular);
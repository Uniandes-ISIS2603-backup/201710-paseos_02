(function (ng) {
    var mod = ng.module("caminanteModule", ['ui.router']);
   
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/caminantes/';
            $urlRouterProvider.otherwise("/caminantesList");

            $stateProvider.state('caminantes', {
                url: '/caminantes',
                abstract: true,
                resolve: {
                    caminantes: ['$http', function ($http) {
                            return $http.get('data/caminantes.json');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'caminantes.html',
                        controller: ['$scope', 'caminantes', function ($scope, caminantes) {
                                $scope.caminantesRecords = caminantes.data;
                            }]
                    }
                }
            }).state('caminantesList', {
                url: '/list',
                parent: 'caminantes',
                views: {
                    'listView': {
                        templateUrl: basePath + 'caminantes.list.html'
                    }
                }
            }).state('caminanteDetail', {
                url: '/{caminanteId:int}/detail',
                parent: 'caminantes',
                param: {
                    caminanteId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'caminantes.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'caminantes.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentCaminante = $scope.caminantesRecords[$params.caminanteId-1];
                            }]
                    }

                }

            });
        }]);
})(window.angular);

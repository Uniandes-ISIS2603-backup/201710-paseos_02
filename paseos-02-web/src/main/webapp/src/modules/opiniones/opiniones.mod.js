(function (ng) {
    var mod = ng.module('opinionesModule', ['ui.router']);

    mod.constant("paseosContext", "api/paseos");
    mod.constant('opinionesContext', 'api/opiniones');
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        var basePath = 'src/modules/opiniones/';
        $urlRouterProvider.otherwise('/opinionesList');
        $stateProvider
            .state('opiniones', {
                url: '/opiniones',
                abstract: true,
                parent: 'paseoDetail',
                views: {
                    childrenView: {
                        resolve: {
                            paseos: ['$http', function ($http) {
                                return $http.get(paseosContext);
                            }],
                            opiniones: ['$http', function ($http) {
                                return $http.get( opinionesContext );
                            }]
                        },
                        templateUrl: basePath + 'opiniones.html',
                        controller: ['$scope', 'paseos', 'opiniones', '$stateParams', function ($scope, paseos, opiniones, $params) {
                            $scope.currentPaseo = paseos.data[$params.paseoId - 1];
                            $scope.opinionesRecords = opiniones.data;
                        }]
                    }
                }

            })
            .state('opinionesList', {
                url: '/list',
                parent: 'opiniones',
                views: {
                    'listView': {
                        templateUrl: basePath + 'opiniones.list.html'
                    }
                }
            })
            .state('opinionDetail', {
                url: '/{opinionId:int}/detail',
                parent: 'opiniones',
                views: {
                    'listView': {
                                templateUrl: basePath + 'opiniones.list.html'
                            },
                    'detailView': {
                        templateUrl: basePath + 'opiniones.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                            $scope.currentOpinion = $scope.opinionesRecords[$params.opinionId - 1];
                        }]
                    }
                }});
    }]);
})(window.angular);
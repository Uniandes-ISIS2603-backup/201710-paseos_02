(function (ng) {
    var mod = ng.module("opinionesModule", ['ui.router']);

    mod.constant("paseosContext", "api/paseos");

    mod.constant("opinionesContext", "api/opiniones");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/opiniones/';
            $urlRouterProvider.otherwise("/opinionesList");

            $stateProvider.state('opiniones', {
                url: '/opiniones',
                abstract: true,
                parent: 'paseoDetail',
                views: {
                    'childrenView': {
                        templateUrl: basePath + 'opiniones.html'
                    }
                }
            }).state('opinionesList', {
                url: '/list',
                parent: 'opiniones',
                resolve: {
                    currentPaseo: ['$http', 'paseosContext', '$stateParams', function ($http, paseosContext, $stateParams) {
                            return $http.get(paseosContext + '/' + $stateParams.paseoId);
                        }]
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'opiniones.list.html',
                        controller: ['$scope', 'currentPaseo',function ($scope,currentPaseo) {
                                $scope.opinionesRecords = currentPaseo.data.opiniones;
                            }]
                    }
                }
            }).state('opinionDetail', {
                url: '/{opinionId:int}/detail',
                parent: 'opiniones',
                param: {
                    opinionId: null
                },
                resolve:  {
                    currentOpinion: ['$http', 'opinionesContext', '$stateParams', function ($http, opinionesContext, $params) {
                            return $http.get(opinionesContext+'/'+$params.opinionId);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'opiniones.detail.html',
                        controller: ['$scope', 'currentOpinion', function ($scope,  currentOpinion) {
                                $scope.currentOpinion = currentOpinion.data;
                            }]
                    },
                    'listView': {
                        templateUrl: basePath + 'opiniones.list.html',
                        controller: ['$scope', 'currentPaseo',function ($scope,currentPaseo) {
                                $scope.opinionesRecords = currentPaseo.data.opiniones;
                            }]
                    }
                }
            }).state('opinionesCreate', {
                url: '/create',
                parent: 'opiniones',
                views: {
                    'opinionView': {
                        controller: 'opinionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'opiniones.create.html'
                    }
                }
            });
        }]);
})(window.angular);
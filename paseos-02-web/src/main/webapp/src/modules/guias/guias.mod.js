(function (ng) {
    var mod = ng.module("guiaModule", ['ui.router']);

    mod.constant("guiasContext", "api/guias");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            var basePath = 'src/modules/guias/';

            $urlRouterProvider.otherwise("/guiasList");

            $stateProvider.state('guias', {
                url: '/guias',
                abstract: true,
                resolve: {
                    guias: ['$http', 'guiasContext', function ($http, guiasContext) {
                            return $http.get(guiasContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'guias.html'
                    }
                }
            }).state('guiasList', {
                url: '/list',
                parent: 'guias',
                views: {
                    'listView': {
                        templateUrl: basePath + 'guias.list.html',
                        controller: ['$scope', 'guias', function ($scope, guias) {
                                $scope.guiasRecords = guias.data;
                            }]
                    }
                }
            }).state('guiaDetail', {
                url: '/{guiaId:int}/detail',
                parent: 'guias',
                param: {
                    guiaId: null
                },
                resolve: {
                    currentGuia: ['$http', 'guiasContext', '$stateParams', function ($http, guiasContext, $params) {
                            return $http.get(guiasContext + '/' + $params.guiaId);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'guias.detail.html',
                        controller: ['$scope', 'currentGuia', '$http', 'guiasContext', '$state', function ($scope, currentGuia, $http, guiasContext, $state) {
                                $scope.currentGuia = currentGuia.data;

                                $scope.desactivarCuenta = function (recordR)
                                {
                                    console.log(123);
                                    recordR.cuentaActiva = false;
                                    return $http.put(guiasContext + "/" + recordR.id, recordR)
                                            .then(function () {

                                                $state.go('guiasList', null, {reload: true});
                                            });
                                };

                            }]
                    },
                    'listView': {
                        templateUrl: basePath + 'guias.list.html',
                        controller: ['$scope', 'guias', function ($scope, guias) {
                                $scope.guiasRecords = guias.data;
                            }]
                    }
                }
            }).state('paseoGuia', {
                url: '/guia',
                parent: 'paseoDetail',
                resolve: {
                    currentPaseo: ['$http', 'paseosContext', '$stateParams', function ($http, paseosContext, $stateParams) {
                            return $http.get(paseosContext + '/' + $stateParams.paseoId);
                        }],
                    currentGuia: ['$http', 'guiasContext', 'currentPaseo', function ($http, guiasContext, currentPaseo) {
                            return $http.get(guiasContext + '/' + currentPaseo.data.guia.id);
                        }]
                },
                views: {
                    'paseoGuiaView': {
                        templateUrl: basePath + 'guias.detail.html',
                        controller: ['$scope', 'currentGuia', function ($scope, currentGuia) {
                                $scope.currentGuia = currentGuia.data;
                            }]
                    }
                }
            }).state('guiaCreate', {
                url: '/create',
                parent: 'guias',
                views: {
                    'guiaView': {
                        controller: 'guiasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'guias.create.html'
                    }
                },
            }).state('guiaEdit', {
                url: '/{guiaId:int}/edit',
                parent: 'guias',
                views: {
                    'guiaView': {
                        controller: 'guiasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'guias.create.html'
                    }
                }
            });
        }]);
})(window.angular);
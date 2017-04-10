(function (ng) {
    var mod = ng.module("lugaresModule", ['ui.router']);

    mod.constant('lugaresContext', 'api/lugares');
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/lugares/';
            $urlRouterProvider.otherwise('/lugaresList');
            $stateProvider
                    .state('lugares', {
                        url: '/lugares',
                        abstract: true,
                        resolve: {
                            lugares: ['$http', 'lugaresContext', function ($http, lugaresContext) {
                                    return $http.get(lugaresContext);
                                }]
                        },
                        views: {
                            'mainView': {
                                templateUrl: basePath + 'lugares.html',
                                controller: ['$scope', 'lugares', function ($scope, lugares) {
                                        $scope.lugaresRecords = lugares.data;
                                    }]
                            }
                        }
                    })
                    .state('lugaresList', {
                        url: '/list',
                        parent: 'lugares',
                        views: {
                            'listView': {
                                templateUrl: basePath + 'lugares.list.html'
                            }
                        }
                    })
                    .state('lugarDetail', {
                        url: '/{lugarId:int}/detail',
                        parent: 'lugares',
                        param: {
                            lugarId: null
                        },
                        resolve: {
                            currentLugar: ['$http', 'lugaresContext', '$stateParams', function ($http, lugaresContext, $params) {
                                    return $http.get(lugaresContext + '/' + $params.lugarId);
                                }]
                        },
                        views: {
                            'listView': {
                                templateUrl: basePath + 'lugares.list.html'
                            },
                            'detailView': {
                                templateUrl: basePath + 'lugares.detail.html',
                                controller: ['$scope', '$sce', 'currentLugar', function ($scope, $sce, currentLugar) {
                                        $scope.trust = function (data) {
                                            return $sce.trustAsResourceUrl(data);
                                        },
                                                $scope.currentLugar = currentLugar.data;
                                    }]
                            }
                        }
                    })
                    .state('paseoLugar', {
                        url: '/lugar',
                        parent: 'paseoDetail',
                        resolve: {
                            currentLugar: ['$http', 'lugaresContext', 'currentPaseo', function ($http, lugaresContext, currentPaseo) {
                                    return $http.get(lugaresContext + '/' + currentPaseo.data.lugarDeDestino.id);
                                }]
                        },
                        views: {
                            'paseoLugarView': {
                                templateUrl: basePath + 'lugares.detail.html',
                                controller: ['$scope', '$sce', 'currentLugar', function ($scope, $sce, currentLugar) {
                                        $scope.trust = function (data) {
                                            return $sce.trustAsResourceUrl(data);
                                        },
                                                $scope.currentLugar = currentLugar.data;
                                    }]
                            }
                        }
                    });
        }]);
})(window.angular);


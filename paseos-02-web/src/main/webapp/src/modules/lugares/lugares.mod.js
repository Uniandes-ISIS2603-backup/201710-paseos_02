/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templat
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("lugaresModule", ['ui.router']);
    mod.constant("lugaresContext", "api/lugares");
   
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/lugares/';
            $urlRouterProvider.otherwise("/lugaresList");
            $stateProvider.state('reviews', {
                url: '/reviews',
                abstract: true,
                parent: 'lugaresDetail',
                resolve: {
                    reviews: ['$http', 'lugaresContext', 'reviewsContext', '$stateParams', function ($http, lugaresContext, reviewsContext, $params) {
                            return $http.get(lugaresContext + '/' + $params.lugarId + '/' + reviewsContext);
                        }]
                }}),
            $stateProvider.state('lugares', {
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
            }).state('lugaresList', {
                url: '/list',
                parent: 'lugares',
                views: {
                    'listView': {
                        templateUrl: basePath + 'lugares.list.html'
                    }
                }
            }).state('lugaresDetail', {
                url: '/{lugarId:int}/detail',
                parent: 'lugares',
                param: {
                    lugarId: null
                },
                resolve:  {
                    currentLugar: ['$http', 'lugaresContext', '$stateParams', function ($http, lugaresContext, $params) {
                            return $http.get(lugaresContext+'/'+$params.lugarId);
                        }]
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'lugares.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'lugares.detail.html',
                        controller: ['$scope', '$stateParams', '$sce', function ($scope, $params, $sce) {
                              $scope.trust = function(data) {
                                  return $sce.trustAsResourceUrl(data);
                              },
                                $scope.currentLugar = $scope.lugaresRecords[$params.lugarId];
                            }]
                    }

                }

            });
        }]);
})(window.angular);



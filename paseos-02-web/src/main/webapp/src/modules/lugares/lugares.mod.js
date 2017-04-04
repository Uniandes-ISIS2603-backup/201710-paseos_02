/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templat
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("lugaresModule", ['ui.router']);
   
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/lugares/';
            $urlRouterProvider.otherwise("/lugaresList");
 
            $stateProvider.state('lugares', {
                url: '/lugares',
                abstract: true,
                resolve: {
                    lugares: ['$http', function ($http) {
                            return $http.get('data/lugares.json');
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
                views: {
                    'listView': {
                        templateUrl: basePath + 'lugares.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'lugares.detail.html',
                        controller: ['$scope', '$stateParams', '$sce', function ($scope, $params, $sce) {
                              $scope.trust = function(data) {
                                  return $sce.trustAsResourceUrl(data)
                              }
                                $scope.currentLugar = $scope.lugaresRecords[$params.lugarId];
                            }]
                    }

                }

            });
        }]);
})(window.angular);



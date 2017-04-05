/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng){
    var mod = ng.module("paseosModule", ['ui.router']);

    mod.constant('paseosContext', 'api/paseos');
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        var basePath = 'src/modules/paseos/';
        $urlRouterProvider.otherwise('/paseosList');

        $stateProvider
            .state('paseos', {
                url: '/paseos',
                abstract: true,
                resolve: {
                    paseos: ['$http', function ($http) {
                        //  return $http.get( paseosContext )
                        return $http.get('data/paseos.json');
                    }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'paseos.html',
                        controller: ['$scope', 'paseos', function ($scope, paseos) {
                            $scope.paseosRecords = paseos.data;
                        }]
                    }
                }
            })
            .state('paseosList', {
                url: '/list',
                parent: 'paseos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'paseos.list.html'
                    }
                }
            })
            .state('paseoDetail', {
                url: '/{paseoId:int}/detail',
                parent: 'paseos',
                param: {
                    paseoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'paseos.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                            $scope.currentPaseo = $scope.paseosRecords[$params.paseoId - 1];
                        }]
                    }


                }
            });
    }]);
})(window.angular);



(function (ng) {
    var mod = ng.module('opinionesModule', ['ui.router'])
    mod.constant('opinionesContext', 'api/opiniones')
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        var basePath = 'src/modules/opiniones/'
        $urlRouterProvider.otherwise('/opinionesList')

        $stateProvider
            .state('opiniones', {
                url: '/opiniones',
                abstract: true,
                resolve: {
                    opiniones: ['$http', function ($http) {
                        //  return $http.get( opinionesContext )
                        return $http.get('data/opiniones.json')
                    }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'opiniones.html',
                        controller: ['$scope', 'opiniones', function ($scope, opiniones) {
                            $scope.opinionesRecords = opiniones.data
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
                param: {
                    opinionId: null
                },
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


                }
            })
    }])
})(window.angular)
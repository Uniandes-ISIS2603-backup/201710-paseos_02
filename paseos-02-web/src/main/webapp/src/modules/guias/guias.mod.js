(function (ng) {
    var mod = ng.module('guiaModule', ['ui.router'])
    mod.constant('guiasContext', 'api/guias')
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        var basePath = 'src/modules/guias/'
        $urlRouterProvider.otherwise('/guiasList')

        $stateProvider
            .state('guias', {
                url: '/guias',
                abstract: true,
                resolve: {
                    guias: ['$http', function ($http) {
                        //  return $http.get( guiasContext )
                        return $http.get('data/guias.json')
                    }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'guias.html',
                        controller: ['$scope', 'guias', function ($scope, guias) {
                            $scope.guiasRecords = guias.data
                        }]
                    }
                }
            })
            .state('guiasList', {
                url: '/list',
                parent: 'guias',
                views: {
                    'listView': {
                        templateUrl: basePath + 'guias.list.html'
                    }
                }
            })
            .state('guiaDetail', {
                url: '/{guiaId:int}/detail',
                parent: 'guias',
                param: {
                    guiaId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'guias.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'guias.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                            $scope.currentGuia = $scope.guiasRecords[$params.guiaId - 1];
                        }]
                    }


                }
            })
    }])
})(window.angular)




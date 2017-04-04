(function (ng) {
    var mod = ng.module("actividadModule", ['ui.router']);

    mod.constant("paseosContext", "api/paseos");

    mod.constant("actividadesContext", "actividades");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/actuvidades/';
            $urlRouterProvider.otherwise("/actividadesList");

            $stateProvider.state('actividades', {
                url: '/actividades',
                abstract: true,
                parent: 'paseoDetail',
                resolve: {
                    reviews: ['$http', 'paseosContext', 'actividadesContext', '$stateParams', function ($http, paseosContext, actividadesContext, $params) {
                            return $http.get(paseosContext + '/' + $params.paseoId + '/' + actividadesContext);
                        }]
                },
                views: {
                    'childrenView': {
                        templateUrl: basePath + 'actividades.html'
                    }
                },
            }).state('actividadesList', {
                url: '/list',
                parent: 'actividades',
                views: {
                    'listView': {
                        templateUrl: basePath + 'actvidades.list.html',
                        controller: ['$scope', 'actividades', function ($scope, actividades) {
                                $scope.actividadesRecords = actividades.data;
                            }]
                    }
                }
            });
        }]);
})(window.angular);
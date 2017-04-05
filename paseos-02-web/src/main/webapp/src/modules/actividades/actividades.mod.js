(function (ng) {
    var mod = ng.module("actividadModule", ['ui.router']);
    mod.constant("actividadesContext", "api/actividades");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/actividades/';
            $urlRouterProvider.otherwise("/actividadesList");

            $stateProvider.state('actividades', {
                url: '/actividades',
                abstract: true,
                parent: 'paseoDetail',
                views: {
                    childrenView: {                       
                        resolve: {
                            paseos: ['$http', function ($http) {
                                    return $http.get('data/paseos.json');
                                }],
                            actividades: ['$http', function ($http) {
                                    return $http.get('data/actividades.json');
                                }]
                        },
                        templateUrl: basePath + 'actividades.html',
                        controller: ['$scope', 'paseos', 'actividades', '$stateParams', function ($scope, paseos, actividades, $params) {
                                $scope.currentPaseo = paseos.data[$params.paseoId - 1];
                                $scope.actividadesRecords = actividades.data;
                            }]
                    }
                }

            }).state('actividadesList', {
                url: '/list',
                parent: 'actividades',
                views: {
                    'listView': {
                        templateUrl: basePath + 'actividades.list.html'
                    }
                }
            });
        }]);
})(window.angular);
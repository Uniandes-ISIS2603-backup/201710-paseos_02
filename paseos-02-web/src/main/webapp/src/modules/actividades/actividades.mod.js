(function (ng) {
    var mod = ng.module("actividadModule", ['ui.router']);
 
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/actividades/';
            $urlRouterProvider.otherwise("/actividadesList");
            $stateProvider.state('actividadesList', {
                url: '/actividades/list',
            
                 resolve: {
                    actividades: ['$http', function ($http) {
                            return $http.get('data/actividades.json'); 
                        }]
                },
                templateUrl: basePath + 'actividades.list.html',
             
                controller: ['$scope', 'actividades', function ($scope, paseos) {
                        $scope.actividadesRecords = paseos.data;
                    }]              
            });
        }
    ]);
})(window.angular);


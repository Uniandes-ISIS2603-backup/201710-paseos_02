(function (ng) {
    // Definición del módulo 
    var mod = ng.module("calificacionModule", ['guiaModule', 'ui.router']);
 
   // Configuración de los estados del módulo
    mod.constant("guiasContext", "api/guias");
    
    mod.constant("calificacionesContext", "calificaciones");
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/calificaciones/';
            // Mostrar la lista de caminantes será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/calificacionesList");
            // Definición del estado 'caminantesList' donde se listan los caminantes
            $stateProvider.state('calificaciones', {
                url: '/calificaciones',
                abstract: true,
                parent: 'guiaDetail',
                resolve: {
                    calificaciones: ['$http', 'guiasContext', 'calificacionesContext', '$stateParams', function ($http, guiasContext, calificacionesContext, $params) {
                            return $http.get(guiasContext + '/' + $params.guiaId + '/' + calificacionesContext);
                        }]
                },
                views: {
                    'childrenView': {
                        templateUrl: basePath + 'calificaciones.html'
                    }
                },
            }).state('calificacionesList', {
                url: '/list',
                parent: 'calificaciones',
                views: {
                    'listView': {
                        templateUrl: basePath + 'calificaciones.list.html',
                        controller: ['$scope', 'calificaciones', function ($scope, calificaciones) {
                                $scope.calificacionesRecords = calificaciones.data;
                            }]
                    }
                }
            });
        }]);
})(window.angular);
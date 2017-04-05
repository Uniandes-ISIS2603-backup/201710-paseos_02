(function (ng) {
    // Definición del módulo
    var mod = ng.module("inscripcionModule", ['caminanteModule', 'ui.router']);
 
   // Configuración de los estados del módulo
    mod.constant("caminantesContext", "api/caminantes");
    
    mod.constant("inscripcionesContext", "inscripciones");
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/inscripciones/';
            // Mostrar la lista de caminantes será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/inscripcionesList");
            // Definición del estado 'caminantesList' donde se listan los caminantes
            $stateProvider.state('inscripciones', {
                url: '/inscripciones',
                abstract: true,
                parent: 'inscripcionDetail',
                views: {
                    'childrenView': {
                        templateUrl: basePath + 'inscripciones.html'
                    }
                }
            }).state('inscripcionesList', {
                url: '/list',
                parent: 'inscripciones',
                views: {
                    'listView': {
                        templateUrl: basePath + 'inscripciones.list.html',
                        controller: ['$scope', 'inscripciones', function ($scope, inscripciones) {
                                $scope.inscripcionesRecords = inscripciones.data;
                            }]
                    }
                }
            });
        }]);
})(window.angular);
(function (ng) {
    // Definición del módulo
    var mod = ng.module("inscripcionModule", ['ui.router']);
 
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
                parent: 'caminanteDetail',
            }).state('inscripcionesList', {
                url: '/list',
                parent: 'inscripciones',
                views: {
                    'listView@caminantes': {
                        templateUrl: basePath + 'inscripciones.list.html',
                        controller: ['$scope', 'currentCaminante', function ($scope, currentCaminante) {
                                $scope.inscripcionesRecords = currentCaminante.data.inscripciones;
                            }]
                    }
                }
            }).state('inscripcionDetail', {
                url: '/{inscripcionId:int}/detail',
                parent: 'inscripciones',
                param: {
                    inscripcionId: null
                },
                views: {
                    'detailView@caminantes': {
                        templateUrl: basePath + 'inscripciones.detail.html',
                        controller: ['$scope', '$stateParams', '$filter', 'currentCaminante', function ($scope, $params, $filter,currentCaminante) {
                               $scope.inscripcionesRecords = currentCaminante.data.inscripciones;
                               var found = $filter('filter')( $scope.inscripcionesRecords, {id: $params.inscripcionId}, true);
                                if (found.length) {
                                    $scope.currentInscripcion = found[0];
                                } else {
                                    $scope.currentInscripcion = '';
                                }
                            }]
                    },
                    'listView@caminantes': {
                        templateUrl: basePath + 'inscripciones.list.html',
                        controller: ['$scope', 'currentCaminante', function ($scope, currentCaminante) {
                                $scope.inscripcionesRecords = currentCaminante.data.inscripciones;
                            }]
                    }
                }
            });;
        }]);
})(window.angular);
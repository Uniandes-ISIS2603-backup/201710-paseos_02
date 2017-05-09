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
                parent: 'caminanteDetail',
                resolve: {
                    inscripciones: ['$http', 'caminantesContext', 'inscripcionesContext', '$stateParams', function ($http, caminantesContext, inscripcionesContext, $params) {
                            return $http.get(caminantesContext + '/' + $params.caminanteId + '/' + inscripcionesContext);
                        }]
                },
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
            }).state('inscripcionDetail', {
                url: '/{inscripcionId:int}/detail',
                parent: 'inscripciones',
                param: {
                    inscripcionId: null
                },
                resolve: {
                    currentInscripcion: ['$http', 'caminantesContext', '$stateParams', function ($http, caminantesContext, $params) {
                            return $http.get(caminantesContext + '/' + $params.caminanteId);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'caminantes.detail.html',
                        controller: ['$scope', 'currentCaminante', function ($scope, currentCaminante) {
                                $scope.currentCaminante = currentCaminante.data;
                            }]
                    },
                    'listView': {
                        templateUrl: basePath + 'caminantes.list.html',
                        controller: ['$scope', 'caminantes', function ($scope, caminantes) {
                                $scope.caminantesRecords = caminantes.data;
                            }]
                    }
                }
            });;
        }]);
})(window.angular);
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
                url: 'caminantes/{caminanteId:int}/inscripciones',
                abstract: true,
                param:{
                    caminanteId: null
                },
                resolve: {
                    currentCaminante:['$http', 'caminantesContext', '$stateParams', function($http, caminantesContext, $params){
                                return $http.get(caminantesContext + '/' + $params.caminanteId);
                            }],
                    inscripciones: ['$http', 'caminantesContext', 'inscripcionesContext', '$stateParams', function ($http, caminantesContext, inscripcionesContext, $params) {
                            return $http.get(caminantesContext + '/' + $params.caminanteId + '/' + inscripcionesContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'inscripciones.html',
                        controller:['$scope', 'inscripciones', 'currentCaminante', function ($scope, inscripciones, currentCaminante){
                                $scope.currentCaminante= currentCaminante.data;
                                $scope.inscripcionesRecords = inscripciones.data;
                        }]
                    }
                }
            }).state('inscripcionesList', {
                url: '/list',
                parent: 'inscripciones',
                views: {
                    'listView': {
                        templateUrl: basePath + 'inscripciones.list.html'
                        
                    }
                }
            }).state('inscripcionDetail', {
                url: '/{inscripcionId:int}/detail',
                parent: 'inscripciones',
                param: {
                    inscripcionId: null
                },
                resolve: {
                    currentInscripcion: ['$http', 'caminantesContext', 'inscripcionesContext', '$stateParams', function ($http, caminantesContext,inscripcionesContext, $params) {
                            return $http.get(caminantesContext + '/' + $params.caminanteId + '/' + inscripcionesContext + '/' + $params.inscripcionId);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'inscripciones.detail.html',
                        controller: ['$scope', 'currentInscripcion', function ($scope,currentInscripcion) {
                               $scope.currentInscripcion = currentInscripcion.data;
                            }]
                    }
                   
                }
            }).state('inscripcionCreate', {
                url: '/create',
                parent: 'inscripciones',
                views: {
                    'createView': {
                        templateUrl: basePath + 'inscripciones.detail.html'  
                    }
                   
                }
            });
        }]);
})(window.angular);
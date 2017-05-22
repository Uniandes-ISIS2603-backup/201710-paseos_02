(function (ng) {
    // Definición del módulo 
    var mod = ng.module("calificacionModule", ['ui.router']);
 
    mod.constant("guiasContext", "api/guias");
    
    mod.constant("calificacionesContext", "calificaciones");
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/calificaciones/';
            // Mostrar la lista de caminantes será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/calificacionesList");
            // Definición del estado 'caminantesList' donde se listan los caminantes
            $stateProvider.state('calificaciones', {
                url: '/calificaciones/{guiaId:int}/calificaciones',
                abstract: true,
                param: {
                    guiaId: null
                },
                resolve: {
                    currentGuia: ['$http', 'guiasContext', '$stateParams', function ($http, guiasContext, $params) {
                            return $http.get(guiasContext + '/' + $params.guiaId);
                        }],
                    calificaciones: ['$http', 'guiasContext', 'calificacionesContext', '$stateParams', function ($http, guiasContext, calificacionesContext, $params) {
                            return $http.get(guiasContext + '/' + $params.guiaId + '/' + calificacionesContext);
                        }]
                },
                views: {
                    'mainView': {
                        controller: 'calificacionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'calificaciones.html'
                    }
                }
            }).state('calificacionesList', {
                url: '/list',
                parent: 'calificaciones',
                views: {
                    'listView': {
                        controller: 'calificacionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'calificaciones.list.html'
                    }
                }
            }).state('calificacionDetail', {
                url: '/{guiaId:int}/detail',
                parent: 'calificaciones',
                views: {
                    'listView': {
                        controller: 'calificacionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'calificaciones.list.html'

                    },
                    'detailView': {
                        controller: 'calificacionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'calificaciones.detail.html'
                    }
                }
            }).state('calificacionesCreate', {
                url: '/create',
                parent: 'calificaciones',
                views: {
                    'nuevoView': {
                        controller: 'calificacionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'calificaciones.create.html'

                    }
                }
            }).state('calificacionesEdit', {
                url: '/{guiaId:int}/edit',
                parent: 'calificaciones',
                views: {
                    'nuevoView': {
                        controller: 'calificacionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'calificaciones.create.html'

                    },
                    'detailView': {
                        controller: 'calificacionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'calificaciones.detail.html'
                    }
                }
            });
        }]);
})(window.angular);
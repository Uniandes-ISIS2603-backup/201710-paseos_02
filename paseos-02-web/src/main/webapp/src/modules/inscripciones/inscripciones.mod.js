(function (ng) {
    var mod = ng.module('inscripcionModule', ['ui.router'])
    mod.constant('inscripcionContext', 'api/inscripcion')
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        var basePath = 'src/modules/inscripciones/'
        $urlRouterProvider.otherwise('/inscripcionesList')

        $stateProvider
            .state('inscripciones', {
                url: '/inscripciones',
                abstract: true,
                resolve: {
                    inscripciones: ['$http', function ($http) {
                        //  return $http.get( guiasContext )
                        return $http.get('data/inscripciones.json')
                    }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'inscripciones.html',
                        controller: ['$scope', 'inscripciones', function ($scope, inscripciones) {
                            $scope.inscripcionesRecords = inscripciones.data
                        }]
                    }
                }
            })
            .state('inscripcionesList', {
                url: '/list',
                parent: 'inscripciones',
                views: {
                    'listView': {
                        templateUrl: basePath + 'inscripciones.list.html'
                    }
                }
            })
            .state('inscripcionDetail', {
                url: '/{inscripcionId:int}/detail',
                parent: 'inscripciones',
                param: {
                    inscripcionId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'inscripciones.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                            $scope.currentInscripcion = $scope.inscripcionesRecords[$params.inscripcionId - 1]
                        }]
                    }
                }
            })
    }])
})(window.angular)
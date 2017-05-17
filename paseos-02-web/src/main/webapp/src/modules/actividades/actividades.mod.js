(function (ng) {
    var mod = ng.module("actividadModule", ['paseosModule', 'ui.router']);

    mod.constant("paseosContext", "api/paseos");

    mod.constant("actividadesContext", "actividades");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/actividades/';
            $urlRouterProvider.otherwise("/actividadesList");

            $stateProvider.state('actividades', {
                url: '/actividades',
                abstract: true,
                parent: 'paseoDetail',
                resolve: {
                    actividades: ['$http', 'paseosContext', 'actividadesContext', '$stateParams', function ($http, paseosContext, actividadesContext, $params) {
                            return $http.get(paseosContext + '/' + $params.paseoId + '/' + actividadesContext);
                        }]
                },
                views: {
                    'childrenView': {
                        templateUrl: basePath + 'actividades.html'
                    }
                }
            }).state('actividadesList', {
                url: '/list',
                parent: 'actividades',
                views: {
                    'listView': {
                        templateUrl: basePath + 'actividades.list.html',
                        controller: ['$scope', 'actividades','$http', function ($scope, actividades, $http) {
                                $scope.actividadesRecords = actividades.data;
                                
                                
                                $scope.borrarActividad = function(index){
                                    var act = $scope.actividadesRecords[index];
                                    $http.delete('api/paseos/'+$scope.currentPaseo.id +'/actividades/'+act.id).
                                            then(function(response){
                                                $scope.actividadesRecords.splice(index,1);
                                    })
                                    
                                }
                                
                            }]
                    }
                }
            }).state('actividadesFormulario', {
                url: '/formulario',
                parent: 'actividades',
                views: {
                    'listView': {
                        templateUrl: basePath + 'actividades.formulario.html',
                        controller: ['$http','$scope',
                            function ($http,$scope) {
                                                         
                                $scope.agregarActividad = function(){
                                    
                                    console.log('mae');
                                    
                                    var nombreA = document.getElementById('nombreActividad').value;
                                    var descripcionA = document.getElementById('descripcionActividad').value;
                                    var duracionA = document.getElementById('duracionActividad').value;
                                    var equipamientoA = document.getElementById('equipamientoActividad').value;
                                    var objActividad = {
                                        nombre: nombreA,
                                        descripcion: descripcionA,
                                        duracion: duracionA,
                                        equipamiento: equipamientoA.split(","),
                                        paseoEcologico:{
                                            id: $scope.currentPaseo.id
                                        }
                                    };
                                    $http.post('api/paseos/'+$scope.currentPaseo.id +'/actividades', objActividad);
                                };
                            }]
                    }
                }
            }).state('actividadesFormularioPut', {
                url: '/{actividadId:int}/formulario',
                parent: 'actividades',
                 param: {
                    actividadId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'actividades.formulario.put.html',
                        controller: ['$http','$scope', '$stateParams','$state',
                            function ($http,$scope, $params,$state) {
                                                         
                                $scope.actualizarActividad = function(){
                                    
                                    var nombreA = document.getElementById('nombreActividadPut').value;
                                    var descripcionA = document.getElementById('descripcionActividadPut').value;
                                    var duracionA = document.getElementById('duracionActividadPut').value;
                                    var equipamientoA = document.getElementById('equipamientoActividadPut').value;
                                    var objActividad = {
                                        nombre: nombreA,
                                        descripcion: descripcionA,
                                        duracion: duracionA,
                                        equipamiento: equipamientoA.split(","),
                                        paseoEcologico:{
                                            id: $scope.currentPaseo.id
                                        }
                                    };
                                    
                                    $http.put('api/paseos/'+$scope.currentPaseo.id +'/actividades/'+$params.actividadId, objActividad);
                                    $state.go('actividadesList',{},{reload:true});
                                    
                                };
                                
                                
                            }]
                    }
                }
            }).state('actividadDetail', {
                url: '/{actividadId:int}/detail',
                parent: 'actividades',
                param: {
                    actividadId: null
                },
                resolve:  {
                    currentActividad: ['$http', 'paseosContext','actividadesContext', '$stateParams', function ($http, paseosContext, actividadesContext, $params) {
                            return $http.get(paseosContext+'/'+$params.paseoId+'/'+ actividadesContext+ '/'+ $params.actividadId);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'actividades.detail.html',
                        controller: ['$scope', 'currentActividad', function ($scope,  currentActividad) {
                                $scope.currentActividad = currentActividad.data;
                        }]
                    },
                    'listView': {
                        templateUrl: basePath + 'actividades.list.html',
                        controller: ['$scope', 'actividades', function ($scope, actividades) {
                                $scope.actividadesRecords = actividades.data;
                            }]
                    }
                }
            });
        }]);
})(window.angular);
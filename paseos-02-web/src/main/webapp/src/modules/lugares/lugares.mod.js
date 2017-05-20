(function (ng) {
    var mod = ng.module("lugaresModule", ['ui.router']);

    mod.constant('lugaresContext', 'api/lugares');
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/lugares/';
            $urlRouterProvider.otherwise('/lugaresList');
            $stateProvider
                    .state('lugares', {
                        url: '/lugares',
                        abstract: true,
                        resolve: {
                            lugares: ['$http', 'lugaresContext', function ($http, lugaresContext) {
                                    return $http.get(lugaresContext);
                                }]
                        },
                        views: {
                            'mainView': {
                                templateUrl: basePath + 'lugares.html',
                                controller: ['$scope', 'lugares', function ($scope, lugares) {
                                        $scope.lugaresRecords = lugares.data;
                                    }]
                            }
                        }
                    })
                    .state('lugaresList', {
                        url: '/list',
                        parent: 'lugares',
                        views: {
                            'listView': {
                                templateUrl: basePath + 'lugares.list.html',
                                controller: ['$scope', 'lugares','$http', function ($scope, lugares, $http) {
                                $scope.lugaresRecords = lugares.data;
//                                
//                                
                                $scope.borrarlugar = function(h_id){
                                    console.log('mae');
                                    console.log('mae');
                                    $http.delete('api/lugares/'+h_id);
                                     }
//                                
                            }]
                            }
                        }
                    })
                    .state('lugarDetail', {
                        url: '/{lugarId:int}/detail',
                        parent: 'lugares',
                        param: {
                            lugarId: null
                        },
                        resolve: {
                            currentLugar: ['$http', 'lugaresContext', '$stateParams', function ($http, lugaresContext, $params) {
                                    return $http.get(lugaresContext + '/' + $params.lugarId);
                                }]
                        },
                        views: {
//                            'listView': {
//                                templateUrl: basePath + 'lugares.list.html'
//                            },
                            'detailView': {
                                templateUrl: basePath + 'lugares.detail.html',
                                controller: ['$scope', '$sce', 'currentLugar', function ($scope, $sce, currentLugar) {
                                        $scope.trust = function (data) {
                                            return $sce.trustAsResourceUrl(data);
                                        },
                                                $scope.currentLugar = currentLugar.data;
                                    }]
                            }
                        }
                    })
                    .state('paseoLugar', {
                        url: '/lugar',
                        parent: 'paseoDetail',
                        resolve: {
                            currentLugar: ['$http', 'lugaresContext', 'currentPaseo', function ($http, lugaresContext, currentPaseo) {
                                    return $http.get(lugaresContext + '/' + currentPaseo.data.lugarDeDestino.id);
                                }]
                        },
                        views: {
                            'paseoLugarView': {
                                templateUrl: basePath + 'lugares.detail.html',
                                controller: ['$scope', '$sce', 'currentLugar', function ($scope, $sce, currentLugar) {
                                        $scope.trust = function (data) {
                                            return $sce.trustAsResourceUrl(data);
                                        },
                                                $scope.currentLugar = currentLugar.data;
                                    }]
                            }
                        }
                    })
                    .state('lugaresActualizar', {
                url: '/{lugarId:int}/actualizar',
                parent: 'lugares',
                 param: {
                    lugarId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'lugares.Actualizar.html',
                        controller: ['$http','$scope', '$stateParams','$state',
                            function ($http,$scope, $params,$state) {
                                                         
                                $scope.actualizarlugar = function(){
                                    
                                    var nombreA = $('#nombreInput').val();
                                    var imagenA = $('#imagenInput').val();;
                                    var linkA = $('#linkInput').val();;
                                    var direccionA = $('#direccionInput').val();;
                                    var objLugar = {
                                        nombre: nombreA,
                                        imagen: imagenA,
                                        linkGoogleMaps: linkA,
                                        direccion:direccionA
                                    };
                                    
                                    $http.put('api/lugares/'+$params.lugarId, objLugar);
                                    $state.go('lugaresList',{},{reload:true});
                                    
                                };
                                
                                
                            }]
                    }
                }
            })
                .state('lugaresCreate', {
                url: '/create',
                parent: 'lugares',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'lugares.create.html',
                        controller: ['$http','$scope',
                            function ($http,$scope) {
                                                         
                                $scope.crearLugar = function(){
                                    
                                    console.log('mae');
                                    

                                    var nombreA = $('#nombreInput').val();
                                    var idA = $('#idInput').val();
                                    var imagenA = $('#imagenInput').val();;
                                    var linkA = $('#linkInput').val();;
                                    var direccionA = $('#direccionInput').val();;
                                    var objLugar = {
                                        nombre: nombreA,
                                        id: idA,
                                        imagen: imagenA,
                                        linkGoogleMaps: linkA,
                                        direccion:direccionA
                                    };
                                    $http.post('api/lugares', objLugar);
                                };
                            }]
                    }
                }
            });      
        }]);
//    mod.controller('createLugarCurrent'['$scope','$http','lugarContext'])
//    {
//        var data={
//            "id":"2001",
//            "nombre":"Plaza los ginazoles",
//            "imagen":"http://www.1001consejos.com/wp-content/uploads/2012/05/canon-sumidero-chiapas.jpg",
//            "linkGoogleMaps":"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3977.3916928254157!2d-74.3568116861062!3d4.52324899671349!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0xf1901677fd6bfb2d!2sVarsana+Eco+Yoga+Village!5e0!3m2!1ses!2ses!4v1494984885840"
//        }
//        $scope.createLugar=funtion()
//        {
//            $http.post(lugarContext,data)
//                    .then(funtion(response))
//            {
//                console.log("succes");
//            },
//                    funtion(response)
//            {
//                console.log("fallo");
//                
//            });
//        }
//    }
})(window.angular);


/* 
 * The MIT License
 *
 * Copyright 2017 Treamwork - Team software development - Los Andes University
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

(function (ng) {
    var mod = ng.module("caminanteModule", ["ngMessages",'ui.bootstrap']);

    mod.constant("caminantesContext", "api/caminantes");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/caminantes/';
            $urlRouterProvider.otherwise("/caminantesList");

            $stateProvider.state('caminantes', {
                url: '/caminantes',
                abstract: true,
                resolve: {
                    caminantes: ['$http', 'caminantesContext', function ($http, caminantesContext) {
                            return $http.get(caminantesContext);
                        }]
                },
                views: {
                    'mainView': {
                        controller: 'caminantesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'caminantes.html'
                    }
                }
            }).state('caminantesList', {
                url: '/list',
                parent: 'caminantes',
                views: {
                    'listView': {
                        controller: 'caminantesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'caminantes.list.html'
                    }
                }
            }).state('caminanteDetail', {
                url: '/{caminanteId:int}/detail',
                parent: 'caminantes',                
                views: {
                    'detailView': {
                        controller: 'caminantesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'caminantes.detail.html'          
                    },
                    'listView': {
                        controller: 'caminantesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'caminantes.list.html'
                    }
                }
            }).state('caminanteCreate', {
                url: '/create',
                parent: 'caminantes',
                views: {
                    'caminanteView': {
                        controller: 'caminantesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'caminantes.create.html'
                    }
                }
            });
        }]);
})(window.angular);




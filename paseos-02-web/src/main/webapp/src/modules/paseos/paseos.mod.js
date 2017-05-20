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
    var mod = ng.module("paseosModule", ["ngMessages",'ui.bootstrap']);

    mod.constant("paseosContext", "api/paseos");
    mod.constant("guiasContext", "api/guias");
    mod.constant("lugaresContext", "api/lugares");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/paseos/';
            $urlRouterProvider.otherwise("/paseosList");

            $stateProvider.state('paseos', {
                url: '/paseos',
                abstract: true,
                resolve: {
                    paseos: ['$http', 'paseosContext', function ($http, paseosContext) {
                            return $http.get(paseosContext);
                        }]
                },
                views: {
                    'mainView': {
                        controller: 'paseosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'paseos.html'
                    }
                }
            }).state('paseosList', {
                url: '/list',
                parent: 'paseos',
                views: {
                    'listView': {
                        controller: 'paseosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'paseos.list.html'
                    }
                }
            }).state('paseoDetail', {
                url: '/{paseoId:int}/detail',
                parent: 'paseos',                
                views: {
                    'detailView': {
                        controller: 'paseosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'paseos.detail.html'          
                    }
                }
            }).state('paseoFicha', {
                url: '/fichaTecnica',
                parent: 'paseoDetail',
                views: {
                    'fichaTecnicaView': {
                        controller: 'paseosCtrl',
                        controllerAs: 'ctrl',                      
                        templateUrl: basePath + 'paseos.fichaTecnica.html'                        
                    }
                }
            }).state('paseoCreate', {
                url: '/create',
                parent: 'paseos',
                views: {
                    'paseoView': {
                        controller: 'paseosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'paseos.create.html'
                    }
                }
            }).state('paseoEdit', {
                url: '/{paseoId:int}/edit',
                parent: 'paseos',
                views: {
                    'paseoView': {
                        controller: 'paseosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'paseos.create.html'
                    } 
                }
            });
        }]);
})(window.angular);






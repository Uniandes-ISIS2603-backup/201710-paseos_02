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
    var mod = ng.module("paseosModule");
    
    mod.controller("paseosCtrl", ['$scope', '$state', '$stateParams', '$http', 'paseosContext', 'guiasContext', 'lugaresContext', '$filter',
        function ($scope, $state, $stateParams, $http, paseosContext, guiasContext, lugaresContext, $filter) {

            // inicialmente el listado de paseos está vacio
            $scope.records = {};
            
            // carga los paseos
            $http.get(paseosContext).then(function (response) {
                $scope.records = response.data;
            }, responseError);
            
            $scope.guiaPaseoDetail = {};

            // el controlador recibió un paseoId ??
            // revisa los parámetros (ver el :paseoId en la definición de la ruta)
            if ($stateParams.paseoId !== null && $stateParams.paseoId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.paseoId;
                // obtiene el dato del recurso REST
                $http.get(paseosContext + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentRecord = response.data;     
                            $scope.guiaPaseoDetail = response.data.guia;                           
                        }, responseError);
                        
                if(document.getElementById('transporte') !== null)
                {
                    document.getElementById('transporte').checked = $scope.currentRecord.hayTransporte;
                }
                // el controlador no recibió un paseoId
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    tematica: '' /*Tipo String*/,
                    nMinimCaminantes: '' /*Tipo Integer*/,
                    nMaxCaminantes: '' /*Tipo Integer*/,
                    hayTransporte:false /*Tipo Boolean*/,
                    costo: '' /*Tipo Double*/,
                    descripcion: '' /*Tipo String*/,                   
                    condicionesFisicas:[0,0,0,0,0],            
                    guia:{
                        id:''
                    } /*Objeto que representa instancia de Guia*/,
                    lugarDeDestino:{}/*Objeto que representa instancia de Lugar*/,
                    lugarDeEncuentro:{} /*Objeto que representa instancia de Lugar*/
                };

                $scope.alerts = [];
 
            }
            
             $http.get(guiasContext).then(function (response) {
                $scope.guias = response.data;
            });
            
             $http.get(lugaresContext).then(function (response) {
                $scope.lugares = response.data;
            });
            
            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;                

                // si el id es null, es un registro nuevo, entonces lo crea
               
                if (id == null) {
                                                 
                    //No se envia todo el detalle del guia, puesto que con ello también se envia
                    //informacion asociada a la herencia (de usuario) y esta no se mapea correctamente.
                    //Por ello, para indicar cual es el guia del paseo solo se manda su id.  
                    currentRecord.guia.id = $scope.guiaPaseoDetail.id;  
                    
                    if(document.getElementById('encuentro').value === "")
                    {
                        currentRecord.lugarDeEncuentro = null;
                    }
                    
                    if(document.getElementById('destino').value === "")
                    {
                        currentRecord.lugarDeDestino = null;
                    }
                    
                    if(document.getElementById('destino').value === "")
                    {
                        $scope.guiaPaseoDetail = null;
                    }
                    
                    
                    
                    
                    // ejecuta POST en el recurso REST  
                    return $http.post(paseosContext, currentRecord)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                //$scope.records = $http.get(paseosContext);
                                $state.go('paseosList', null, {reload: true});
                            }, responseError);

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {
                                           
                    // ejecuta PUT en el recurso REST    
                    return $http.put(paseosContext + "/" + currentRecord.id, currentRecord)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('paseosList');
                            }, responseError);
                }
                ;
            };            
           
           this.deleteRecord = function(recordR)
           {
                return $http.delete(paseosContext + "/" + recordR.id)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                //$scope.records = $http.get(paseosContext);
                                $state.go('paseosList', null, {reload: true});
                            }, responseError);
           };

            // -----------------------------------------------------------------
            // Funciones para manejar los mensajes en la aplicación


            //Alertas
            this.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };

            // Función showMessage: Recibe el mensaje en String y su tipo con el fin de almacenarlo en el array $scope.alerts.
            function showMessage(msg, type) {
                var types = ["info", "danger", "warning", "success"];
                if (types.some(function (rc) {
                    return type === rc;
                })) {
                    $scope.alerts.push({type: type, msg: msg});
                }
            }

            this.showError = function (msg) {
                showMessage(msg, "danger");
            };

            this.showSuccess = function (msg) {
                showMessage(msg, "success");
            };

            var self = this;
            function responseError(response) {

                self.showError(response.data);
            }
        }]);

})(window.angular);
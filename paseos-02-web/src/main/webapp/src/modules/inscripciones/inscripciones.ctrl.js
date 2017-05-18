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
    var mod = ng.module("inscripcionModule");
    

    mod.controller("inscripcionesCtrl", ['$scope', '$state', '$stateParams', '$http', 'inscripciones', 'caminantesContext', 'inscripcionesContext', 'currentCaminante',
        function ($scope, $state, $params, $http, inscripciones, caminantesContext, inscripcionesContext, currentCaminante) {

            
            $scope.records = inscripciones.data;
                      

            if ($params.inscripcionId !== null && $params.inscripcionId !== undefined) {

                // toma el id del parámetro
                id = $params.inscripcionId;
                // obtiene el dato del recurso REST
                $http.get(caminantesContext + '/' + $params.caminanteId + '/' + inscripcionesContext + '/' + $params.inscripcionId)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentRecord = response.data;                                        
                           
                        }, responseError);

                // el controlador no recibió un caminanteId
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    observaciones: '' /*Tipo String*/,
                    fechaInscripcion: '' /*Tipo Integer*/,
                    realizoPago: false,
                    caminante: {
                        id: currentCaminante.data.id
                    }
                };

                $scope.alerts = [];
            }
            
            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;
             
                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(caminantesContext + '/' + $params.caminanteId + '/' + inscripcionesContext, currentRecord)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('caminanteDetail',{caminanteId: currentRecord.caminante.id},{reload: true});
                            }, responseError);

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {

                    // ejecuta PUT en el recurso REST
                    return $http.put(caminantesContext + '/' + $params.caminanteId + '/' + inscripcionesContext + "/" + currentRecord.id, currentRecord)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('inscripcionDetail', {caminanteId: currentRecord.caminante.id, inscripcionId: currentRecord.id}, {reload: true});
                            }, responseError);
                }
                ;
            };
           
           this.deleteRecord = function(idCamin,idDelete)
           {
                return $http.delete(caminantesContext + '/' + idCamin + '/' + inscripcionesContext + '/' + idDelete)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                //$scope.records = $http.get(caminantesContext);
                                $state.go('caminanteDetail',{caminanteId: currentRecord.caminante.id},{reload: true});
                            }, responseError);
           };


            // -----------------------------------------------------------------
            // Funciones para manejra los mensajes en la aplicación


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
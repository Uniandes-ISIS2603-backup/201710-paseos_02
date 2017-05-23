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
    var mod = ng.module("guiaModule");
    

    mod.controller("guiasCtrl", ['$scope', '$state', '$stateParams', '$http', 'guiasContext', 'guias', '$filter',
        function ($scope, $state, $stateParams, $http, guiasContext, guias, $filter) {

            // inicialmente el listado de caminantes está vacio
            $scope.records = guias.data;
                  
            

            // el controlador recibió un caminanteId ??
            // revisa los parámetros (ver el :caminanteId en la definición de la ruta)
            if ($stateParams.guiaId !== null && $stateParams.guiaId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.guiaId;
                // obtiene el dato del recurso REST
                $http.get(guiasContext + "/" + id)
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
                    nombre: '' /*Tipo String*/,
                    identificacion: '' /*Tipo Integer*/,
                    tipoIdentificacion: '' /*Tipo String*/,
                    edad: '' /*Tipo Integer*/,
                    telefono: '' /*Tipo Integer*/,
                    direccion: '' /*Tipo String*/,
                    correoElectronico: '' /*Tipo String*/,
                    contrasenia: '' /*Tipo String*/,  
                    imagen: '' /*Tipo String*/,
                    experiencia: '' /*Tipo String*/,
                    formacion:'' /*Tipo String*/
                    
                    
                };

                $scope.alerts = [];
            }
            
            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;
                console.log(currentRecord);
                
                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(guiasContext, currentRecord)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                //$scope.records = $http.get(caminantesContext);
                                $state.go('guiasList', null, {reload: true});
                            }, responseError);

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {

                    // ejecuta PUT en el recurso REST
                    return $http.put(guiasContext + "/" + currentRecord.id, currentRecord)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('guiasList');
                            }, responseError);
                }
                ;
            };
           
           //this.desactivarCuenta = function(recordR)
           //{
             //   return $http.put(guiasContext + "/desactivar/" + recordR.id)
              //              .then(function () {
  
              //                  $state.go('guiasList', null, {reload: true});
              //              }, responseError);
           //};

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
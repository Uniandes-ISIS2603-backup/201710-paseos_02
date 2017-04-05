(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router',
        // Internal modules dependencies       
        'caminanteModule',
        'guiaModule',
        'paseosModule',
        'lugaresModule',
        'actividadModule'
        // 'opinionesModule'
    ]);
    // Resuelve problemas de las promesas. Presenta error al desplegar. No se incluye por ahora.
    //
    //app.config(['$qProvider', function ($qProvider) {
    //       $qProvider.errorOnUnhandledRejections(false);
     //   }]);
   
})(window.angular);
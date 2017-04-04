(function (ng) {
    // Definición del módulo
    var mod = ng.module("actividadModule", ['ui.router']);
 
   // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/actividades/';
            // Mostrar la lista de caminantes será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/actividadesList");
           
            $stateProvider.state('actividadesList', {
                // Url que aparecerá en el browser
                url: '/actividades/list',
                // Se define una variable books (del estado) que toma por valor 
                // la colección de libros que obtiene utilizando $http.get 
                 resolve: {
                    actividades: ['$http', function ($http) {
                            return $http.get('data/actividades.json'); // $http retorna una promesa que aquí no se está manejando si viene con error.
                        }]
                },
                // Template que se utilizara para ejecutar el estado
                templateUrl: basePath + 'actividades.list.html',
                // El controlador guarda en el scope en la variable paseosEcologicosRecords los datos que trajo el resolve
                // paseosEcologicosRecords será visible en el template
                controller: ['$scope', 'actividades', function ($scope, paseosEcologicos) {
                        $scope.actividadesRecords = paseosEcologicos.data;
                    }]              
            });
        }
    ]);
})(window.angular);


/**
 * Created by Willian on 06/06/2015.
 */
angular.module("cdweb")
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/usuario', {
                templateUrl: 'usuario.html',
                controller:'UsuarioController'
            })
            .when('/login', {
                templateUrl: 'login.html',
                controller: 'LoginController',
                controllerAs:"ctrl"
            })
            
    
              .when('/listausuario', {
                   templateUrl: 'listausuario.html'
             })


            .when('/config', {
                templateUrl: 'configuration.html',
                controller: 'ConfigurationController'
            })


            .when('/menu', {
                templateUrl: 'menu.html',
                controller: 'MenuController'
            })

            .when('/dispositivo/:id', {
                templateUrl: 'dispositivo.html',
                controller: 'DispositivoController',
                controllerAs:"ctrl"
            })

            .when('/dispositivo', {
                templateUrl: 'dispositivo.html',
                controller: 'DispositivoController',
                controllerAs:"ctrl"
            })

            .when('/predefinicoes', {
                templateUrl: 'predefinicoes.html'
            })
            
          

            .when('/modelodispositivo', {
                templateUrl: 'modelodispositivo.html',
                 controller:'ModeloDispositivoController',
                controllerAs:"ctrl"
            })

            .when('/modelodispositivo/:id', {
                templateUrl: 'modelodispositivo.html',
                controller:'ModeloDispositivoController',
                controllerAs:"ctrl"
            })



            .when('/Book/:bookId/ch/:chapterId', {
                templateUrl: 'chapter.html',
                controller: 'ChapterController'
            })
            .otherwise({
                redirectTo: '/login'
            });

    }]);

/**
 * Created by Willian on 06/06/2015.
 */
angular.module("cdweb")
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/usuario', {
                templateUrl: 'usuario.html',
                controller:'UsuarioController',
                controllerAs:"ctrl"
            })

            .when('/usuario/:id', {
                templateUrl: 'usuario.html',
                controller:'UsuarioController',
                controllerAs:"ctrl"
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

            .when('/usuarioperfil/:id', {
                templateUrl: 'usuario_perfil.html',
                controller: 'UsuarioPerfilController',
                controllerAs:"ctrl"
            })

            .when('/usuarioperfil', {
                templateUrl: 'usuario_perfil.html',
                controller: 'UsuarioPerfilController',
                controllerAs:"ctrl"
            })
            .when('/dispositivoacao/:id', {
                templateUrl: 'dispositivo_acao.html',
                controller: 'DispositivoAcaoController',
                controllerAs:"ctrl"
            })

            .when('/dispositivoacao', {
                templateUrl: 'dispositivo_acao.html',
                controller: 'DispositivoAcaoController',
                controllerAs:"ctrl"
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

            .when('/perfil', {
                templateUrl: 'perfil.html',
                controller:'PerfilController',
                controllerAs:"ctrl"
            })

            .when('/perfil/:id', {
                templateUrl: 'perfil.html',
                controller:'PerfilController',
                controllerAs:"ctrl"
            })
            .when('/perfilacesso/:id', {
                templateUrl: 'perfil_acesso.html',
                controller: 'PerfilAcessoController',
                controllerAs:"ctrl"
            })

            .when('/perfilacesso', {
                templateUrl: 'perfil_acesso.html',
                controller: 'PerfilAcessoController',
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

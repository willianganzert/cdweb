/**
 * Created by mayju_000 on 05/12/2015.
 */
var services = angular.module('cdweb');

services.factory('UsuarioFactory', function ($resource) {
    return $resource('http://localhost:8080/server/rest/usuarios/:id', {}, {
        get: { method: 'GET',params: {id: '@idUsuario'}},
        query: { method: 'GET', isArray:true},
        save: { method: 'POST'},
        update: { method: 'PUT', params: {id: '@idUsuario'} },
        delete: { method: 'DELETE', params: {id: '@idUsuario'} }
    })
});

services.factory('DispositivoAcao', function ($resource) {
    return $resource('http://localhost:8080/server/rest/acao/:id', {}, {
        execute: { method: 'POST', params: {id: '@idModeloAcao'} }
    })
});

services.factory('UsuariosPerfilFactory', function ($resource) {
    return $resource('http://localhost:8080/server/rest/usuariosperfis/:id', {}, {
        /*get: { method: 'GET',params: {id: '@idDispositivo'}},
        query: { method: 'GET', isArray:true},
        save: { method: 'POST'},
        update: { method: 'PUT', params: {id: '@idDispositivo'} },
        delete: { method: 'DELETE', params: {id: '@idDispositivo'} },*/
        update: { method: 'PUT', params: {id: '@idUsuario'} },
        getPerfis: { method: 'get',
            url:'http://localhost:8080/server/rest/usuariosperfis/usuario/:id',
            params: {id: '@idUsuario'}, isArray:true},
    })
});

services.factory('PerfilAcessoFactory', function ($resource) {
    return $resource('http://localhost:8080/server/rest/perfisacesso/:id', {}, {
        /*get: { method: 'GET',params: {id: '@idDispositivo'}},
         query: { method: 'GET', isArray:true},
         save: { method: 'POST'},
         update: { method: 'PUT', params: {id: '@idDispositivo'} },
         delete: { method: 'DELETE', params: {id: '@idDispositivo'} },*/
        update: { method: 'PUT', params: {id: '@idPerfil'} },
        getPerfis: { method: 'get',
            url:'http://localhost:8080/server/rest/perfisacesso/perfil/:id',
            params: {id: '@idPerfil'}, isArray:true},
    })
});


services.factory('UserFactory', function ($resource) {
    return $resource('http://localhost:8080/server/rest/usuarios/:id', {}, {
        show: { method: 'GET' },
        query: { method: 'GET', isArray:true},
        update: { method: 'PUT', params: {id: '@idUsuario'} },
        delete: { method: 'DELETE', params: {id: '@idUsuario'} }
    })
});

services.factory('DispositivoFactory', function ($resource) {
    return $resource('http://localhost:8080/server/rest/dispositivos/:id/:parametros', {}, {
        get: { method: 'GET',params: {id: '@idDispositivo'}},
        query: { method: 'GET', isArray:true},
        save: { method: 'POST'},
        update: { method: 'PUT', params: {id: '@idDispositivo'} },
        delete: { method: 'DELETE', params: {id: '@idDispositivo'} },
        getParametros: { method: 'POST',
        	url:'http://localhost:8080/server/rest/dispositivos/:id/parametros',
        	params: {id: '@idDispositivo'}, isArray:true},
    })
});

services.factory('PerfilFactory', function ($resource) {
    return $resource('http://localhost:8080/server/rest/perfis/:id', {}, {
        get: { method: 'GET',params: {id: '@idPerfil'}},
        query: { method: 'GET', isArray:true},
        save: { method: 'POST'},
        update: { method: 'PUT', params: {id: '@idPerfil'} },
        delete: { method: 'DELETE', params: {id: '@idPerfil'} }
    })
});

services.factory('ParametroFactory', function ($resource) {
    return $resource('http://localhost:8080/server/rest/parametros/:id/:parametros', {}, {
        get: { method: 'GET',params: {id: '@idParametro'}},
        query: { method: 'GET', isArray:true},
        save: { method: 'POST'},
        update: { method: 'PUT', params: {id: '@idParametro'} },
        delete: { method: 'DELETE', params: {id: '@idParametro'} }
    })
});

services.factory('ModeloDispositivoFactory', function ($resource) {
    return $resource('http://localhost:8080/server/rest/modelosdispositivo/:id', {}, {
        get: { method: 'GET',params: {id: '@idModeloDispositivo'}},
        query: { method: 'GET', isArray:true},
        save: { method: 'POST'},
        update: { method: 'PUT', params: {id: '@idModeloDispositivo'} },
        delete: { method: 'DELETE', params: {id: '@idModeloDispositivo'} }
    })
});

services.factory('ModeloAcaoFactory', function ($resource) {
    return $resource('http://localhost:8080/server/rest/modelosacao/:id', {}, {
        get: { method: 'GET',params: {id: '@idModeloAcao'}},
        query: { method: 'GET', isArray:true},
        save: { method: 'POST'},
        update: { method: 'PUT', params: {id: '@idModeloAcao'} },
        delete: { method: 'DELETE', params: {id: '@idModeloAcao'} }
    })
});


services.factory('ModeloParametroFactory', function ($resource) {
    return $resource('http://localhost:8080/server/rest/modelosparametro/:id', {}, {
        get: { method: 'GET',params: {id: '@idModeloParametro'}},
        query: { method: 'GET', isArray:true},
        save: { method: 'POST'},
        update: { method: 'PUT', params: {id: '@idModeloParametro'} },
        delete: { method: 'DELETE', params: {id: '@idModeloParametro'} }        
    })
});

services.factory('ModeloParametroFactory', function ($resource) {
    return $resource('http://localhost:8080/server/rest/modelosparametro/:id', {}, {
        get: { method: 'GET',params: {id: '@idModeloParametro'}},
        query: { method: 'GET', isArray:true},
        save: { method: 'POST'},
        update: { method: 'PUT', params: {id: '@idModeloParametro'} },
        delete: { method: 'DELETE', params: {id: '@idModeloParametro'} }
    })
});

services.factory('user', ['$resource', 'serverConfig', function ($resource, serverConfig) {
    return $resource(serverConfig.getServer().servicesServer + '/authentication', {}, {
        authenticate: {
            method: 'POST',
            isArray: false,
            transformRequest: function (obj) {
                var str = [];
                for (var p in obj)
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                return str.join("&");
            },
            transformResponse: function (data) {
                return {content: data};
            },
            responseType: "text",
            headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
        }
    })
}]);

services.factory('serverConfig', ["$location", function ($location) {
    var servidorSelecionado = "teste";
    return {
        getServer: function () {
            if (servidorSelecionado) {
                return this[servidorSelecionado];
            }
            else {
                return this["default"];
            }
        },
        default: {
            servicesServer: $location.protocol()+"://"+$location.host()+($location.port()?":"+$location.port():"") + location.pathname+"rest",
            urlAppServer: $location.protocol()+"://"+$location.host()+($location.port()?":"+$location.port():"") + location.pathname
        },
        teste: {
            servicesServer: "http://localhost:8080/server/rest",
            urlAppServer: "http://localhost:8080/server/"
        }
    };
}]);




services.factory('AuthenticationService', ['$http', '$cookieStore', '$rootScope', '$q', 'user', function ($http, $cookieStore, $rootScope, $q, user) {
    var auth = {
        login: function (username, password) {
            var deferred = $q.defer();
            user.authenticate({u: Base64.encode(username), p: Base64.encode(password)}).$promise.then(function (data) {
                data.content = JSON.parse(data.content);
                var token = data.content[0];
                var perm = data.content[1];
                auth.setCredentials(username,token,perm);
                deferred.resolve(token);
            }, function (data) {
                deferred.reject(data);
            });
            return deferred.promise;
        },
        setFromCookies: function(){
            $rootScope.globals = $cookieStore.get('globals');
            $http.defaults.headers.common['Authorization'] = 'Bearer ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
            $rootScope["authorized"] = true;
        },
        setCredentials: function (username, data, perm) {
            //var authdata = Base64.encode(username + ':' + password);
            if(!$rootScope.globals){
                $rootScope.globals = {
                    currentUser: {
                        username: username,
                        authdata: data,
                        perm: perm
                    }
                }
            }
            else{
                $rootScope.globals.currentUser.username = username;
                $rootScope.globals.currentUser.authdata = data;
                $rootScope.globals.currentUser.perm = perm;
            }


            $http.defaults.headers.common['Authorization'] = 'Bearer ' + data; // jshint ignore:line
            $cookieStore.put('globals', $rootScope.globals);
            $rootScope["authorized"] = true;
        },
        clearCredentials: function () {
            if($rootScope.globals){
                delete $rootScope.globals.currentUser.username;
                delete $rootScope.globals.currentUser.authdata;
                delete $rootScope.globals.currentUser.perm;
            }
            $rootScope["authorized"] = false;

            $cookieStore.remove('globals');
            $http.defaults.headers.common.Authorization = 'Basic ';
        },
        hasValidCredentials: function(){
            if($cookieStore.get('globals')) return true;
            return false;
        }
    }
    return auth;
}]);

// Base64 encoding service used by AuthenticationService
var Base64 = {

    keyStr: 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=',

    encode: function (input) {
        var output = "";
        var chr1, chr2, chr3 = "";
        var enc1, enc2, enc3, enc4 = "";
        var i = 0;

        do {
            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);

            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;

            if (isNaN(chr2)) {
                enc3 = enc4 = 64;
            } else if (isNaN(chr3)) {
                enc4 = 64;
            }

            output = output +
                this.keyStr.charAt(enc1) +
                this.keyStr.charAt(enc2) +
                this.keyStr.charAt(enc3) +
                this.keyStr.charAt(enc4);
            chr1 = chr2 = chr3 = "";
            enc1 = enc2 = enc3 = enc4 = "";
        } while (i < input.length);

        return output;
    },

    decode: function (input) {
        var output = "";
        var chr1, chr2, chr3 = "";
        var enc1, enc2, enc3, enc4 = "";
        var i = 0;

        // remove all characters that are not A-Z, a-z, 0-9, +, /, or =
        var base64test = /[^A-Za-z0-9\+\/\=]/g;
        if (base64test.exec(input)) {
            window.alert("There were invalid base64 characters in the input text.\n" +
                "Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n" +
                "Expect errors in decoding.");
        }
        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

        do {
            enc1 = this.keyStr.indexOf(input.charAt(i++));
            enc2 = this.keyStr.indexOf(input.charAt(i++));
            enc3 = this.keyStr.indexOf(input.charAt(i++));
            enc4 = this.keyStr.indexOf(input.charAt(i++));

            chr1 = (enc1 << 2) | (enc2 >> 4);
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
            chr3 = ((enc3 & 3) << 6) | enc4;

            output = output + String.fromCharCode(chr1);

            if (enc3 != 64) {
                output = output + String.fromCharCode(chr2);
            }
            if (enc4 != 64) {
                output = output + String.fromCharCode(chr3);
            }

            chr1 = chr2 = chr3 = "";
            enc1 = enc2 = enc3 = enc4 = "";

        } while (i < input.length);

        return output;
    }
};
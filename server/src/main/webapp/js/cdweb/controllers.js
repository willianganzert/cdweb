-/**
 * Created by Willian on 06/06/2015.
 */
angular.module("cdweb")
    .controller("ConfigurationController",['$scope',function($scope){
        $scope.status = "Não enviado";
        $scope.enviarIP = function(){
            alert("");
        }
    }])
    .controller("MenuController",['$scope',function($scope){
        $scope.menu = "Menu da ";
        $scope.nome = "";
        $scope.$root.header = true;
        $scope.enviarIP = function(){
            alert("");
        }
    }])


    .controller("CadisController",['$scope',function($scope){
        $scope.menu = "Menu da ";
        $scope.nome = "";
        $scope.$root.header = true;
        $scope.cadastro = false;

        $scope.selecionar = function(linha){
            alert("Registro Selecionado" + linha);
            $scope.cadastro = true;
        }
        $scope.salvar = function(registro){
            alert("Registro Salvo" + registro);
            $scope.cadastro = false;
        }
    }])
    .controller("DispositivoController", ['$scope','NgTableParams',"$filter", "$q",'DispositivoFactory',"$routeParams",'$location', function ($scope,NgTableParams,$filter, $q,DispositivoFactory,$routeParams,$location) {
        var ctrl = this;
        ctrl.params = $routeParams;
        ctrl.recordID = $routeParams && $routeParams.id?$routeParams.id:undefined;
        ctrl.editable = true;
        ctrl.voltarLista = function () {
            $location.path('/dispositivo')
        }
        ctrl.dispositivo = {
            model: {
                nome:""
            },
            editable : true,
            editing: null,
            dataConvert:{
                in: function (data) {
                    return data;
                },
                out: function (data) {
                    return data;
                }
            },
            edit: function (record) {
                if (record) {
                    ctrl.dispositivo.editing = ctrl.dispositivo.dataConvert.in(record);

                }
                else {
                    ctrl.dispositivo.edit(angular.extend({}, ctrl.dispositivo.model));
                }
            },
            save: function () {
                if(ctrl.dispositivo.editing.idDispositivo){
                    DispositivoFactory.update(ctrl.dispositivo.editing, function (data) {
                        ctrl.voltarLista();
                    });
                }
                else {
                    DispositivoFactory.save(ctrl.dispositivo.editing, function (data) {
                        ctrl.voltarLista();
                    });
                }
                ctrl.dispositivo.clear();
            },
            clear:function(){
                ctrl.dispositivo.editing = null;
            }
        }
        if (ctrl.recordID) {
            if (ctrl.recordID > 0) {
                DispositivoFactory.get({id: ctrl.recordID}, function (data) {
                    ctrl.dispositivo.edit(data);
                });
            }
            else {
                ctrl.dispositivo.edit();
            }
        }
        else {
            ctrl.dispositivoTable = new NgTableParams({
                page: 1, // show first page
                count: 10 // count per page

            }, {
                counts: [],
                getData: function (params) {
                    var d = $q.defer();
                    DispositivoFactory.query().$promise.then(function (data) {
                        params.total(data.length);
                        d.resolve(data);
                    }, function (e) {
                        d.reject(e);
                    });

                    return d.promise;
                }
            });
        }
    }])

    .controller("UsuarioController",['$scope', 'UserFactory','$routeParams', '$location', function($scope, UserFactory, $routeParams, $location){
        $scope.menu = "Menu da ";
        $scope.nome = "";
        $scope.$root.header = true;
        $scope.user = {};

        $scope.buscarusuario = function(idUsuario) {
        	$scope.user = UserFactory.show({id: idUsuario});

        }        
        $scope.cancelar = function(){
        	  $location.path('/menu');
        }
                
        $scope.gravar = function () {
            UserFactory.update($scope.user);
            $location.path('/menu');
        };


        $scope.buscarusuario(1);
    }])
    
    .controller("ModeloDispositivoController2",['$scope', 'DispositivoFactory','$routeParams', '$location', 'ModeloDispositivoFactory', 'ModeloAcaoFactory',  function($scope, DispositivoFactory, $routeParams, $location, ModeloDispositivoFactory, ModeloAcaoFactory){
        $scope.dispositivos = DispositivoFactory.query(); 
        $scope.nome = "";
        $scope.$root.header = true;
        $scope.modelo = {};
        $scope.modeloacao = {};
        

        $scope.$watch("modelo.dispositivo", function(newValue,oldValue){
        	if(newValue){
        		$scope.parametros = DispositivoFactory.getParametros(newValue)	
        	}
        	else{
        		$scope.parametros = null;
        	}
        	
        })
        $scope.buscar= function(idModeloAcao) {
//        	$scope.modelo = ModelFactory.show({id: idModeloAcao});

        }        
        $scope.cancelar = function(){
        	  $location.path('/menu');
        }
                
        $scope.gravar = function () {
        	ModeloDispositivoFactory[$scope.modelo.idDispositivo?"update":"save"]($scope.modelo, function(dadosModeloDispositivo){
        		$scope.modeloacao.modeloDispositivo = dadosModeloDispositivo;
        		ModeloAcaoFactory[$scope.modeloacao.idAcao?"update":"save"]($scope.modeloacao);

                $location.path('/menu');
        	});        	
        };

        
    }])


    .controller("ModeloDispositivoController", ['$scope','NgTableParams',"$filter", "$q",'ModeloDispositivoFactory','DispositivoFactory',"$routeParams",'$location', function ($scope,NgTableParams,$filter, $q,ModeloDispositivoFactory,DispositivoFactory,$routeParams,$location) {
        var ctrl = this;
        ctrl.objPrincipal = "modelodispositivo";
        ctrl.idObjPrincipal = "idModeloDispositivo";

        $scope.dispositivos = DispositivoFactory.query();
        $scope.$watch("ctrl."+ctrl.objPrincipal+".editing.dispositivo", function(newValue,oldValue){
            if(newValue){
                $scope.parametros = DispositivoFactory.getParametros(newValue)
            }
            else{
                $scope.parametros = null;
            }

        })
        ctrl.params = $routeParams;
        ctrl.recordID = $routeParams && $routeParams.id?$routeParams.id:undefined;
        ctrl.editable = true;
        ctrl.voltarLista = function () {
            $location.path('/modelodispositivo')
        }
        ctrl[ctrl.objPrincipal] = {
            model: {
                dispositivo:{idDispositivo:null},
                nome:""
            },
            editable : true,
            editing: null,
            dataConvert:{
                in: function (data) {
                    return data;
                },
                out: function (data) {
                    return data;
                }
            },
            edit: function (record) {
                if (record) {
                    ctrl[ctrl.objPrincipal].editing = ctrl[ctrl.objPrincipal].dataConvert.in(record);

                }
                else {
                    ctrl[ctrl.objPrincipal].edit(angular.extend({}, ctrl[ctrl.objPrincipal].model));
                }
            },
            save: function () {
                if(ctrl[ctrl.objPrincipal].editing[ctrl.idObjPrincipal]){
                    ModeloDispositivoFactory.update(ctrl[ctrl.objPrincipal].editing, function (data) {
                        ctrl.voltarLista();
                    });
                }
                else {
                    ModeloDispositivoFactory.save(ctrl[ctrl.objPrincipal].editing, function (data) {
                        ctrl.voltarLista();
                    });
                }
                ctrl[ctrl.objPrincipal].clear();
            },
            clear:function(){
                ctrl[ctrl.objPrincipal].editing = null;
            }
        }
        if (ctrl.recordID) {
            if (ctrl.recordID > 0) {
                ModeloDispositivoFactory.get({id: ctrl.recordID}, function (data) {
                    ctrl[ctrl.objPrincipal].edit(data);
                });
            }
            else {
                ctrl[ctrl.objPrincipal].edit();
            }
        }
        else {
            ctrl[ctrl.objPrincipal].table = new NgTableParams({
                page: 1, // show first page
                count: 10 // count per page

            }, {
                counts: [],
                getData: function (params) {
                    var d = $q.defer();
                    ModeloDispositivoFactory.query().$promise.then(function (data) {
                        params.total(data.length);
                        d.resolve(data);
                    }, function (e) {
                        d.reject(e);
                    });

                    return d.promise;
                }
            });
        }
    }])

    .controller("ModeloAcao", ['$scope','NgTableParams',"$filter", "$q",'ModeloAcaoFactory','ModeloParametroFactory',"$routeParams",'$location', function ($scope,NgTableParams,$filter, $q,ModeloAcaoFactory,ModeloParametroFactory,$routeParams,$location) {
        var ctrl = this;
        ctrl.objPrincipal = "modeloacao";
        ctrl.idObjPrincipal = "idModeloAcao";

        $scope.dispositivos = ModeloAcaoFactory.query();
        $scope.$watch("ctrl."+ctrl.objPrincipal+".editing.dispositivo", function(newValue,oldValue){
            if(newValue){
                $scope.parametros = ModeloAcaoFactory.getParametros(newValue)
            }
            else{
                $scope.parametros = null;
            }

        })
        ctrl.params = $routeParams;
        ctrl.recordID = $routeParams && $routeParams.id?$routeParams.id:undefined;
        ctrl.editable = true;
        ctrl.voltarLista = function () {
            $location.path('/modeloacao')
        }
        ctrl[ctrl.objPrincipal] = {
            model: {
                dispositivo:{idDispositivo:null},
                nome:""
            },
            editable : true,
            editing: null,
            dataConvert:{
                in: function (data) {
                    return data;
                },
                out: function (data) {
                    return data;
                }
            },
            edit: function (record) {
                if (record) {
                    ctrl[ctrl.objPrincipal].editing = ctrl[ctrl.objPrincipal].dataConvert.in(record);

                }
                else {
                    ctrl[ctrl.objPrincipal].edit(angular.extend({}, ctrl[ctrl.objPrincipal].model));
                }
            },
            save: function () {
                if(ctrl[ctrl.objPrincipal].editing[ctrl.idObjPrincipal]){
                    ModeloAcaoFactory.update(ctrl[ctrl.objPrincipal].editing, function (data) {
                        ctrl.voltarLista();
                    });
                }
                else {
                    ModeloAcaoFactory.save(ctrl[ctrl.objPrincipal].editing, function (data) {
                        ctrl.voltarLista();
                    });
                }
                ctrl[ctrl.objPrincipal].clear();
            },
            clear:function(){
                ctrl[ctrl.objPrincipal].editing = null;
            }
        }
        if (ctrl.recordID) {
            if (ctrl.recordID > 0) {
                ModeloAcaoFactory.get({id: ctrl.recordID}, function (data) {
                    ctrl[ctrl.objPrincipal].edit(data);
                });
            }
            else {
                ctrl[ctrl.objPrincipal].edit();
            }
        }
        else {
            ctrl[ctrl.objPrincipal].table = new NgTableParams({
                page: 1, // show first page
                count: 10 // count per page

            }, {
                counts: [],
                getData: function (params) {
                    var d = $q.defer();
                    ModeloAcaoFactory.query().$promise.then(function (data) {
                        params.total(data.length);
                        d.resolve(data);
                    }, function (e) {
                        d.reject(e);
                    });

                    return d.promise;
                }
            });
        }
    }])



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
    
    .controller("ModeloDispositivoController",['$scope', 'DispositivoFactory','$routeParams', '$location', 'ModeloDispositivoFactory', 'ModeloAcaoFactory',  function($scope, DispositivoFactory, $routeParams, $location, ModeloDispositivoFactory, ModeloAcaoFactory){
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

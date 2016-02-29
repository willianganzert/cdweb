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


    .controller("ModeloDispositivoController", ['$scope','NgTableParams',"$filter", "$q",'ModeloDispositivoFactory','DispositivoFactory',"$routeParams",'$location','$uibModal', function ($scope,NgTableParams,$filter, $q,ModeloDispositivoFactory,DispositivoFactory,$routeParams,$location,$uibModal) {
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
        ctrl.modeloAcao = {
            ID_NAME: "idModeloAcao",
            model: {
                descricao: "",
                nome: "",
                modeloParametros:[]
            },
            editing: null,
            removeList: [],
            clear: function () {
                ctrl.modeloAcao.editing = null;
            },
            edit: function (record) {
                if (record) {
                    var tempRecord = angular.extend({}, record);
                    ctrl.modeloAcao.editing = ctrl.modeloAcao.convertData && ctrl.modeloAcao.convertData.in ? ctrl.modeloAcao.convertData.in(tempRecord) : tempRecord;
                }
                else {
                    record = angular.extend({}, ctrl.modeloAcao.model);
                    ctrl.modeloAcao.edit(record);
                }
            },
            save: function (record) {
                record = record ? record : ctrl.modeloAcao.editing;
                ctrl.schedule.form.$setDirty();

                var indexArray = ctrl.modeloAcao.isInArray(record, ctrl.schedule.editing.scheduleParams);
                var isNewRecord = indexArray == -1;
                if (!isNewRecord) {
                    ctrl.schedule.editing.scheduleParams.splice(indexArray, 1);
                }

                if (record.paramOrder < ctrl.modeloAcao.calculateNextOrder(ctrl.schedule.editing.scheduleParams)) {
                    ctrl.schedule.editing.scheduleParams.push(ctrl.modeloAcao.calculateOrders(record, ctrl.schedule.editing.scheduleParams));
                }
                else {
                    ctrl.schedule.editing.scheduleParams.push(record);
                }

                if (!isNewRecord) {
                    ctrl.modeloAcao.resetOrder(ctrl.schedule.editing.scheduleParams);
                    ctrl.schedule.editing.scheduleParams.sort(function (a, b) {
                        return a.paramOrder - b.paramOrder
                    });
                }

                ctrl.modeloAcao.clear();
            },
            delete: function (record) {
                if (record[this.ID_NAME]) {
                    ctrl.modeloAcao.removeList.push(record);
                }
                for (var idx = 0; ctrl.schedule.editing.scheduleParams.length; idx++) {
                    if (record == ctrl.schedule.editing.scheduleParams[idx]) {
                        ctrl.schedule.editing.scheduleParams.splice(idx, 1);
                        break;
                    }
                }
                ctrl.modeloAcao.clear();
            },
            openModeloParametroModal: function () {

                var modalInstance = $uibModal.open({
                    animation: true,
                    templateUrl: 'modeloParametroModal.html',
                    controller:'ModeloParametrosController',
                    size: 'lg',
                    resolve: {
                        modeloAcao: function () {
                            return ctrl.modeloAcao.editing;
                        },
                        parametros: function() {
                            return $scope.parametros
                        }
                    }

                })
                modalInstance.result.then(function (selectedItem) {
                    $scope.selected = selectedItem;
                }, function () {
                    $log.info('Modal dismissed at: ' + new Date());
                });


            },
            calculateNextOrder: function (params) {
                var order = 0;
                if (params) {
                    for (var p in params) {
                        if (params[p].paramOrder > order) {
                            order = params[p].paramOrder;
                        }
                    }
                }
                return ++order;
            },
            calculateOrders: function (newParam, params) {
                if (params && newParam) {
                    for (var p in params) {
                        if (params[p].paramOrder >= newParam.paramOrder) {
                            params[p].paramOrder++;
                        }
                    }
                }
                return newParam;
            },
            isInArray: function (newParam, params) {
                if (params && newParam) {
                    for (var p in params) {
                        if (params[p] == newParam) {
                            return p;
                        }
                    }
                }
                return -1;
            },
            resetOrder: function (params, startAt) {
                startAt = (startAt ? startAt : 0);
                var less = null;
                var indexLess = null;
                for (var p = startAt; p < params.length; p++) {
                    if (!less || params[p].paramOrder < less.paramOrder) {
                        less = params[p];
                        indexLess = p;
                    }
                }
                if (less) {
                    params.splice(indexLess, 1);
                    params.unshift(less);
                    less.paramOrder = startAt + 1;
                    this.resetOrder(params, (startAt + 1));
                }
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
    .controller("ModeloParametrosController", ['$scope','NgTableParams','modeloAcao','parametros', function ($scope,NgTableParams,modeloAcao,parametros) {
        /*var self = this;

        var originalData = angular.copy(simpleList);

        self.tableParams = new NgTableParams({}, {
            dataset: angular.copy(simpleList)
        });*/
        $scope.parametros = parametros;
        $scope.modeloAcao = modeloAcao;
        $scope.addNovo=function(){
            $scope.modeloAcao.modeloParametros.push({parametro:null,valorParametroAcao: ""});
        };
        $scope.ok=function(){
            $scope.$close($scope.selected.item);
        };
        $scope.cancel = function(){
            $scope.$dismiss('cancel');
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
    .controller('LoginController', ['$scope', '$location', 'AuthenticationService','$rootScope',
        function ($scope, $location, AuthenticationService, $rootScope) {
            var ctrl = this;

            ctrl.login = login;

            (function initController() {
                // reset login status
                AuthenticationService.clearCredentials();
            })();

            function login() {
                ctrl.dataLoading = true;
                AuthenticationService.login(ctrl.username, ctrl.password).then(function (data) {
                    $rootScope["authorized"] = true;
                    $location.path('/menu');
                }, function (data) {
                    alert("Não foi possível efetuar login")
                    ctrl.dataLoading = false;
                });
            };
        }
    ])



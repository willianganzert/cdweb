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
    .controller("DispositivoController", ['$scope','NgTableParams',"$filter", "$q",'DispositivoFactory',"$routeParams",'$location','ParametroFactory', function ($scope,NgTableParams,$filter, $q,DispositivoFactory,$routeParams,$location,ParametroFactory) {
        var ctrl = this;
        ctrl.params = $routeParams;
        ctrl.recordID = $routeParams && $routeParams.id?$routeParams.id:undefined;
        ctrl.editable = true;
        ctrl.voltarLista = function () {
            $location.path('/dispositivo')
        }
        ctrl.objPrincipal = "dispositivo";

        ctrl.dispositivo = {
            ID_NAME: "idDispositivo",
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
        ctrl.parametro = {
            ID_NAME: "idParametro",
            model: {
                nome: "",
                tipo: 1,//1|2|3
                tipoValor: "",
                tipoValor2: ""
            },
            habilitaCampo2 :false,
            editable : true,
            editing: null,
            dataConvert:{
                in: function (data) {
                    ctrl.parametro.changeTipoDado(data.tipo)
                    return data;
                },
                out: function (data) {
                    if(ctrl.dispositivo.editing[ctrl.dispositivo.ID_NAME]){
                        data.dispositivo = {};
                        data.dispositivo[ctrl.dispositivo.ID_NAME] = ctrl.dispositivo.editing[ctrl.dispositivo.ID_NAME];
                    }
                    return data;
                }
            },
            edit: function (record) {
                if (record) {
                    this.editing = this.dataConvert.in(record);
                }
                else {
                    this.edit(angular.extend({}, this.model));
                }
            },
            save: function () {
                if(this.editing[this.ID_NAME]){
                    ParametroFactory.update(this.dataConvert.out(this.editing), function (data) {
                    });
                }
                else {
                    ParametroFactory.save(this.dataConvert.out(this.editing), function (data) {
                    });
                }
                this.clear();
            },
            clear:function(){
                this.editing = null;
            },
            changeTipoDado : function(tipoDado){
                if(tipoDado=3){
                    this.habilitaCampo2 = true;

                }else {
                    this.habilitaCampo2 = false;
                    delete this.editing.tipoValor2;
                }
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
    .controller("PerfilController",['$scope','$q', 'PerfilFactory','NgTableParams','$routeParams', '$location', function($scope, $q, PerfilFactory,NgTableParams, $routeParams, $location){
        $scope.$root.header = true;
        var ctrl = this;
        ctrl.objPrincipal = "perfil";
        ctrl.params = $routeParams;
        ctrl.recordID = $routeParams && $routeParams.id?$routeParams.id:undefined;
        ctrl.voltarLista = function () {
            $location.path('/perfil')
        }
        var obj = ctrl[ctrl.objPrincipal] = {
            ID_NAME: "idPerfil",
            model: {
                nome: "",
                descricao: ""
            },
            editable:true,
            editing: null,
            dataConvert:{
                in: function (data) {
                    return data;
                },
                out: function (data) {
                    return data;
                }
            },
            clear: function () {
                obj.editing = null;
            },
            edit: function (record) {
                if (record) {
                    var tempRecord = angular.extend({}, record);
                    obj.editing = obj.convertData && obj.convertData.in ? obj.convertData.in(tempRecord) : tempRecord;
                }
                else {
                    record = angular.extend({}, obj.model);
                    obj.edit(record);
                }
            },
            save: function (record) {
                var tempRecord = angular.extend({}, record || obj.editing);
                tempRecord = obj.convertData && obj.convertData.out ? obj.convertData.out(tempRecord) : tempRecord;
                if(obj.editing[obj.ID_NAME]){
                    PerfilFactory.update(tempRecord, function (data) {
                        ctrl.voltarLista();
                    });
                }
                else {
                    PerfilFactory.save(tempRecord, function (data) {
                        ctrl.voltarLista();
                    });
                }
                ctrl[ctrl.objPrincipal].clear();
            },
            delete: function (record) {
                record = record || obj.editing
                PerfilFactory.delete({id: record[obj.ID_NAME]},function (){
                    obj.clear();
                });

            }
        }
        if (ctrl.recordID) {
            if (ctrl.recordID > 0) {
                PerfilFactory.get({id: ctrl.recordID}, function (data) {
                    obj.edit(data);
                });
            }
            else {
                obj.edit();
            }
        }
        else {
            obj.table = new NgTableParams({
                page: 1, // show first page
                count: 10 // count per page

            }, {
                counts: [],
                getData: function (params) {
                    var d = $q.defer();
                    PerfilFactory.query().$promise.then(function (data) {
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
    .controller("UsuarioController",['$scope','$q', 'UsuarioFactory','NgTableParams','$routeParams', '$location', function($scope, $q, UsuarioFactory,NgTableParams, $routeParams, $location){
        $scope.$root.header = true;
        var ctrl = this;
        ctrl.objPrincipal = "usuario";
        ctrl.params = $routeParams;
        ctrl.recordID = $routeParams && $routeParams.id?$routeParams.id:undefined;
        ctrl.voltarLista = function () {
            $location.path('/usuario')
        }
        var obj = ctrl[ctrl.objPrincipal] = {
            ID_NAME: "idUsuario",
            model: {
                nome: "",
                descricao: ""
            },
            editable:true,
            editing: null,
            dataConvert:{
                in: function (data) {
                    return data;
                },
                out: function (data) {
                    return data;
                }
            },
            clear: function () {
                obj.editing = null;
            },
            edit: function (record) {
                if (record) {
                    var tempRecord = angular.extend({}, record);
                    obj.editing = obj.convertData && obj.convertData.in ? obj.convertData.in(tempRecord) : tempRecord;
                }
                else {
                    record = angular.extend({}, obj.model);
                    obj.edit(record);
                }
            },
            save: function (record) {
                var tempRecord = angular.extend({}, record || obj.editing);
                tempRecord = obj.convertData && obj.convertData.out ? obj.convertData.out(tempRecord) : tempRecord;
                if(obj.editing[obj.ID_NAME]){
                    UsuarioFactory.update(tempRecord, function (data) {
                        ctrl.voltarLista();
                    });
                }
                else {
                    UsuarioFactory.save(tempRecord, function (data) {
                        ctrl.voltarLista();
                    });
                }
                ctrl[ctrl.objPrincipal].clear();
            },
            delete: function (record) {
                record = record || obj.editing
                UsuarioFactory.delete({id: record[obj.ID_NAME]},function (){
                    obj.clear();
                });

            }
        }
        if (ctrl.recordID) {
            if (ctrl.recordID > 0) {
                UsuarioFactory.get({id: ctrl.recordID}, function (data) {
                    obj.edit(data);
                });
            }
            else {
                obj.edit();
            }
        }
        else {
            obj.table = new NgTableParams({
                page: 1, // show first page
                count: 10 // count per page

            }, {
                counts: [],
                getData: function (params) {
                    var d = $q.defer();
                    UsuarioFactory.query().$promise.then(function (data) {
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
    .controller("DispositivoAcaoController", ['$scope','NgTableParams',"$filter", "$q",'ModeloDispositivoFactory',"DispositivoAcao","$routeParams",'$location','$uibModal', function ($scope,NgTableParams,$filter, $q,ModeloDispositivoFactory,DispositivoAcao,$routeParams,$location,$uibModal) {
        var ctrl = this;
        ctrl.objPrincipal = "modelodispositivo";
        ctrl.idObjPrincipal = "idModeloDispositivo";

        ctrl.params = $routeParams;
        ctrl.usuarioID = $routeParams && $routeParams.id?$routeParams.id:undefined;


        ctrl.executarAcao = function(acao){
            acao.executing = true;
            DispositivoAcao.execute(acao, function(fim){
                delete acao.executing;
            });
        }

        /*$scope.dispositivos = ModeloDispositivoFactory.query();*/
        ctrl[ctrl.objPrincipal]={};
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

        if(ctrl.usuarioID){
            ctrl.modeloacao = {};
            ctrl.modeloacao.table = new NgTableParams({
                page: 1, // show first page
                count: 10 // count per page

            }, {
                counts: [],
                getData: function (params) {
                    var d = $q.defer();
                    ModeloDispositivoFactory.get({id:ctrl.usuarioID}).$promise.then(function (data) {
                        params.total(data.modeloAcoes.length);
                        d.resolve(data.modeloAcoes);
                    }, function (e) {
                        d.reject(e);
                    });

                    return d.promise;
                }
            });
        }


        ctrl.params = $routeParams;
        ctrl.voltarLista = function () {
            $location.path('/modelodispositivo')
        }

    }])
    .controller("UsuarioPerfilController", ['$scope','NgTableParams',"$filter", "$q",'UserFactory',"PerfilFactory","UsuariosPerfilFactory","$routeParams",'$location','$uibModal',"$route", function ($scope,NgTableParams,$filter, $q,UserFactory,PerfilFactory,UsuariosPerfilFactory,$routeParams,$location,$uibModal,$route) {
        var ctrl = this;
        ctrl.objPrincipal = "usuario";
        ctrl.idObjPrincipal = "idUsuario";

        ctrl.params = $routeParams;
        ctrl.usuarioID = $routeParams && $routeParams.id?$routeParams.id:undefined;

        ctrl.usuarioPerfis = [];
        ctrl.hasAccess = function(perfil){
            return ctrl.usuarioPerfis.indexOf(perfil.idPerfil) != -1
        }
        ctrl.togglePerfil = function(perfil){
            ctrl.$dirty = true;
            if(ctrl.usuarioPerfis.indexOf(perfil.idPerfil) != -1){
                ctrl.usuarioPerfis.splice(ctrl.usuarioPerfis.indexOf(perfil.idPerfil),1);
            }
            else{
                ctrl.usuarioPerfis.push(perfil.idPerfil);
            }
        }
        ctrl.save = function(){
            ctrl.$dirty = true;
            UsuariosPerfilFactory.update({id:ctrl.usuarioID}, ctrl.usuarioPerfis, function(){
                ctrl.clear();
            })
        }
        ctrl.clear = function(){
            $route.reload();
        }

        /*$scope.dispositivos = ModeloDispositivoFactory.query();*/
        ctrl[ctrl.objPrincipal]={};
        ctrl[ctrl.objPrincipal].table = new NgTableParams({
            page: 1, // show first page
            count: 10 // count per page

        }, {
            counts: [],
            getData: function (params) {
                var d = $q.defer();
                UserFactory.query().$promise.then(function (data) {
                    params.total(data.length);
                    d.resolve(data);
                }, function (e) {
                    d.reject(e);
                });

                return d.promise;
            }
        });

        if(ctrl.usuarioID){
            ctrl.usuarioPerfis.splice(0,1000);
            UsuariosPerfilFactory.getPerfis({id:ctrl.usuarioID}, function(data){
                for(var a = 0; a<data.length; a++){
                    ctrl.usuarioPerfis.push(data[a])
                }

            })
            ctrl.usuarioperfil = {};
            ctrl.usuarioperfil.table = new NgTableParams({
                page: 1, // show first page
                count: 10 // count per page

            }, {
                counts: [],
                getData: function (params) {
                    var d = $q.defer();
                    PerfilFactory.query().$promise.then(function (data) {
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
    .controller("PerfilAcessoController", ['$scope','NgTableParams',"$filter", "$q",'PerfilFactory',"ModeloDispositivoFactory","PerfilAcessoFactory","$routeParams",'$location','$uibModal',"$route", function ($scope,NgTableParams,$filter, $q,PerfilFactory,ModeloDispositivoFactory,PerfilAcessoFactory,$routeParams,$location,$uibModal,$route) {
        var ctrl = this;
        ctrl.objPrincipal = "perfil";
        ctrl.idObjPrincipal = "idPerfil";

        ctrl.params = $routeParams;
        ctrl.perfilID = $routeParams && $routeParams.id?$routeParams.id:undefined;

        ctrl.perfiModelos = [];
        ctrl.hasAccess = function(modeloDispositivo){
            return ctrl.perfiModelos.indexOf(modeloDispositivo.idModeloDispositivo) != -1
        }
        ctrl.togglePerfil = function(modeloDispositivo){
            ctrl.$dirty = true;
            if(ctrl.perfiModelos.indexOf(modeloDispositivo.idModeloDispositivo) != -1){
                ctrl.perfiModelos.splice(ctrl.perfiModelos.indexOf(modeloDispositivo.idModeloDispositivo),1);
            }
            else{
                ctrl.perfiModelos.push(modeloDispositivo.idModeloDispositivo);
            }
        }
        ctrl.save = function(){
            ctrl.$dirty = true;
            PerfilAcessoFactory.update({id:ctrl.perfilID}, ctrl.perfiModelos, function(){
                ctrl.clear();
            })
        }
        ctrl.clear = function(){
            $route.reload();
        }


        ctrl[ctrl.objPrincipal]={};
        ctrl[ctrl.objPrincipal].table = new NgTableParams({
            page: 1, // show first page
            count: 10 // count per page

        }, {
            counts: [],
            getData: function (params) {
                var d = $q.defer();
                PerfilFactory.query().$promise.then(function (data) {
                    params.total(data.length);
                    d.resolve(data);
                }, function (e) {
                    d.reject(e);
                });

                return d.promise;
            }
        });

        if(ctrl.perfilID){
            ctrl.perfiModelos.splice(0,1000);
            PerfilAcessoFactory.getPerfis({id:ctrl.perfilID}, function(data){
                for(var a = 0; a<data.length; a++){
                    ctrl.perfiModelos.push(data[a])
                }

            })
            ctrl.perfilacesso = {};
            ctrl.perfilacesso.table = new NgTableParams({
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
    .controller("ModeloDispositivoController", ['$scope','NgTableParams',"$filter", "$q",'ModeloDispositivoFactory','DispositivoFactory','ModeloAcaoFactory',"$routeParams",'$location','$uibModal', function ($scope,NgTableParams,$filter, $q,ModeloDispositivoFactory,DispositivoFactory,ModeloAcaoFactory,$routeParams,$location,$uibModal) {
        var ctrl = this;
        ctrl.objPrincipal = "modeloDispositivo";
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
            dataConvert:{
                in: function (data) {
                    return data;
                },
                out: function (data) {
                    if(ctrl.modeloDispositivo.editing[ctrl.idObjPrincipal]){
                        data.modeloDispositivo = {};
                        data.modeloDispositivo[ctrl.idObjPrincipal] = ctrl.modeloDispositivo.editing[ctrl.idObjPrincipal];
                    }
                    return data;
                }
            },
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

            save: function () {
                if(this.editing[this.ID_NAME]){
                    ModeloAcaoFactory.update(this.dataConvert.out(this.editing), function (data) {
                    });
                }
                else {
                    ModeloAcaoFactory.save(this.dataConvert.out(this.editing), function (data) {
                    });
                }
                this.clear();
            },
            delete: function (record) {
                if (record[this.ID_NAME]) {
                    ctrl.modeloAcao.removeList.push(record);
                }
                for (var idx = 0; this.editing.scheduleParams.length; idx++) {
                    if (record == this.editing.scheduleParams[idx]) {
                        this.editing.scheduleParams.splice(idx, 1);
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
            //$scope.$close($scope.selected.item);
            $scope.$close();
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



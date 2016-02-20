angular.module("cdweb", ['ngRoute','ui.bootstrap', 'ngResource', 'ngTable'])
    .run(["$rootScope","$location",function($rootScope,$location){
    	$rootScope.headersadassa = true;
        $rootScope.go = function ( path ) {
            $location.path( path );
        };
    }]);
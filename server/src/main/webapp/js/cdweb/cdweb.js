angular.module("cdweb", ['ngRoute','ui.bootstrap', 'ngResource', 'ngTable','ngCookies','ngAnimate'])
    .run(["$rootScope","$location","AuthenticationService",function($rootScope,$location,AuthenticationService){
    	$rootScope.headersadassa = true;
        $rootScope.go = function ( path ) {
            $location.path( path );
        };
        $rootScope.$watch("authorized",function(newValue,oldValue){
            console.log("[authorized] NEW=" + newValue + ", OLD="+oldValue)
            if(newValue != oldValue && newValue == false){
                console.log("[WATCH]");
                $rootScope.logout();
            }
        });
        $rootScope.logout = function(){
            AuthenticationService.clearCredentials();
            if($location.path().indexOf("/login") == -1)
                $location.path("/login");
            $rootScope["authorized"] = false;
            return true;
        }
        $rootScope.validateUser = function(){
            if(AuthenticationService.hasValidCredentials()){
                AuthenticationService.setFromCookies();

                $rootScope["authorized"] = true;
                return true;
            }
            else{
                $rootScope.logout();
                $rootScope["authorized"] = false;
                return false;
            }
        }
        $rootScope.validateUser();
    }]);
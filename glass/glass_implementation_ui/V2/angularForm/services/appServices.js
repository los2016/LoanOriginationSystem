'use strict';
 
angular.module('myApp')
 
.factory('AuthenticationService',
    ['$http', '$rootScope', '$timeout',
    function ($http, $rootScope, $timeout) {
        var service = {};
        service.getCredentials = function (callback) {
			var response = { userName: "testUser",pageNumber:1};
			callback(response);
        };
        return service;
    }]);
(function(angular){

	"use strict";

	angular.module("mainModule").service("HTTPInterfaceProvider",["$rootScope","$http","$q",function($rootScope,$http,$q)
	{

		this.initHTTPConnection = function(HTTP_SERVICE_INDICATOR, HTTP_REQUEST,HTTP_DATA)
		{

			var SERVICE_URL="";
			
			switch(HTTP_SERVICE_INDICATOR)
			{
				case "AUTH_USER":
				SERVICE_URL = "http://google.com";
				break;

			}

			return makeHTTPRequest(SERVICE_URL,HTTP_REQUEST,HTTP_DATA);
		};


		function makeHTTPRequest(SERVICE_URL,HTTP_REQUEST,HTTP_DATA)
		{
			var deferred = $q.defer();
			
			$http({
				method:HTTP_REQUEST,
				url:SERVICE_URL,
				data:HTTP_DATA,
				headers:"Content-Type:'application/json'"
			}).then(function successCallback(RESPONSE){
					return deferred.resolve(RESPONSE);
				},
				function errorCallback(RESPONSE){
					return deferred.reject(RESPONSE);
				});
		};
	}]);
	
})(window.angular);

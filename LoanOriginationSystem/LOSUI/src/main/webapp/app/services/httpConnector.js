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
				SERVICE_URL = "http://localhost:8080/authenticateUser";
				break;
				
				case "TIMELINE":
				SERVICE_URL = "";
				break;
				
				case "QUESTION_BANK":
				SERVICE_URL = "http://localhost:8080/getMortgageQuestionsMetaData";
				break;
				
				case "VALIDATE_QUESTION":
				SERVICE_URL = "http://localhost:8080/validateQuestion";
				break;

				case "GET_QUESTION_RULE":
				SERVICE_URL = "http://localhost:8080/getRuleforquestions";
				break;
				
				case "GET_MORTGAGE":
				SERVICE_URL = "http://localhost:8080/getMortgageApplicationsDetails";
				break;
				
				case "SAVE_MORTGAGE":
				SERVICE_URL = "http://localhost:8080/saveMortgageApplication";
				break;
				
				case "UPLOAD_FILE":
				SERVICE_URL = "";
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

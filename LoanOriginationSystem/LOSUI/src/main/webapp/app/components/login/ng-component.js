"use strict";

function authenticateUser($scope, $element, $attrs,HTTPInterfaceProvider)
{
	this.authenticateLogin = function()
	{
		var HTTP_POST={};

		HTTP_POST.userid = $scope.userName;
		HTTP_POST.password = $scope.userPassword;

		var Q_Obj = HTTPInterfaceProvider.initHTTPConnection("AUTH_USER","POST",HTTP_POST);
		Q_Obj.then(function(RESPONSE_DATA){

		});
	}
}

angular.module("mainModule").component("appLogin",{
	templateUrl:"/app/components/login/ng-template.html",
	controller:authenticateUser
});
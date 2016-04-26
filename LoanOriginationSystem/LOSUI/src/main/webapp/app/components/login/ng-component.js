(function(angular)
{
	"use strict";

	function authenticateUser($scope, $element, $attrs,HTTPInterfaceProvider,loggedUser)
	{

		var $ctrl = this;

		this.$routerOnActivate = function()
		{

			if(loggedUser.isValid)
			{
				this.$router.navigate(['Home']);				
			}

			$ctrl.authenticateLogin = function()
			{
				var HTTP_POST={};

				HTTP_POST.userid = $scope.userName;
				HTTP_POST.password = $scope.userPassword;
				
				var Q_Obj = HTTPInterfaceProvider.initHTTPConnection("AUTH_USER","POST",HTTP_POST);
				
				//Q_Obj.then(function(RESPONSE_DATA){

				//	if(RESPONSE_DATA.status==200 && RESPONSE_DATA.data.returnType=="success")
				//	{
				//		loggedUser.setUserInformation(RESPONSE_DATA.data);
						loggedUser.isValid=true;
						this.$router.navigate(['Home']);
				//	}
				//	else
				//	{
				//		console.log("error in login");
				//	}

				// });
				
			};
		}	
	}

	angular.module("mainModule").component("appLogin",{
		templateUrl:"/app/components/login/ng-template.html",
		controller:authenticateUser,
		bindings:{$router: '<'}
	});
})(window.angular);
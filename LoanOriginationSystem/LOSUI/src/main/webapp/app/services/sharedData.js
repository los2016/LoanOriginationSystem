(function(angular){

	"use strict";

	angular.module("mainModule").factory("loggedUser",function()
	{
		return {

			userInformation:{},
			isValid:false,

			setUserInformation:function(userInfo)
			{
				userInformation = userInfo;
			},

			getUserInformation:function()
			{
				return userInformation;
			}
		};

	});

})(window.angular);
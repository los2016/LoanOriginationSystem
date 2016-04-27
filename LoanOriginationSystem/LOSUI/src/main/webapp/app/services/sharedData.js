(function(angular){

	"use strict";

	angular.module("mainModule").factory("loggedUser",function()
	{
		return {

			userInformation:{},
			isValid:false,

			setUserInformation:function(userInfo)
			{
				this.userInformation = userInfo;
			},

			getUserInformation:function()
			{	
				return this.userInformation;
			}
		};

	});


	angular.module("mainModule").factory("questionBank",function()
	{
		return {

			allQuestions:{},
			allRules:{},
			customerType:{},

			setQuestions:function(obj)
			{
				this.allQuestions = obj;
			},
			setQuestionRules:function(obj)
			{
				this.allRules = obj;
			},
			setCustomerType:function(obj)
			{
				this.customerType = obj;
			},

			getQuestions:function()
			{
				return this.allQuestions;
			},
			getQuestionRules:function()
			{
				return this.allRules;
			},
			getCustomerType:function()
			{
				return this.customerType;
			}
		};

	});


})(window.angular);
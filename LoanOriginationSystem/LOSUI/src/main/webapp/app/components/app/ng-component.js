(function(angular)
{
	"use strict";

	angular.module("mainModule").component("mainApp",{
		templateUrl:"/app/components/app/ng-template.html",
		$routeConfig:[
			{
				path:"/login/",
				name:"Login",
				useAsDefault:true,
				component:"appLogin"
			},
			{
				path:"/home/",
				name:"Home",
				component:"appHome"
			},
			{
				path:"/loanSummary/...",
				name:"loanSummary",
				component:"appLoanSummary"
			},
			{
				path:"/loanDetail/...",
				name:"loanDetail",
				component:"appLoanDetail"
			}]
	});

})(window.angular);
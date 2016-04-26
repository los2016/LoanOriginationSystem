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
				path:"/loanSummary/",
				name:"LoanSummary",
				component:"appLoanSummary"
			},
			{
				path:"/loanDetail/",
				name:"LoanDetail",
				component:"appLoanDetail"
			}]
	});

})(window.angular);
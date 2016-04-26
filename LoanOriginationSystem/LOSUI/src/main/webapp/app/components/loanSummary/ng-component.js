(function(angular)
{
	"use strict";

	function loanSummaryController($scope, $element, $attrs,HTTPInterfaceProvider,loggedUser)
	{
		var $ctrl = this;

		this.$routerOnActivate = function()
		{
			if(loggedUser.isValid)
			{
				//TODO Code here
			}
			else
			{
				this.$router.navigate(['Login']);
			}
		}	
	}

	angular.module("mainModule").component("appLoanSummary",{
		templateUrl:"/app/components/loanSummary/ng-template.html",
		controller:loanSummaryController,
		bindings:{$router: '<'}
	});
})(window.angular);
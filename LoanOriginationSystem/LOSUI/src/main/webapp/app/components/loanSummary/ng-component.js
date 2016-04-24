(function(angular)
{
	"use strict";

	function loanSummaryController($scope, $element, $attrs,HTTPInterfaceProvider)
	{
		
	}

	angular.module("mainModule").component("appLoanSummary",{
		templateUrl:"/app/components/loanSummary/ng-template.html",
		controller:loanSummaryController
	});
})(window.angular);
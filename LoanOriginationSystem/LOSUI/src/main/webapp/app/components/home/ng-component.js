(function(angular)
{
	"use strict";

	function homeController($scope, $element, $attrs,HTTPInterfaceProvider)
	{
		
	}

	angular.module("mainModule").component("appHome",{
		templateUrl:"/app/components/home/ng-template.html",
		controller:homeController,
	});
})(window.angular);
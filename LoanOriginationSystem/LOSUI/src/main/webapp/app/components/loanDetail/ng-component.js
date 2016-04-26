(function(angular)
{
	"use strict";

	function loanDocumentWorker($scope, $element, $attrs,HTTPInterfaceProvider,loggedUser)
	{
		
		var $ctrl = this;
		var LOAN_DOCUMENT_OBJ = {};

		$ctrl.writeCustomerProperty = function(customer,property,newValue)
		{
			var tmp="";
			var a = property.split("@");

			if(a.length>2)
			{
				var n = a.length-1;

				for(ctr=1;ctr<n;ctr++)
				{
					if(tmp=="")
					{
						tmp = customer[a[ctr]];
					}
					else
					{
						tmp = tmp[a[ctr]];
					}
				}
				tmp[a[n]]= newValue;
			}
			else
			{
				customer[a[1]]=newValue;
			}

		}

		$ctrl.readCustomerProperty = function(customer,property)
		{
			var tmp="";
			var a = property.split("@");
			var returnValue ="";

			if(a.length>2)
			{
				var n = a.length;

				for(ctr=1;ctr<n;ctr++)
				{
					if(tmp=="")
					{
						tmp = customer[a[ctr]];
					}
					else
					{
						tmp = tmp[a[ctr]];
					}
				}
				returnValue = tmp;
			}
			else
			{
				returnValue = customer[a[1]];
			}

			return returnValue;

		}


		$ctrl.getClientType = function(TYPE)
		{
			var JSON_URL=""
			
			if(TYPE=="ENTITY")
			{
				JSON_URL = "entityClient.json";
			}
			else if(TYPE=="INDIVIDUAL")
			{
				JSON_URL = "individualClient.json";
			}

			$.getJSON(JSON_URL, function(result)
			{
				$ctrl.LOAN_DOCUMENT_OBJ = result.data;
			});
		}
		

		$ctrl.loadQuestionBank = function()
		{

		}

		$ctrl.getLoanDocument = function(LOAN_ID)
		{
			
		}

		$ctrl.saveLoanDocument=function()
		{


		}

		$ctrl.updateLoanDocument=function()
		{

			
		}

		$ctrl.$routerOnActivate = function()
		{
			if(loggedUser.isValid)
			{
				//TODO Code here
				LOAN_DOCUMENT_OBJ = $ctrl.getLoanDocument($ctrl.LOAN_ID);
				$ctrl.loadQuestionBank();
			}
			else
			{
				this.$router.navigate(['Login']);
			}
		}	
	
	}

	angular.module("mainModule").component("appLoanDetail",{
		templateUrl:"/app/components/loanDetail/ng-template.html",
		controller:loanDocumentWorker,
		$routeConfig:[
		{
			path:"/timeline/",
			name:"Timeline",
			useAsDefault:true,
			component:"appLoanTimeline"
		}],
		bindings:{
			$router: '<',
			LOAN_ID:'<'
		}
	});

})(window.angular);
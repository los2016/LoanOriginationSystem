(function(angular)
{
	"use strict";

	function loanDocumentWorker($scope, $element, $attrs,HTTPInterfaceProvider,loggedUser,questionBank)
	{
		
		var $ctrl = this;

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

		$ctrl.loadQuestionRule = function()
		{
			var HTTP_POST={};
			HTTP_POST.questionBank="mortgageLOS";

			var Q_Obj = HTTPInterfaceProvider.initHTTPConnection("GET_QUESTION_RULE","POST",HTTP_POST);
				
			Q_Obj.then(function(RESPONSE_DATA){

				if(RESPONSE_DATA.status==200 && RESPONSE_DATA.data.returnType=="success")
				{
					console.log("success - loanDetail");
					questionBank.setQuestionRules(RESPONSE_DATA.data);
				}
				if(RESPONSE_DATA.status==200 && RESPONSE_DATA.data.returnType=="warning")
				{
					console.log("warning - loanDetail");
				}
				if(RESPONSE_DATA.status==200 && RESPONSE_DATA.data.returnType=="error")
				{
					console.log("error - loanDetail");
				}
				else
				{
					console.log("other error - loanDetail");
				}

			});

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

		$ctrl.getLoanDocument = function(LOAN_ID)
		{
			// This will load the customer object entity or individual based on the getClientType()
			var HTTP_POST = {};
			HTTP_POST.clientOrAdvisor = "";
			HTTP_POST.clientOrAdvisorId = "";
			questionBank.setCustomerType(RESPONSE_DATA.data);

		}

		$ctrl.saveLoanDocument=function(DO_EVENT)
		{
			var HTTP_POST={};
			HTTP_POST.questionBank="mortgageLOS";
			HTTP_POST.saveType = DO_EVENT;
			HTTP_POST.morgatgeApplicatioObject = LOAN_DOCUMENT_OBJ;

			var Q_Obj = HTTPInterfaceProvider.initHTTPConnection("SAVE_MORTGAGE","POST",HTTP_POST);
				
			Q_Obj.then(function(RESPONSE_DATA){

				if(RESPONSE_DATA.status==200 && RESPONSE_DATA.data.returnType=="success")
				{
					console.log("success - loanDetail");
					LOAN_QUESTIONS = RESPONSE_DATA.data;
				}
				if(RESPONSE_DATA.status==200 && RESPONSE_DATA.data.returnType=="warning")
				{
					console.log("warning - loanDetail");
				}
				if(RESPONSE_DATA.status==200 && RESPONSE_DATA.data.returnType=="error")
				{
					console.log("error - loanDetail");
				}
				else
				{
					console.log("other error - loanDetail");
				}

			});
		}


		$ctrl.$routerOnActivate = function()
		{
			if(loggedUser.isValid)
			{
				$scope.userProfile = loggedUser.getUserInformation();
				//TODO Code here
				//LOAN_DOCUMENT_OBJ = $ctrl.getLoanDocument($ctrl.LOAN_ID);
				//$ctrl.loadQuestionBank();
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
			path:"/detail/",
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
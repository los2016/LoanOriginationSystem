(function(angular)
{
	"use strict";

	function loanTimeline($scope, $element, $attrs,$q,HTTPInterfaceProvider,loggedUser,questionBank)
	{
		var $ctrl = this;
		
		this.$routerOnActivate = function()
		{
			$ctrl.section="abc farms";
			if(loggedUser.isValid)
			{
				//TODO Code here
				$ctrl.loadQuestionBank();
				$ctrl.loadTimeLineData();
			}
			else
			{
				//this.$router.navigate(['Login']);
			}
		}

		$ctrl.loadTimeLineData = function () {

			

			
		}

		$ctrl.loadQuestionBank = function()
		{
			var HTTP_POST={};
			var Q_Obj = HTTPInterfaceProvider.initHTTPConnection("QUESTION_BANK","GET",HTTP_POST);
				
			Q_Obj.then(function(RESPONSE_DATA){

				if(RESPONSE_DATA.status==200 && RESPONSE_DATA.data.returnType=="success")
				{
					questionBank.setQuestions(RESPONSE_DATA.data.sections);
					$ctrl.renderQuestionsInUI(21,-1);
				}
				if(RESPONSE_DATA.status==200 && RESPONSE_DATA.data.returnType=="warning")
				{
					console.log("warning - loanDetail");
				}
				if(RESPONSE_DATA.status==200 && RESPONSE_DATA.data.returnType=="error")
				{
					console.log("error - loanDetail");
				}

			});
		}	

		$ctrl.renderQuestionsInUI=function(sectionID,subSectionID)
		{
			var allQuestions;

			var deferred = $ctrl.getSpecificSection(questionBank.getQuestions(),sectionID,subSectionID);

			deferred.then(function(RESPONSE_DATA){
				allQuestions = RESPONSE_DATA;
				console.log("wait 4");

				var QuestionObj = [];
				console.log(allQuestions);

				if(allQuestions.questions.length>0)
				{
					console.log("parent");
					$ctrl.readQuestions(allQuestions.questions,QuestionObj);
				}
			});
		}

		$ctrl.readQuestions=function(Q_OBJ,QuestionObj)
		{
			var HTML_STRING = "";
			var obj_attributes = [];

			for(var ctr=0;ctr<Q_OBJ.length;ctr++) // looping questions
			{
				var q = Q_OBJ[ctr];

				if(typeof(q) != "undefined") // push based on roleID
				{
					HTML_STRING = "<div id='"+q.questionId+"' class='form-group'><label class='control-label'>"+q.questionLongDesc+"</label>";
					HTML_STRING = HTML_STRING  + "<div>";
					var COMPONENT = q.attributes;
					for(var xtr=0;xtr<COMPONENT.length;xtr++) // looping attributes
					{
						var c = COMPONENT[xtr];
						console.log(c.dataType.dataTypeNm);

						if(c.dataType.dataTypeNm=="Choice")
						{
							var len = c.listOfValues.length;
							if (len<=4)
							{
								for(var ptr=0; ptr<len;ptr++) // looping data
								{
									var v = c.listOfValues;
									console.log("in choice")
									HTML_STRING = HTML_STRING + "<input  class='form-control' type='radio' id='"+c.attributeId+"' name=N'"+c.attributeId+"' value='"+v[ptr].lookupCd+"'>"+v[ptr].lookupValue;
								} // looping data ends
							}
							else
							{

								HTML_STRING = HTML_STRING + "<select  class='form-control' id='"+c.attributeId+"' name=N'"+c.attributeId+"'";

								for(var ptr=0; ptr<len;ptr++) // looping data
								{
									var v = c.listOfValues;
									console.log("in choice")
									HTML_STRING = HTML_STRING + "<option value='"+v[ptr].lookupCd+"'>"+v[ptr].lookupValue+"</option>";
								} // looping data ends
								HTML_STRING = HTML_STRING + "</select>";
							}
							
						}

						if(c.dataType.dataTypeNm=="Multi-Choice")
						{
							var len = c.listOfValues.length;
							for(var ptr=0; ptr<len;ptr++) // looping data
							{
								var v = c.listOfValues;
								HTML_STRING = HTML_STRING + "<input  class='form-control' type='checkbox' id='"+c.attributeId+"' name=N'"+c.attributeId+"' value='"+v[ptr].lookupCd+"'>"+v[ptr].lookupValue;
							} // looping data ends
							
						}

						if(c.dataType.dataTypeNm=="Text")
						{

							HTML_STRING = HTML_STRING + "<input  class='form-control' type='text' id='"+c.attributeId+"' name=N'"+c.attributeId+"' size=30 placeholder='"+c.colDesc+"'>";
						}

						if(c.dataType.dataTypeNm=="Year")
						{
							HTML_STRING = HTML_STRING + "<input  class='form-control' type='text' id='"+c.attributeId+"' name=N'"+c.attributeId+"' size=30 placeholder='"+c.colDesc+"'>";
						}

						if(c.dataType.dataTypeNm=="Date")
						{
							HTML_STRING = HTML_STRING + "<input  class='form-control' type='text' id='"+c.attributeId+"' name=N'"+c.attributeId+"' size=30 placeholder='"+c.colDesc+"'>";
						}

						if(c.dataType.dataTypeNm=="File")
						{
							HTML_STRING = HTML_STRING + "<input  class='form-control' type='file' id='"+c.attributeId+"' name=N'"+c.attributeId+"'>";
						}

						if(c.dataType.dataTypeNm=="Interest")
						{
							HTML_STRING = HTML_STRING + "<input  class='form-control' type='number' id='"+c.attributeId+"' name=N'"+c.attributeId+"' size=30 placeholder='"+c.colDesc+"'>";
						}

						if(c.dataType.dataTypeNm=="Amount")
						{
							HTML_STRING = HTML_STRING + "<input  class='form-control' type='number' id='"+c.attributeId+"' name=N'"+c.attributeId+"' size=30 placeholder='"+c.colDesc+"'>";
						}

						if(c.dataType.dataTypeNm=="SSN")
						{
							HTML_STRING = HTML_STRING + "<input  class='form-control' type='text' id='"+c.attributeId+"' name=N'"+c.attributeId+"' size=30 placeholder='"+c.colDesc+"'>";
						}

						obj_attributes.push({
							"id":c.attributeId,
							"ref":c.objectAttrFQN
						});
					} // looping attributes ends
					
					HTML_STRING = HTML_STRING + "</div></div>";

					QuestionObj.push(
					{	"questionID":q.questionId,
						"mandatory":q.mandatoryCd,
						"context":q.questionContext.questionContextNm,
						"component":obj_attributes
					});

				} // If ends - if questions?

				$("#frm_detail").append(HTML_STRING);
				//console.log(HTML_STRING);

			} // looping question ends

		} // readQuestions(...) ends






		
		$ctrl.getSpecificSection=function(listOfQuestions,sectionID,subSectionID)
		{

			var rtnObj = _.filter(listOfQuestions, function(o){
				return o.sectionId==sectionID
			});

			var deferred = $q.defer();

			console.log(rtnObj);
			if(subSectionID != -1)
			{
				recData(rtnObj[0].childSections,subSectionID,deferred);
				console.log("wait");
			}
			else
			{
				deferred.resolve(rtnObj[0]);
			}
			return deferred.promise;
		}
		
		function recData(sections,subSectionID,deferred)
		{
			console.log("recdata")
			console.log(sections)
			var rtnObj=[];
			var flag=false;

			for(var ctr=0;ctr<sections.length;ctr++)
			{
				console.log("inside for");
				console.log(sections[ctr]);
				console.log(sections[ctr]["sectionId"]);
				if(sections[ctr]["sectionId"]==subSectionID)
				{
					rtnObj =sections[ctr];
					flag=true;
					break;
				}
				else
				{
					if(sections[ctr].childSections)
					{
						recData(sections[ctr].childSections,subSectionID,deferred);
					}
				}
			}
			
			if(flag)
			{
				deferred.resolve(rtnObj);
				return deferred.promise;
			}

		}


	};

	angular.module("mainModule").component("appLoanTimeline",{
		templateUrl:"/app/components/loanTimeline/ng-template.html",
		controller:loanTimeline,
		$routeConfig:[
		{
			path:"/loanSummary/timeline",
			name:"Timeline",
			useAsDefault:true,
			component:"appLoanTimeline"
		}],
		bindings:{$router: '<'}
	});
})(window.angular);
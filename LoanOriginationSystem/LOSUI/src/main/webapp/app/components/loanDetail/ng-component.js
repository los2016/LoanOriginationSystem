"use strict";

function Customer([])
{

	this.getCustomerProfile=function()
	{
		
		
		
		
	}


	var j = "@entityClient@legalAddress@contactInfo@workemail";
	var k = "@applicationID";
	var l = "@entityClient@isHighlySensitive";

this.writeCustomerProperty = function(customer,property,newValue)
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

this.readCustomerProperty = function(customer,property)
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

this.writeCustomerProperty(abc,k,600);
console.log(this.readCustomerProperty(abc,k));

}
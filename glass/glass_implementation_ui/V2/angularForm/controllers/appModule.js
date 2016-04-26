'use strict';

angular.module('myApp', []).controller('myCtrl', function($scope,AuthenticationService, $timeout) {
      var stompClient = null;
      var socket = null;
      var whoami = null;
      var pageId = null;
      var existingUsers = [];

      function connect() {
        socket = new SockJS('http://localhost:8080/glass/chat');
        stompClient = Stomp.over(socket);
        stompClient.connect('', '', function(frame) {
          stompClient.subscribe('/user/queue/messages', function(message) {
            showMessage(JSON.parse(message.body));
          });
          stompClient.subscribe('/topic/active', function(activeMembers) {
            showActive(activeMembers);
          });
        });
      }
      
      function showActive(activeMembers) {
        stompClient.send('/app/activeUsers', {}, JSON.stringify({
            'sender' : whoami
          }));
		  var users = JSON.parse(activeMembers.body);
		for(var i=0;i<users.length;i++){
			var content = $('<li class="list-group-item">'+users[i]+'</li>');
			var selContent = $('<option value="'+users[i]+'">'+users[i]+'</option>');
			var found = $.inArray(users[i], existingUsers) > -1;
			if(!found){
			$('#connectedUsers').append(content);
			$('#inputTo').append(selContent);
			}
			existingUsers.push(users[i]);
		}
      }
      
      function disconnect() {
        stompClient.disconnect();
        console.log("Disconnected");
      }

      function sendMessageTo(user, chatInput,target, isGlass) {
        var message = chatInput;
        if (!message) {
        	message = '';
        }
        stompClient.send("/app/chat", {}, JSON.stringify({
          'recipient': user,
          'targetElement' : target,
          'message' : message,
          'sender' : whoami,
          'targetPageId' : pageId,
          'glass' : isGlass
        }));
      }	
	  
	  function showMessage(message) {
    	  console.log(message);
    	  if(message.recipient == whoami) {
    		  var targetElement = '#' + message.targetElement;
			  $(targetElement).parent().parent().find('input').removeClass('glow');
			  $(targetElement).parent().parent().find('textarea').removeClass('glow');
			  $(targetElement).addClass('glow');
    		  //glass true means trnasfer form
    		  if($(targetElement) && message.targetPageId == pageId && message.glass) {
    		  	$(targetElement).val(message.message)
    		  }
    		  if(!message.glass) { //work as a chat
    			  console.log("message sent : " + message.message);
    		  }
    	  }
      }

	$scope.$watch('dataQuery', function() {
		if($scope.dataTo){
			sendMessageTo($scope.dataTo, $scope.dataQuery,'inputQuery',true);
		}
	});
	$scope.$watch('dataMessage', function() {
		if($scope.dataTo){
			sendMessageTo($scope.dataTo, $scope.dataMessage,'inputMessage', true);
		}
	});
	$scope.$watch('dataContact', function() {
		if($scope.dataTo){
			sendMessageTo($scope.dataTo, $scope.dataContact,'inputContact', true);
		}
	});
	$scope.$watch('dataEmail', function() {
		if($scope.dataTo){
			sendMessageTo($scope.dataTo, $scope.dataEmail,'inputEmail', true);
		}
	});
	  angular.element(document).ready(function () {
	  	  AuthenticationService.getCredentials(function(response) {
			whoami = response.userName;
			pageId = response.pageNumber;
		  });
    	  $('#inputFrom').val(whoami);
		  $("#inputTo option[value='? undefined:undefined ?']").remove();
		  $("#inputTo").val($("#inputTo option:first").val());
		  connect();
    });
});

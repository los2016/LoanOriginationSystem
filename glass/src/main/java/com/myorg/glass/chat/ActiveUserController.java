package com.myorg.glass.chat;

import java.security.Principal;

import javax.inject.Inject;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ActiveUserController {
  
  private ActiveUserService activeUserService;

  @Inject
  public ActiveUserController(ActiveUserService activeUserService) {
    this.activeUserService = activeUserService;
  }
  
  @MessageMapping("/activeUsers")
  public void activeUsers(Message<Object> message, @Payload ChatMessage chatMessage) {
    //Principal user = message.getHeaders().get(SimpMessageHeaderAccessor.USER_HEADER, Principal.class);
    //activeUserService.mark(user.getName());
	  System.out.println("active user : " + chatMessage.getSender());
	  activeUserService.mark(chatMessage.getSender());
  }

}

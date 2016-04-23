package com.myorg.glass.chat;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

public class HandshakeHandler extends DefaultHandshakeHandler {

	@Override
	protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
			Map<String, Object> attributes) {
		Principal principal = request.getPrincipal();
		if(principal != null) {
			System.out.println("principal : " + principal.getName());
		}
		if (principal == null) {
			ServletServerHttpRequest req = (ServletServerHttpRequest) request;
			HttpSession session = req.getServletRequest().getSession();
			final String user = (String) session.getAttribute("user");
			System.out.println("user : " + req.getServletRequest().getParameter("user"));
			principal = new Principal() {
				@Override
				public String getName() {
					return user != null ? user : "";
				}
			};
		}
		return principal;
	}

}

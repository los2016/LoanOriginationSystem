package com.myorg.document.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DocumentController {

	@RequestMapping("/home")
	public String index() {
		return "FileUpload.html";
	}
}

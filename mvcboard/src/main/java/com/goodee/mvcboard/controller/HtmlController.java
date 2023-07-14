package com.goodee.mvcboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {
	@GetMapping("/html/localNameListGraph")
	public String localNameListGraph() {
		return "/html/localNameListGraph";
	}
}

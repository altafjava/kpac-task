package com.altafjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.altafjava.model.KnowledgePackage;
import com.altafjava.service.KpacService;

@Controller
@RequestMapping(value = "/kpacs")
public class KpacController {

	@Autowired
	private KpacService kpacService;

	@GetMapping
	public String getKpacs() {
		return "kpacs";
	}

	@PostMapping
	public String addKpac(@ModelAttribute("knowledgePackage") KnowledgePackage knowledgePackage) {
		kpacService.saveKpac(knowledgePackage);
		return "redirect:/kpacs";
	}

}

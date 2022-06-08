package com.altafjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.altafjava.model.KnowledgePackageSet;
import com.altafjava.service.KpacSetService;

@Controller
@RequestMapping(value = "/sets")
public class KpacSetController {

	@Autowired
	private KpacSetService kpacSetService;

	@GetMapping
	public String getKpacSets(Model model) {
		return "sets";
	}

	@PostMapping
	public String addKpacSet(@ModelAttribute("knowledgePackageSet") KnowledgePackageSet knowledgePackageSet) {
		kpacSetService.saveKpacSet(knowledgePackageSet);
		return "redirect:/sets";
	}

}

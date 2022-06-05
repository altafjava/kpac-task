package com.altafjava.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.altafjava.model.KnowledgePackage;
import com.altafjava.service.KpacSetService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value = "/set")
public class KpacRelationController {

	@Autowired
	private KpacSetService kpacSetService;

	@GetMapping("/{id}")
	public String getKpacsFromSet(Model model, @PathVariable("id") int id) throws IOException {
		List<KnowledgePackage> knowledgePackages = kpacSetService.getKpacsFromSet(id);
		ObjectMapper objectMapper = new ObjectMapper();
		String knowledgePackagesJsonString = objectMapper.writeValueAsString(knowledgePackages);
		model.addAttribute("knowledgePackagesJsonString", knowledgePackagesJsonString);
		return "kpacs-from-kpacset";
	}

}

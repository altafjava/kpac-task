package com.altafjava.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.altafjava.model.KnowledgePackage;
import com.altafjava.model.KnowledgePackageSet;
import com.altafjava.service.KpacService;
import com.altafjava.service.KpacSetService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value = "/sets")
public class KpacSetController {

	@Autowired
	private KpacService kpacService;
	@Autowired
	private KpacSetService kpacSetService;

//	@GetMapping
//	public String getKpacSets(Model model) throws IOException {
//		List<KnowledgePackageSet> knowledgePackageSets = kpacSetService.getAllKpacSet();
//		ObjectMapper objectMapper = new ObjectMapper();
//		String knowledgePackageSetsJsonString = objectMapper.writeValueAsString(knowledgePackageSets);
//		model.addAttribute("knowledgePackageSetsJsonString", knowledgePackageSetsJsonString);
//		List<KnowledgePackage> knowledgePackages = kpacService.getAllKpacs();
//		String knowledgePackagesJsonString = objectMapper.writeValueAsString(knowledgePackages);
//		model.addAttribute("knowledgePackagesJsonString", knowledgePackagesJsonString);
//		return "sets";
//	}

	@GetMapping
	public String getKpacSets(Model model) {
		return "sets";
	}

	@PostMapping
	public String addKpacSet(@ModelAttribute("knowledgePackageSet") KnowledgePackageSet knowledgePackageSet) {
		kpacSetService.saveKpacSet(knowledgePackageSet);
		return "redirect:/sets";
	}

//	@GetMapping("/delete/{id}")
//	public String deleteKpacSet(@PathVariable("id") int id) {
//		kpacSetService.deleteKpacSet(id);
//		return "redirect:/sets";
//	}

}

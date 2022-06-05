package com.altafjava.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.altafjava.model.KnowledgePackage;
import com.altafjava.service.KpacService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value = "/kpacs")
public class KpacController {

	@Autowired
	private KpacService kpacService;

	@GetMapping
	public String getKpacs(Model model) throws IOException {
//		List<KnowledgePackage> knowledgePackages = kpacService.getAllKpacs();
//		ObjectMapper objectMapper = new ObjectMapper();
//		String knowledgePackagesJsonString = objectMapper.writeValueAsString(knowledgePackages);
//		model.addAttribute("knowledgePackagesJsonString", knowledgePackagesJsonString);
		return "kpacs";
	}

	@PostMapping
	public String addKpac(@ModelAttribute("knowledgePackage") KnowledgePackage knowledgePackage) throws IOException {
		kpacService.saveKpac(knowledgePackage);
		return "redirect:/kpacs";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteKpac(@PathVariable(value = "id") int id) {
		kpacService.deleteKpac(id);
		return "redirect:/kpacs";
	}

	@ResponseBody
	@GetMapping(value = "/api/kpacs", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<KnowledgePackage> loadAll() {
		List<KnowledgePackage> knowledgePackages = kpacService.getAllKpacs();
		return knowledgePackages;
	}
	
	@ResponseBody
	@DeleteMapping(value = "/api/kpacs/{id}")
	public void delete(@PathVariable int id) {
		kpacService.deleteKpac(id);
	}

}

package com.altafjava.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.altafjava.model.KnowledgePackage;
import com.altafjava.model.KnowledgePackageSet;
import com.altafjava.service.KpacService;
import com.altafjava.service.KpacSetService;

@Controller
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private KpacService kpacService;
	@Autowired
	private KpacSetService kpacSetService;

	@ResponseBody
	@GetMapping(value = "/kpacs", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<KnowledgePackage> getKpacs() {
		List<KnowledgePackage> knowledgePackages = kpacService.getAllKpacs();
		return knowledgePackages;
	}

	@ResponseBody
	@DeleteMapping(value = "/kpacs/{id}")
	public void deleteKpac(@PathVariable int id) {
		kpacService.deleteKpac(id);
	}

	@ResponseBody
	@GetMapping(value = "/sets", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<KnowledgePackageSet> getKpacSets() {
		return kpacSetService.getAllKpacSet();
	}

	@ResponseBody
	@DeleteMapping("/sets/{id}")
	public void deleteKpacSet(@PathVariable int id) {
		kpacSetService.deleteKpacSet(id);
	}

	@ResponseBody
	@GetMapping(value = "/kpacs/{setId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<KnowledgePackage> getKpacsFromSet(@PathVariable int setId) {
		return kpacSetService.getKpacsFromSet(setId);
	}
}

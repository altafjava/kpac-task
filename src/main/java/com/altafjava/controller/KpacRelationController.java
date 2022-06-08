package com.altafjava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/set")
public class KpacRelationController {

	@GetMapping("/{kpacSetId}")
	public String getKpacsFromSet(Model model, @PathVariable int kpacSetId) {
		model.addAttribute("kpacSetId", kpacSetId);
		return "kpacs-from-kpacset";
	}

}

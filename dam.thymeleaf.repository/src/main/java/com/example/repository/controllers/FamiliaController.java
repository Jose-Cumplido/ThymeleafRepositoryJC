package com.example.repository.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.repository.services.FamiliaService;

@Controller
@RequestMapping("/admin/familia")
public class FamiliaController {
	
	@Autowired
	private FamiliaService familiaService;

	@GetMapping("/")
	public String index(Model model) {					
		model.addAttribute("familias", familiaService.findAll());
		return "admin/list-familia";
	}
}

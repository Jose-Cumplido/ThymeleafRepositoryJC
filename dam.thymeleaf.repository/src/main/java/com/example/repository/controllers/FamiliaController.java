package com.example.repository.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.repository.model.Familia;
import com.example.repository.services.EspecieService;
import com.example.repository.services.FamiliaService;

@Controller
@RequestMapping("/admin/familia")
public class FamiliaController {

	@Autowired
	private FamiliaService familiaService;

	@Autowired
	private EspecieService especieService;

	@GetMapping("/")
	public String index(Model model) {					
		model.addAttribute("familias", familiaService.findAll());
		return "admin/list-familia";
	}

	@GetMapping("/nueva")
	public String nuevaFamilia(Model model) {
		model.addAttribute("familia", new Familia());
		return "admin/form-familia";
	}

	@PostMapping("/nueva/submit")
	public String submitNuevaFamilia(@ModelAttribute("familia") Familia familia, Model model) {

		familiaService.save(familia);

		return "redirect:/admin/familia/";
	}

	@GetMapping("/editar/{id}")
	public String editarFamilia(@PathVariable("id") Long id, Model model) {

		Familia familia = familiaService.findById(id);

		if (familia != null) {
			model.addAttribute("familia", familia);
			return "admin/form-familia";
		} else {
			return "redirect:/admin/familia/";
		}
	}

	@GetMapping("/borrar/{id}")
	public String borrarfamilia(@PathVariable("id") Long id, Model model) {

		Familia familia = familiaService.findById(id);

		if (familia != null) {

			if (especieService.numeroEspeciesFamilia(familia) == 0) {
				familiaService.delete(familia);				
			} else {
				return "redirect:/admin/damilia/?error=true";
			}	
		}
		return "redirect:/admin/familia/";
	}
}

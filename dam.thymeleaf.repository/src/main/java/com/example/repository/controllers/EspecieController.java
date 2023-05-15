package com.example.repository.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.repository.model.Especie;

import com.example.repository.services.EspecieService;
import com.example.repository.services.FamiliaService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/admin/especie")
public class EspecieController {
	
	@Autowired
	private EspecieService especieService;
	
	@Autowired
	private FamiliaService familiaService;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("especies", especieService.findAll());
		return "admin/list-especie";
	}	
	
	@GetMapping("/nuevo")
	public String nuevoProducto(Model model) {
		model.addAttribute("especie",new Especie());
		model.addAttribute("familias",this.familiaService.findAll());
		return "admin/form-especie";
	}
	
	@PostMapping("/nuevo/submit")
	public String submitNuevaEspecie(@Valid Especie especie, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("familias", familiaService.findAll());
			return "admin/form-especie";
		} else {
			especieService.save(especie);
			return "redirect:/admin/especie/";

		}

	}

	@GetMapping("/editar/{id}")
	public String editarEspecie(@PathVariable("id") Long id, Model model) {

		Especie especie = especieService.findById(id);

		if (especie != null) {
			model.addAttribute("especie", especie);
			model.addAttribute("familias", familiaService.findAll());
			return "admin/form-especie";
		} else {
			return "redirect:/admin/especie/";
		}

	}

	@GetMapping("/borrar/{id}")
	public String borrarEspecie(@PathVariable("id") Long id, Model model) {

		Especie especie = especieService.findById(id);

		if (especie != null) {
			especieService.delete(especie);
		}

		return "redirect:/admin/especie/";

	}
}
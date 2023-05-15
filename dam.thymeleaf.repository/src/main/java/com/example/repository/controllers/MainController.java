package com.example.repository.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.repository.model.Especie;
import com.example.repository.repositories.EspecieRepository;
import com.example.repository.services.EspecieService;
import com.example.repository.services.FamiliaService;

@Controller
public class MainController {

	@Autowired
	private FamiliaService familiaService;

	@Autowired
	private EspecieService especieService;

	@GetMapping("/")
	public String index(@RequestParam(name="idFamilia", required=false) Long idFamilia, Model model) {
		List<Especie> especies;

		if(idFamilia==null) {
			especies = especieService.obtenerEspeciesAleatorios(EspecieRepository.ESPECIES_ALEATORIOS);
		}else {
			especies = especieService.findAllByFamilia(idFamilia);
		}

		model.addAttribute("familias", familiaService.findAll());

		model.addAttribute("especies", especies);

		return "index";
	}
	
	@GetMapping("/especie/{id}")
	public String showDetails(@PathVariable("id") Long id, Model model) {
		Especie especie = especieService.findById(id);
		if(especie!=null) {
			model.addAttribute(especie);
			return "detail";
		}
		return "redirect:/";
	}

}

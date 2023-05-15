package com.example.repository.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repository.model.Especie;
import com.example.repository.model.Familia;
import com.example.repository.repositories.EspecieRepository;

@Service
public class EspecieService {
	@Autowired
	private EspecieRepository especieRepository;
	
	public List<Especie> findAll() {
		return especieRepository.findAll();
	}
	
	public List<Especie> findAllByFamilia(Familia familia) {
		return especieRepository.findByFamilia(familia);
	}
	
	public List<Especie> findAllByFamilia(Long familiaId) {
		return especieRepository.findByFamiliaId(familiaId);
	}
	
	public Especie findById(Long id) {
		return especieRepository.findById(id).orElse(null);
	}
	
	public Especie save(Especie especie) {
		return especieRepository.save(especie);
	}
	
	public Especie delete(Especie especie) {
		Especie result = findById(especie.getId());
		especieRepository.delete(result);
		return result;
	}
	
	public int numeroEspeciesFamilia(Familia familia) {
		return especieRepository.findNumEspeciesByFamilia(familia);
	}
	
	
	/*
	 * Este método sirve para obtener un número de productos aleatorios.
	 * Lo realizamos en Java para abstraernos mejor de la base de datos
	 * concreta que vamos a usar.
	 * Algunos SGBDR nos permitirían usar la función RANDOM, y podríamos
	 * hacer esta consulta de forma nativa.
	 */
	public List<Especie> obtenerEspeciesAleatorios(int numero) {
		// Obtenemos los ids de todos los productos
		List<Long> listaIds = especieRepository.obtenerIds();
		// Los desordenamos 
		Collections.shuffle(listaIds);
		// Nos quedamos con los N primeros, con N = numero.
		listaIds = listaIds.stream().limit(numero).collect(Collectors.toList());
		// Buscamos los productos con esos IDs y devolvemos la lista
		return especieRepository.findAllById(listaIds);

	}
}

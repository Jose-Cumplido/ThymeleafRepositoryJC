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

	/**
	 * Metodo para obtener todas las especies
	 * @return todas las especies
	 */
	public List<Especie> findAll() {
		return especieRepository.findAll();
	}
	
	/**
	 * Metodo para ontener las especies que coincidan con la busqueda
	 * @param clave cadena introducida en el buscador
	 * @return lista con las coincidencias del buscador
	 */
	public List<Especie> findAllBySeacrh(String clave) {
		List<Especie> especies;
		especies = especieRepository.findBySearch((clave));
		return especies;
	}

	/**
	 * Metodo para obtener todas las especies que pertenezcan a una familia
	 * @param familia por la que vamos a filtrar
	 * @return listado de especies que pertenezcan a la familia
	 */
	public List<Especie> findAllByFamilia(Familia familia) {
		return especieRepository.findByFamilia(familia);
	}
	/**
	 * Metodo igual que el anterior pero esta vez se especifica un id
	 * @param familiaId
	 * @return
	 */
	public List<Especie> findAllByFamilia(Long familiaId) {
		return especieRepository.findByFamiliaId(familiaId);
	}

	public Especie findById(Long id) {
		return especieRepository.findById(id).orElse(null);
	}
	
	/**
	 * 
	 * @param especie
	 * @return
	 */
	public Especie save(Especie especie) {
		return especieRepository.save(especie);
	}
	
	/**
	 * Metodo para eliminar una especie
	 * @param especie a eliminar
	 * @return 
	 */
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

package com.example.repository.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.model.Familia;
import com.example.repository.repositories.FamiliaRepository;

@Service
public class FamiliaService {
	@Autowired
	private FamiliaRepository repositorio;
	
	public List<Familia> findAll() {
		return repositorio.findAll();
	}	
	
	public List<Familia> findDestacadas() {
		return repositorio.findDestacadas();
	}
	
	public Familia save(Familia familia) {
		return repositorio.save(familia);
	}
	
	public Familia findById(Long id) {
		return repositorio.findById(id).orElse(null);
	}
	
	public Familia delete(Familia familia) {
		Familia result = findById(familia.getId());
		repositorio.delete(result);
		return result;
	}
}

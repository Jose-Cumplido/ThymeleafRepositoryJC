package com.example.repository.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.repository.model.Familia;

public interface FamiliaRepository extends JpaRepository<Familia, Long> {
	@Query("select c from Familia c where c.destacada = true")
	public List<Familia> findDestacadas();
}

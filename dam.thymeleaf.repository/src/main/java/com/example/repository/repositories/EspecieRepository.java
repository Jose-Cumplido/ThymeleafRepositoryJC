package com.example.repository.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.repository.model.Especie;
import com.example.repository.model.Familia;

public interface EspecieRepository extends JpaRepository<Especie, Long> {
	public final int ESPECIES_ALEATORIOS=8;
	public List<Especie> findByFamilia(Familia familia);

	@Query("select e.id from Especie e")
	public List<Long> obtenerIds();

	@Query("select e from Especie e where e.familia.id = ?1")
	public List<Especie> findByFamiliaId(Long familiaId);

	@Query("select count(e) from Especie e where e.familia = ?1")
	public int findNumEspeciesByFamilia(Familia familia);
}

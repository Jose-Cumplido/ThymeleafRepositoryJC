package com.example.repository.model;

import java.time.LocalDate;
import org.springframework.data.annotation.CreatedDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Ranking {
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@CreatedDate
	private LocalDate fecha;
	
	private int puntuacion;
	
	@ManyToOne
	private Especie especie;

	public Ranking() {
	}
	
	public Ranking(int puntuacion) {		
		this.puntuacion = puntuacion;
	}
	
	public Ranking(int puntuacion, Especie especie) {
		this.puntuacion = puntuacion;
		this.especie = especie;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}
}

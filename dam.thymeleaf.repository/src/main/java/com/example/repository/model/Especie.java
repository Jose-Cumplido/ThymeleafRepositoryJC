package com.example.repository.model;

import java.util.HashSet;
import java.util.Set;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Especie {

	@Id
	@GeneratedValue
	private Long id;
	private String nombre;

	@Lob 
	private String descripcion;

	private String imagen;

	@ManyToOne
	private Familia familia;

	@OneToMany(mappedBy="especie", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	private Set<Ranking> puntuaciones = new HashSet<Ranking>();

	public Especie() {}

	public Especie(String nombre, String descripcion, String imagen, Familia familia) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.familia = familia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	public Set<Ranking> getPuntuaciones() {
		return puntuaciones;
	}

	public void setPuntuaciones(Set<Ranking> puntuaciones) {
		this.puntuaciones = puntuaciones;
	};

	/**
	 * Métodos helper
	 */
	public void addPuntuacion(Ranking puntuacion) {
		this.puntuaciones.add(puntuacion);
		puntuacion.setEspecie(this);
	}


	public double getPuntuacionMedia() {
		if (this.puntuaciones.isEmpty())
			return 0;
		else 
			return this.puntuaciones.stream()
					.mapToInt(Ranking::getPuntuacion)
					.average()
					.getAsDouble();
	}

	public double getNumeroTotalPuntuaciones() {
		return this.puntuaciones.size();
	}


}

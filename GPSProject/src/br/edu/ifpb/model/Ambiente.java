package br.edu.ifpb.model;

import java.util.Date;

import android.location.Location;

public class Ambiente {
	private int id;
	private String nome;
	private Location location;
	private Date date;
	private Double raio;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getRaio() {
		return raio;
	}
	public void setRaio(Double raio) {
		this.raio = raio;
	}

	
}

package com.escotiel.cursomc.dto;

import java.io.Serializable;

import com.escotiel.cursomc.domain.Categoria;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer Id;
	private String nome;

	public CategoriaDTO() {}
	
	public CategoriaDTO(Categoria obj) {
		Id = obj.getId();
		nome = obj.getNome();
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}


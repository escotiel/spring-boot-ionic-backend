package com.escotiel.cursomc.domain;

import java.io.Serializable;

//serializable -> interface que diz que os objetos da classe possam ser gravados em arquivos e trafegar em rede (transforam em bytes)
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private Integer Id;
	private String nome;
	
	//contrutor padrão
	public Categoria() {	
	}

	public Categoria(Integer id, String nome) {
		super();
		Id = id;
		this.nome = nome;
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

	//hashCode e equals (comparar objetos pelo conteúdo e não pela posição de memória)
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}
	

	
	
	
}

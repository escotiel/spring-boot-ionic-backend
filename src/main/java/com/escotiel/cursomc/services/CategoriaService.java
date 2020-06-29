package com.escotiel.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escotiel.cursomc.domain.Categoria;
import com.escotiel.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	//chama repository
	@Autowired //dependencia automaticamente injetada pelo spring
	private CategoriaRepository repo;
	
	//operação buscar categoria por código
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}

}

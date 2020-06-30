package com.escotiel.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escotiel.cursomc.domain.Categoria;
import com.escotiel.cursomc.repositories.CategoriaRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	//chama repository
	@Autowired //dependencia automaticamente injetada pelo spring
	private CategoriaRepository repo;
	
	//operação buscar categoria por código
	public Categoria find(Integer id) throws ObjectNotFoundException {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

}

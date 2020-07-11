package com.escotiel.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.escotiel.cursomc.domain.Categoria;
import com.escotiel.cursomc.domain.Produto;
import com.escotiel.cursomc.repositories.CategoriaRepository;
import com.escotiel.cursomc.repositories.ProdutoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	//chama repository
	@Autowired //dependencia automaticamente injetada pelo spring
	private ProdutoRepository prodRepo;
	
	@Autowired
	private CategoriaRepository catRepo;
	
	//operação buscar categoria por código
	public Produto find(Integer id) throws ObjectNotFoundException {
		Optional<Produto> obj = prodRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	//busca paginada
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		List<Categoria> categorias = catRepo.findAllById(ids);
		return prodRepo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
}
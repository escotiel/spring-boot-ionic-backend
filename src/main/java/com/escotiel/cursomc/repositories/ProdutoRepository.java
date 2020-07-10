package com.escotiel.cursomc.repositories;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.escotiel.cursomc.domain.Categoria;
import com.escotiel.cursomc.domain.Produto;

@Repository
@Transactional
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

	
	//Código JPQL --> @Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	
	//pode-se usar o padrão de nomes do spring data pra criar automaticamente as consultas ao banco --> substitui a query acima
			Page<Produto> findDistinctByNomeContainingAndCategoriasIn(
			@Param("nome") String nome,
			@Param("categorias") List<Categoria> categorias,
			Pageable pageRequest);
			
}

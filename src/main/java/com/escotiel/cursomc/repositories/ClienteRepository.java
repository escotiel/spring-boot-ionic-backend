package com.escotiel.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.escotiel.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	//padrão de nomes do SpringData
	@Transactional(readOnly=true)
	Cliente findByEmail(String email); //só isso basta pro Spring criar o método automaticamente

}

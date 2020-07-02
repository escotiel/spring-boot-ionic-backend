package com.escotiel.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.escotiel.cursomc.domain.Categoria;
import com.escotiel.cursomc.repositories.CategoriaRepository;
import com.escotiel.cursomc.services.exceptions.DataIntegrityException;

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
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);//garante que está inserindo um objeto novo (nulo)//se o id é nulo insere
		return repo.save(obj); //save serve pra inserir e atualizar
	}

	public Categoria update(Categoria obj) throws ObjectNotFoundException {
		
		//verificar se o objeto existe usando o método find
		find(obj.getId());		
		return repo.save(obj);//como o id não é nulo (existe no bd) ele atualiza
	}

	public void delete(Integer id) throws ObjectNotFoundException {		
		//verificar se o objeto existe usando o método find
		find(id);
		//cria uma exceção personalizada para caso se tente apagar algum objeto que esteja relacionado com outro (integridade referencial)
		//cria no pacote exceptions
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}

}

	public List<Categoria> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
}

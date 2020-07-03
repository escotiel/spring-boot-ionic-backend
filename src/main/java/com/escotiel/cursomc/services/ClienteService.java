package com.escotiel.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.escotiel.cursomc.domain.Cliente;
import com.escotiel.cursomc.dto.ClienteDTO;
import com.escotiel.cursomc.repositories.ClienteRepository;
import com.escotiel.cursomc.services.exceptions.DataIntegrityException;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService {

	// chama repository
	@Autowired // dependencia automaticamente injetada pelo spring
	private ClienteRepository repo;

	// operação buscar cliente por código
	public Cliente find(Integer id) throws ObjectNotFoundException {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public Cliente update(Cliente obj) throws ObjectNotFoundException {

		// pega o cliente do BD
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);// como o id não é nulo (existe no bd) ele atualiza
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	public void delete(Integer id) throws ObjectNotFoundException {
		// verificar se o objeto existe usando o método find
		find(id);
		// cria uma exceção personalizada para caso se tente apagar algum objeto que
		// esteja relacionado com outro (integridade referencial)
		// cria no pacote exceptions
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há entidades relacionadas");
		}

	}

	// listar todas as categorias
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	// listar categorias com paginação
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}

}

package com.escotiel.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.escotiel.cursomc.domain.Cliente;
import com.escotiel.cursomc.dto.ClienteDTO;
import com.escotiel.cursomc.dto.ClienteNewDTO;
import com.escotiel.cursomc.services.ClienteService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	//requisição básica REST
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) throws ObjectNotFoundException {
		
		Cliente obj = service.find(id);
		
		//Cliente cat1 = new Cliente(1,"Informática");
		//Cliente cat2 = new Cliente(2,"Escritório");
		
		return ResponseEntity.ok(obj);
		
		/*lista de categorias
		List<Cliente> lista = new ArrayList<>();
		lista.add(cat1);
		lista.add(cat2);
		
		return lista; //retorna a lista no formato JSON*/
	}
	
	//método de inserção
		@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto){ //converte json para java
			Cliente obj = service.fromDTO(objDto);
			obj = service.insert(obj);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(obj.getId()).toUri();
			
			return ResponseEntity.created(uri).build();
		}
		
	
	//método de atualização
		@RequestMapping(value="/{id}", method = RequestMethod.PUT)
		public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) throws ObjectNotFoundException{
			Cliente obj = service.fromDTO(objDto);
			obj.setId(id);
			obj = service.update(obj);
			return ResponseEntity.noContent().build();
		}
	 
		
		//método de deleção
		@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
		public ResponseEntity<Void> delete(@PathVariable Integer id) throws ObjectNotFoundException {
		
			service.delete(id);
			return ResponseEntity.noContent().build();
		}
		
		//listar todas categorias
		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<List<ClienteDTO>> findAll() {
			
			List<Cliente> list = service.findAll();
			List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
			
			return ResponseEntity.ok().body(listDto);
		}
		
		//listar categorias paginadas
		@RequestMapping(value="/page", method=RequestMethod.GET)
		public ResponseEntity<Page<ClienteDTO>> findPage(
				@RequestParam(value="page", defaultValue = "0") Integer page, 
				@RequestParam(value="linesPerPage", defaultValue = "24")Integer linesPerPage, //usa o 24
				@RequestParam(value="orderBy", defaultValue = "nome")String direction, 
				@RequestParam(value="direction", defaultValue = "ASC")String orderBy) {
			
			Page<Cliente> list = service.findPage(page, linesPerPage, direction, orderBy);
			Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
			
			return ResponseEntity.ok().body(listDto);
		}

}

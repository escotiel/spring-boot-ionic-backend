package com.escotiel.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.escotiel.cursomc.domain.Pedido;
import com.escotiel.cursomc.services.PedidoService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	
	//requisição básica REST
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) throws ObjectNotFoundException {
		
		Pedido obj = service.find(id);
		
		//Pedido cat1 = new Pedido(1,"Informática");
		//Pedido cat2 = new Pedido(2,"Escritório");
		
		return ResponseEntity.ok(obj);
		
		/*lista de categorias
		List<Pedido> lista = new ArrayList<>();
		lista.add(cat1);
		lista.add(cat2);
		
		return lista; //retorna a lista no formato JSON*/
	}

}

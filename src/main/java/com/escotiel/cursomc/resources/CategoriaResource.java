package com.escotiel.cursomc.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	//requisição básica REST
	@RequestMapping(method=RequestMethod.GET)
	public String listar() {
		return "REST OK";
	}

}

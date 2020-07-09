package com.escotiel.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.escotiel.cursomc.domain.Cliente;
import com.escotiel.cursomc.domain.enums.TipoCliente;
import com.escotiel.cursomc.dto.ClienteNewDTO;
import com.escotiel.cursomc.repositories.ClienteRepository;
import com.escotiel.cursomc.resources.exceptions.FieldMessage;
import com.escotiel.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository cliRepo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		// inclua os testes aqui, inserindo erros na lista
		
		//valida CPF
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj","CPF Inválido!"));
		}
		
		//valida CNPJ
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj","CNPJ Inválido!"));
		}
		
		//valida se o email é unico
		Cliente aux = cliRepo.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email", "E-mail já existente!"));
		}
		
		//percorre a lista de fieldMessage adicionando cada um dos erros encontrados na lista do framework
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}

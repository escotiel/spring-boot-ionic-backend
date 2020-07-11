package com.escotiel.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.escotiel.cursomc.domain.PagamentoComBoleto;

@Service
public class boletoService {

	//no mundo real haveria uma chamada pra um web service que geraria um boleto
	public void preencherPagtoComBoleto(PagamentoComBoleto pagto, Date instante) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instante);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}

	
}

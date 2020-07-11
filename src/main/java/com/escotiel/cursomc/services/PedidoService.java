package com.escotiel.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escotiel.cursomc.domain.ItemPedido;
import com.escotiel.cursomc.domain.PagamentoComBoleto;
import com.escotiel.cursomc.domain.Pedido;
import com.escotiel.cursomc.domain.enums.EstadoPagamento;
import com.escotiel.cursomc.repositories.ItemPedidoRepository;
import com.escotiel.cursomc.repositories.PagamentoRepository;
import com.escotiel.cursomc.repositories.PedidoRepository;
import com.escotiel.cursomc.repositories.ProdutoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class PedidoService {
	
	//chama repository
	@Autowired //dependencia automaticamente injetada pelo spring
	private PedidoRepository repo;
	
	@Autowired
	private boletoService boletoService;
	
	@Autowired
	private PagamentoRepository pgtoRepo;
	
	@Autowired
	private ProdutoRepository prodRepo;
	
	@Autowired
	private ItemPedidoRepository itempedRepo;
	
	//operação buscar categoria por código
	public Pedido find(Integer id) throws ObjectNotFoundException {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	//inserindo um pedido
	public Pedido insert(Pedido obj) {
		obj.setId(null); //garante que é um novo produto
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		//calcular data vencimento boleto
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagtoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pgtoRepo.save(obj.getPagamento());
		
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(prodRepo.getOne(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itempedRepo.saveAll(obj.getItens());
		return obj;
	}

}

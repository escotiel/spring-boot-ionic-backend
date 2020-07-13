package com.escotiel.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escotiel.cursomc.domain.Categoria;
import com.escotiel.cursomc.domain.Cidade;
import com.escotiel.cursomc.domain.Cliente;
import com.escotiel.cursomc.domain.Endereco;
import com.escotiel.cursomc.domain.Estado;
import com.escotiel.cursomc.domain.ItemPedido;
import com.escotiel.cursomc.domain.Pagamento;
import com.escotiel.cursomc.domain.PagamentoComBoleto;
import com.escotiel.cursomc.domain.PagamentoComCartao;
import com.escotiel.cursomc.domain.Pedido;
import com.escotiel.cursomc.domain.Produto;
import com.escotiel.cursomc.domain.enums.EstadoPagamento;
import com.escotiel.cursomc.domain.enums.TipoCliente;
import com.escotiel.cursomc.repositories.CategoriaRepository;
import com.escotiel.cursomc.repositories.CidadeRepository;
import com.escotiel.cursomc.repositories.ClienteRepository;
import com.escotiel.cursomc.repositories.EnderecoRepository;
import com.escotiel.cursomc.repositories.EstadoRepository;
import com.escotiel.cursomc.repositories.ItemPedidoRepository;
import com.escotiel.cursomc.repositories.PagamentoRepository;
import com.escotiel.cursomc.repositories.PedidoRepository;
import com.escotiel.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Autowired
	private EstadoRepository estadoRepo;
	@Autowired
	private CidadeRepository cidadeRepo;
	
	@Autowired
	private ClienteRepository clienteRepo;
	@Autowired
	private EnderecoRepository enderecoRepo;
	
	@Autowired
	private PedidoRepository pedidoRepo;
	@Autowired
	private PagamentoRepository pagamentoRepo;
	
	@Autowired
	private ItemPedidoRepository ItemPedidoRepo;
	
	public void instantiateTestDatabase() throws ParseException {
		
		//cadastro das categorias
				Categoria cat1 = new Categoria(null, "Informática");
				Categoria cat2 = new Categoria(null, "Escritório");		
				//testar paginação
				Categoria cat3 = new Categoria(null, "Cama, mesa e banho");
				Categoria cat4 = new Categoria(null, "Eletrônicos");
				Categoria cat5 = new Categoria(null, "Jardinagem");
				Categoria cat6 = new Categoria(null, "Decoração");
				Categoria cat7 = new Categoria(null, "Perfumaria");

				//cadastro dos produtos
				Produto p1 = new Produto(null, "Computador", 2000.00);
				Produto p2 = new Produto(null, "Impressora", 800.00);
				Produto p3 = new Produto(null, "Mouse", 80.00);
				Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
				Produto p5 = new Produto(null, "Toalha", 50.00);
				Produto p6 = new Produto(null, "Colcha", 200.00);
				Produto p7 = new Produto(null, "TV True Color", 1200.00);
				Produto p8 = new Produto(null, "Roçadeira", 800.00);
				Produto p9 = new Produto(null, "Abajour", 100.00);
				Produto p10 = new Produto(null, "Pendente", 180.00);
				Produto p11 = new Produto(null, "Shampoo", 90.00);

				// produtos associados com categorias
				cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
				cat2.getProdutos().addAll(Arrays.asList(p2,p4));
				cat3.getProdutos().addAll(Arrays.asList(p5,p6));
				cat4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
				cat5.getProdutos().addAll(Arrays.asList(p8));
				cat6.getProdutos().addAll(Arrays.asList(p9,p10));
				cat7.getProdutos().addAll(Arrays.asList(p11));

				// categorias associadas com os produtos
				p1.getCategorias().addAll(Arrays.asList(cat1,cat4));
				p2.getCategorias().addAll(Arrays.asList(cat1,cat2,cat4));
				p3.getCategorias().addAll(Arrays.asList(cat1,cat4));
				p4.getCategorias().addAll(Arrays.asList(cat2));
				p5.getCategorias().addAll(Arrays.asList(cat3));
				p6.getCategorias().addAll(Arrays.asList(cat3));
				p7.getCategorias().addAll(Arrays.asList(cat4));
				p8.getCategorias().addAll(Arrays.asList(cat5));
				p9.getCategorias().addAll(Arrays.asList(cat6));
				p10.getCategorias().addAll(Arrays.asList(cat6));
				p11.getCategorias().addAll(Arrays.asList(cat7));
				
				// salvar categorias no bd
				categoriaRepo.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
				// salvar produtos no bd
				produtoRepo.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));

				Estado est1 = new Estado(null, "Minas Gerais");
				Estado est2 = new Estado(null, "São Paulo");

				Cidade c1 = new Cidade(null, "Uberlândia", est1);
				Cidade c2 = new Cidade(null, "Cubatão", est2);
				Cidade c3 = new Cidade(null, "Campinas", est2);

				// cidade 1 relacionada com o estado 1
				est1.getCidades().addAll(Arrays.asList(c1));

				// cidades 2 e 3 relacionadas com o estado2
				est2.getCidades().addAll(Arrays.asList(c2, c3));

				estadoRepo.saveAll(Arrays.asList(est1,est2));
				cidadeRepo.saveAll(Arrays.asList(c1,c2,c3));
				
				Cliente cli1 = new Cliente(null,"Maria","maria@gmail.com","2343243243",TipoCliente.PESSOAFISICA);
				cli1.getTelefones().addAll(Arrays.asList("3432443242", "324244324324"));
				
				Endereco e1 = new Endereco(null, "Rua Flores", "200", "ap 74", "Jardim", "13212121", cli1, c1);	
				Endereco e2 = new Endereco(null, "Av. Matos", "2120", "ap 544", "Parque", "4354234", cli1, c2);	
				
				cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
				
				clienteRepo.saveAll(Arrays.asList(cli1));
				enderecoRepo.saveAll(Arrays.asList(e1,e2));
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32") , cli1, e1);
				Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 10:00") , cli1, e2);
				
				Pagamento pg1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
				ped1.setPagamento(pg1);
				
				Pagamento pg2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),null);
				ped2.setPagamento(pg2);
				
				cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
				
				pedidoRepo.saveAll(Arrays.asList(ped1, ped2));
				pagamentoRepo.saveAll(Arrays.asList(pg1,pg2));	
				
				
				ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
				ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
				ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
				
				ped1.getItens().addAll(Arrays.asList(ip1,ip2));
				ped2.getItens().addAll(Arrays.asList(ip3));
				
				p1.getItens().addAll(Arrays.asList(ip1));
				p2.getItens().addAll(Arrays.asList(ip3));
				p1.getItens().addAll(Arrays.asList(ip2));
				
				ItemPedidoRepo.saveAll(Arrays.asList(ip1,ip2,ip3));
	}

}

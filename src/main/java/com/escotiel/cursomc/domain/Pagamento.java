package com.escotiel.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.escotiel.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
//super classe que irá ativar a heranca
//muitos atributos nas subclasses ('tabelão-.melhor performace mas gera dados nulos)
//poucos atributos nas subclasses (uma tabela para cada classe - igual no modelo diagrama)
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Pagamento implements Serializable { //abstract garante que esta classe não seja instanciada, mas sim suas subclasses
	private static final long serialVersionUID = 1L;
	
	@Id 
	//id do pagamento tem que ser o mesmo do pedido
	private Integer id;
	private Integer estado;
	
	@JsonBackReference
	@OneToOne
	@JoinColumn(name="pedido_id")
	@MapsId //id do pagamento tem que ser o mesmo do pedido
	private Pedido pedido;
	
	public Pagamento() {}

	public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
		super();
		this.id = id;
		this.estado = estado.getCod();
		this.pedido = pedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado);
	}

	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	

}
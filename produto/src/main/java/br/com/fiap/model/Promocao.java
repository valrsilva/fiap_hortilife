package br.com.fiap.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Promocao {
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double valorAntigo;
    private double valorNovo;
    private Date dataInicio;
    private Date dataFim;
    private boolean ativo;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getValorAntigo() {
		return valorAntigo;
	}
	public void setValorAntigo(double valorAntigo) {
		this.valorAntigo = valorAntigo;
	}
	public double getValorNovo() {
		return valorNovo;
	}
	public void setValorNovo(double valorNovo) {
		this.valorNovo = valorNovo;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
}

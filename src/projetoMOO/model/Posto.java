package projetoMOO.model;

import projetoMOO.dao.Campo;

public class Posto {

	@Campo("uniid")
	private Integer codigo;
	@Campo("unilotacao")
	private Integer capacidade;
	@Campo("unisigla")
	private String sigla;	
	// Há informação de município, mas os campos a partir de certo ponto no arquivo ficam bagunçados
	//private Cidade cidade;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Integer getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
}

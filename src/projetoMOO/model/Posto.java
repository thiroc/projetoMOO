package projetoMOO.model;

import projetoMOO.dao.Campo;

public class Posto {

	@Campo("uniid")
	private Integer codigo;
	@Campo("unilotacao")
	private Integer capacidade;
	@Campo("unisigla")
	private SiglaPosto sigla;	
	// Há informação de município, mabuscarCidades os campos a partir de certo ponto no arquivo ficam bagunçados
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
	public SiglaPosto getSigla() {
		return sigla;
	}
	public void setSigla(SiglaPosto sigla) {
		this.sigla = sigla;
	}
	
}

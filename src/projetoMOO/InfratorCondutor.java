package projetoMOO;

import projetoMOO.anotacoes.Campo;

public class InfratorCondutor {

	//cod_condutorinfrator;num_auto;dat_apresentdoc;dat_entradasistema
	
	@Campo("cod_condutorinfrator")
	public Integer codCondutorInfrator;
	
	@Campo("num_auto")
	private String numAutomovel;
	
	@Campo("dat_apresentdoc")
	private String dataApresentacaoDocumento;
	
	@Campo("dat_entradasistema")
	private String dataEntradaSistema;

	public Integer getCodCondutorInfrator() {
		return codCondutorInfrator;
	}

	public void setCodCondutorInfrator(Integer codCondutorInfrator) {
		this.codCondutorInfrator = codCondutorInfrator;
	}

	public String getNumAutomovel() {
		return numAutomovel;
	}

	public void setNumAutomovel(String numAutomovel) {
		this.numAutomovel = numAutomovel;
	}

	public String getDataApresentacaoDocumento() {
		return dataApresentacaoDocumento;
	}

	public void setDataApresentacaoDocumento(String dataApresentacaoDocumento) {
		this.dataApresentacaoDocumento = dataApresentacaoDocumento;
	}

	public String getDataEntradaSistema() {
		return dataEntradaSistema;
	}

	public void setDataEntradaSistema(String dataEntradaSistema) {
		this.dataEntradaSistema = dataEntradaSistema;
	}
	
}

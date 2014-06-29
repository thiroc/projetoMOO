package projetoMOO.model;

import java.util.Date;

import projetoMOO.dao.Campo;

public class Ocorrencia {
    
    final static int       NUM_TIPOS = 9;
    
    @Campo("ocomunicipio")
    private Cidade         cidade;
    @Campo("ocodataocorrencia")
    private Date           dataOcorrencia;
    @Campo("ocotipo")
    private TipoOcorrencia tipoOcorrencia;
    
    public Cidade getCidade() {
        return cidade;
    }
    
    public final Date getDataOcorrencia() {
        return dataOcorrencia;
    }
    
    /**
     * @return o tipoOcorrencia
     */
    public TipoOcorrencia getTipoOcorrencia() {
        return tipoOcorrencia;
    }
    
    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    
    public final void setDataOcorrencia(Date dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }
    
    /**
     * @param tipoOcorrencia
     *            o tipoOcorrencia a ser setado
     */
    public void setTipoOcorrencia(TipoOcorrencia tipoOcorrencia) {
        this.tipoOcorrencia = tipoOcorrencia;
    }
    
}

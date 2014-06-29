package projetoMOO.model;

import java.util.Date;

import projetoMOO.dao.Campo;

public class Ocorrencia {
    
    final static int       NUM_TIPOS = 9;
    
    @Campo("dataOcorrencia")
    private Date           dataOcorrencia;
    @Campo("estado")
    private String         estado;
    @Campo("tipo")
    private TipoOcorrencia tipo;
    
    public final Date getDataOcorrencia() {
        return dataOcorrencia;
    }
    
    public final String getEstado() {
        return estado;
    }
    
    public final TipoOcorrencia getTipo() {
        return tipo;
    }
    
    public final void setDataOcorrencia(Date dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }
    
    public final void setEstado(String estado) {
        this.estado = estado;
    }
    
    public final void setTipo(TipoOcorrencia tipo) {
        this.tipo = tipo;
    }
    
}

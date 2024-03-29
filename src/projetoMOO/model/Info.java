/**
 * 
 */
package projetoMOO.model;

import java.util.Map;

/**
 * @author Guilherme
 * 
 */
public class Info {
    
    private String                       estado;
    private Integer                      nOcorrencias;
    private Integer                      nPostos;
    private Map<TipoOcorrencia, Integer> tipoOcorrencias;
    
    public final String getEstado() {
        return estado;
    }
    
    public final Integer getnOcorrencias() {
        return nOcorrencias;
    }
    
    public Integer getnPostos() {
        return nPostos;
    }
    
    public final Map<TipoOcorrencia, Integer> getTipoOcorrencias() {
        return tipoOcorrencias;
    }
    
    public final void setEstado(String estado) {
        this.estado = estado;
    }
    
    public final void setnOcorrencias(Integer nOcorrencias) {
        this.nOcorrencias = nOcorrencias;
    }
    
    public void setnPostos(Integer nPostos) {
        this.nPostos = nPostos;
    }
    
    public final void setTipoOcorrencias(Map<TipoOcorrencia, Integer> tipoOcorrencias) {
        this.tipoOcorrencias = tipoOcorrencias;
    }
    
}

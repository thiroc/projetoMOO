/**
 * 
 */
package projetoMOO.model;

/**
 * @author Guilherme
 * 
 */
public class TipoOcorrencia {
    public static final TipoOcorrencia   ACIDENTE           = new TipoOcorrencia("Acidentes Rodoviarios", 1);
    
    public static final TipoOcorrencia   APREENSAO_CARGA    = new TipoOcorrencia("Apreensao de Carga", 7);
    public static final TipoOcorrencia   APREENSAO_CNH      = new TipoOcorrencia("Apreensao de CNH", 4);
    
    public static final TipoOcorrencia   APREENSAO_DOC      = new TipoOcorrencia("Apreensao de Documento", 6);
    public static final TipoOcorrencia   DETENCAO_AUXILIO   = new TipoOcorrencia("Pessoa Detencao/auxilio ", 3);
    public static final TipoOcorrencia   INTERDICAO_RODOVIA = new TipoOcorrencia("Intendicao Rodoviaria", 8);
    public static final TipoOcorrencia   OCORRENCIA_D       = new TipoOcorrencia("Ocorrencia D", 9);
    public static final TipoOcorrencia   RETENCAO           = new TipoOcorrencia(
                                                                    "Retencao, Apreensao e recuperacao de Veiculos", 2);
    public final static TipoOcorrencia[] values             = new TipoOcorrencia[] { ACIDENTE, APREENSAO_CARGA,
            APREENSAO_CNH, APREENSAO_DOC, DETENCAO_AUXILIO, INTERDICAO_RODOVIA, OCORRENCIA_D, RETENCAO };
    
    public static TipoOcorrencia buscarTipoPorNumero(int i) {
        for (int j = 0; j < values.length; j++) {
            if (i == values[j].id) {
                return values[j];
            }
        }
        return null;
    }
    
    private final int    id;
    private final String tipo;
    
    private TipoOcorrencia(String tipo, int id) {
        this.tipo = tipo;
        this.id = id;
    }
    
    /**
     * @return o id
     */
    public int getId() {
        return id;
    }
    
    /**
     * @return o tipo
     */
    public String getTipo() {
        return tipo;
    }
    
    /* (non-Javadoc)
     * 
     * @see java.lang.Object#toString() */
    @Override
    public String toString() {
        return tipo;
    }
}

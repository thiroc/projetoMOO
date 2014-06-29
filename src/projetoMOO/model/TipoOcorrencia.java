/**
 * 
 */
package projetoMOO.model;

/**
 * @author Guilherme
 * 
 */
public enum TipoOcorrencia {
    ACIDENTE("Acidentes Rodoviários", 1), APREENSAO_CARGA("Aprenão de Carga", 7), APREENSAO_CNH("Apreensão de CNH", 4), APREENSAO_DOC(
            "Apreensão de Documento", 6), DETENCAO_AUXILIO("Pessoa Detenção/auxílio ", 3), INTERDICAO_RODOVIA(
            "Intendição Rodoviária", 8), OCORRENCIA_D("Ocorrência D", 9), RETENCAO(
            "Retenção, Apreensão e recuperação de Veículos", 2);
    
    public static TipoOcorrencia buscarTipoPorNumero(int i) {
        TipoOcorrencia[] values = values();
        for (int j = 0; j < values.length; j++) {
            if (i == values[i].id) {
                return values[i];
            }
        }
        return null;
    }
    
    private int          id;
    
    private final String tipo;
    
    private TipoOcorrencia(String tipo, int id) {
        this.tipo = tipo;
        this.id = id;
    }
    
    /**
     * @return o tipo
     */
    public String getTipo() {
        return tipo;
    }
}

package projetoMOO;

public class EstadoUF {
    
    final static int    NUM_TIPOS     = 9;
    final static String tipoDocumento = "unidadefederativa";
    private String      abreviatura;
    private String      id;
    private String      nome;
    private int         numOcorrencias;
    
    private int[]       numOcorrenciasTipo;
    private int         numUnidadesPrf;
    
    public EstadoUF() {
        this.id = null;
        this.nome = null;
        this.abreviatura = null;
        this.numUnidadesPrf = 0;
        this.numOcorrencias = 0;
        this.numOcorrenciasTipo = new int[NUM_TIPOS];
        
        for (int i = 0; i < NUM_TIPOS; ++i) {
            this.numOcorrenciasTipo[i] = 0;
        }
    }
    
    public EstadoUF(String id, String nome, String abreviatura, int numUnidadesPrf, int numOcorrencias,
            int numOcorrenciasTipo[]) {
        this.id = id;
        this.nome = nome;
        this.abreviatura = abreviatura;
        this.numUnidadesPrf = numUnidadesPrf;
        this.numOcorrencias = numOcorrencias;
        this.numOcorrenciasTipo = new int[NUM_TIPOS];
        
    }
    
    public String getAbreviatura() {
        return abreviatura;
    }
    
    public String getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public int getNumOcorrencias() {
        return numOcorrencias;
    }
    
    public int[] getNumOcorrenciasTipo() {
        return numOcorrenciasTipo;
    }
    
    public int getNumUnidadesPrf() {
        return numUnidadesPrf;
    }
    
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setNumOcorrencias(int numOcorrencias) {
        this.numOcorrencias = numOcorrencias;
    }
    
    public void setNumOcorrenciasTipo(int[] numOcorrenciasTipo) {
        this.numOcorrenciasTipo = numOcorrenciasTipo;
    }
    
    public void setNumUnidadesPrf(int numUnidadesPrf) {
        this.numUnidadesPrf = numUnidadesPrf;
    }
}

package projetoMOO;

public class ObjetoJSON {
	
	private String id;
	private String nome;
	private String abreviatura;
	private int numUnidadesPrf;
	private int numOcorrencias;
	private int[] numOcorrenciasTipo;
	
	final static String tipoDocumento = "unidadefederativa";
	final static int NUM_TIPOS = 8;
	
	public ObjetoJSON() {
		this.id = null;
		this.nome = null;
		this.abreviatura = null;
		this.numUnidadesPrf = 0;
		this.numOcorrencias = 0;
		this.numOcorrenciasTipo = new int[NUM_TIPOS];
		
		for (int i = 0 ; i < NUM_TIPOS ; ++i) {
			this.numOcorrenciasTipo[i] = 0;
		}
	}
	
	public ObjetoJSON(String id, String nome, String abreviatura, int numUnidadesPrf, int numOcorrencias, int numOcorrenciasTipo[]){
		this.id = id;
		this.nome = nome;
		this.abreviatura = abreviatura;
		this.numUnidadesPrf = numUnidadesPrf;
		this.numOcorrencias = numOcorrencias;
		this.numOcorrenciasTipo = new int[NUM_TIPOS];
		
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public int getNumUnidadesPrf() {
		return numUnidadesPrf;
	}

	public void setNumUnidadesPrf(int numUnidadesPrf) {
		this.numUnidadesPrf = numUnidadesPrf;
	}

	public int getNumOcorrencias() {
		return numOcorrencias;
	}

	public void setNumOcorrencias(int numOcorrencias) {
		this.numOcorrencias = numOcorrencias;
	}

	public int[] getNumOcorrenciasTipo() {
		return numOcorrenciasTipo;
	}

	public void setNumOcorrenciasTipo(int[] numOcorrenciasTipo) {
		this.numOcorrenciasTipo = numOcorrenciasTipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}

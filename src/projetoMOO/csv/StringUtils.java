package projetoMOO.csv;

public class StringUtils {

	//Alguns CSVs possui campos que est�o entre ""
	public static String tratarCampo(String campo) {
		return campo.replaceAll("\"", "").trim();
	}

	//Pega somente o tipo
	public static String getTipo(String tipo) {
		return tipo.substring(tipo.lastIndexOf(".")+1, tipo.length());
	}

}

package projetoMOO.model;

public class SiglaPosto {

	String codigo;
	String area;
	
	
	
	public SiglaPosto(String string, String string2) {
		this.codigo = string;
		this.area = string2;
	}

	public static SiglaPosto parse(String atributo) {
		String[] split = atributo.split("/");
		SiglaPosto sigla = new SiglaPosto(split[0],split[1]);
		return sigla;
	}

}

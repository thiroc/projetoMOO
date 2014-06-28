package projetoMOO.tradutorcsv;

public class Csv2Json {

	LeitorCsv leitorCsv;
	EscritorJson escritorJson;
	
	Csv2Json( ) {
		leitorCsv = new OcorrenciasLeitorCsv();
		escritorJson = new OcorrenciasEscritorJson();
	}
	
	void run() {
		escritorJson.escreverArquivos( leitorCsv.lerArquivos() );
	}
	
	
}

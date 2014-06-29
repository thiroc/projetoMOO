package projetoMOO.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe para a manipulação de arquivos. * 
 * @author Daniel Magalhães
 * */
public class Arquivo {

	private static final String SEPARADOR = ";";

	/**
	 * Lista todos os arquivos e subpastas de uma pasta.
	 * 
	 *  @param dirName	Caminho para pasta.
	 *  @return	Lista de File com todos os arquivos e pastas listadas.
	 * */
	public static List<File> listaArquivos(String dirName) {
		File dir = new File(dirName);
		File[] listaArquivos = dir.listFiles();
		return Arrays.asList(listaArquivos);
	}
	
	/**
	 * Retorna um BufferedReader para a leitura de um arquivo.
	 * 
	 *	@param arquivo	Arquivo de entrada.
	 *	@return Um BefferedReader instanciado pronto para a leitura.
	 * */	
	public static BufferedReader getReaderArquivo(File arquivo) throws FileNotFoundException {
		FileReader fr = null;
		BufferedReader br = null;		
		fr = new FileReader(arquivo);
		br = new BufferedReader(fr);		
		return br;
	}
	
	/**
	 * Retorna uma linha do arquivo no formato de String.
	 * 
	 *	@param arquivo	Arquivo de entrada.
	 *	@param br BufferedReader para a leitura.
	 *	@return A String que representa a leitura de uma linha do arquivo.
	 * */	
	public static String lerLinha(File arquivo, BufferedReader br) throws IOException {
		String conteudoLinha = br.readLine();
		return conteudoLinha != null ? conteudoLinha : null;
	}
	
	/**
	 * Retorna uma List<String> com os campos de uma linha separados pelo separador.
	 * 
	 *	@param linha	String que representa a linha.
	 *	@return Os campos separados em uma lista.
	 * */
	public static List<String> lerCampos(String linha) {
		List<String> campos = new ArrayList<>();
		for(String campo : linha.split(SEPARADOR)) {
			campos.add(tratarCampo(campo));
		}		
		return campos;
	}
	
	//Alguns CSVs possui campos que estão entre ""
	public static String tratarCampo(String campo) {
		return campo.replaceAll("\"", "").trim();
	}
}

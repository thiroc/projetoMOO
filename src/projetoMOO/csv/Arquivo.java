package projetoMOO.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Arquivo {

	private static final String SEPARADOR = ";";

	public static List<File> listaArquivos(String dirName) {
		File dir = new File(dirName);
		File[] listaArquivos = dir.listFiles();
		return Arrays.asList(listaArquivos);
	}
	
	public static BufferedReader getReaderArquivo(File arquivo) throws FileNotFoundException {
		FileReader fr = null;
		BufferedReader br = null;		
		fr = new FileReader(arquivo);
		br = new BufferedReader(fr);		
		return br;
	}
	
	public static String lerLinha(File arquivo, BufferedReader br) throws IOException {
		String conteudoLinha = br.readLine();
		return conteudoLinha != null ? conteudoLinha : null;
	}
	
	public static List<String> lerCampos(String linha) {
		List<String> campos = new ArrayList<>();
		for(String campo : linha.split(SEPARADOR)) {
			campos.add(campo);
		}		
		return campos;
	}
	
//	public List<Arquivo> lerArquivos(List<File> listaArquivos) {
//
//		List<Arquivo> resultado = new ArrayList<>();
//		
//		for(File f : listaArquivos) {
//			if(f.isFile()) {
//				Arquivo arq = null;
//
//				arq.setLinhas(lerArquivo(f));
//				resultado.add(arq);
//			}
//		}
//
//		return resultado;
//	}

}

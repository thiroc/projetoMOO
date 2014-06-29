package projetoMOO.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import projetoMOO.anotacoes.Campo;
import projetoMOO.dao.AbstractDAO;

public class ReadCSV {

	public void lerCSV(File arquivoCSV, Class<?> objetoClasse) {
		String linha = null;
		List<String> campos = null;
		List<String> linhaValores = null;
		Map<String,String> atributoValorAnotacao = null;
		Map<String,String> atributoValor = null;
		
		AbstractDAO<Object> dao = new AbstractDAO<Object>();
		
		try {
			
			BufferedReader br = Arquivo.getReaderArquivo(arquivoCSV);
			
			if((linha = Arquivo.lerLinha(arquivoCSV, br)) != null) {
				campos = Arquivo.lerCampos(linha);
			}
			
			if(!campos.isEmpty()) {
				while((linha = Arquivo.lerLinha(arquivoCSV, br)) != null) {
					linhaValores = Arquivo.lerCampos(linha);
					atributoValorAnotacao = recuperarAtributoValorAnotacao(objetoClasse);
					atributoValor = recuperarAtributoValorObjeto(campos, atributoValorAnotacao, linhaValores);
					Object entidade = instanciarObjeto(objetoClasse, atributoValor);
					
					//DAO generico Object
					dao.salvar(entidade);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public Map<String,String> recuperarAtributoValorAnotacao(Class<?> objeto) throws ClassNotFoundException {
		Map<String,String> atributoAnotacao = new HashMap<>();
		for (Field f: objeto.getDeclaredFields()) {
			Campo anotacao = f.getAnnotation(Campo.class);
			if (anotacao != null)
				atributoAnotacao.put(anotacao.value(), f.getName());
		}
		return atributoAnotacao;
	}
	
	public Map<String,String> recuperarAtributoValorObjeto(List<String> campos, Map<String, String> atributoValorAnotacao, List<String> linhaValores) {
		Map<String,String> atributoValorObjeto = new HashMap<>();
		int i = 0;
		for (String campo : campos) {
			atributoValorObjeto.put(atributoValorAnotacao.get(campo), linhaValores.get(i));
			i++;
		}
		return atributoValorObjeto;
	}
	
	public Object instanciarObjeto(Class<?> objeto, Map<String, String> atributoValor) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object objRetorno = null;
		Class<?> c = Class.forName(objeto.getName());
		objRetorno = c.newInstance();
		return preencherValores(objRetorno, atributoValor);
	}
	
	private Object preencherValores(Object obj, Map<String, String> atributoValores) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, InstantiationException {
		String atributoValor, nomeMetodo;
		Class<?> c = obj.getClass();
		Field[] atributos = c.getDeclaredFields();
		Method[] metodos = c.getDeclaredMethods();
		for(Field f : Arrays.asList(atributos)) {
			if(f.isAnnotationPresent(Campo.class)) {
				atributoValor = atributoValores.get(f.getName());
				nomeMetodo = f.getName();
				nomeMetodo = "set" + nomeMetodo.substring(0, 1).toUpperCase() + nomeMetodo.substring(1);
				for(Method m : Arrays.asList(metodos)) {
					if(m.getName().contains(nomeMetodo)) {
						m.invoke(obj, converterValor(f.getType(), atributoValor));
						break;
					}
				}
			}
		}
		return obj;
	}
	
	private Object converterValor(Class<?> classeCampo, String atributo) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String tipo = classeCampo.getName().replace("java.lang.", "");
		switch(tipo) {
		case "String":
			return atributo; 
		case "Integer":
			return new Integer(atributo);
		case "Long":
			return new Long(atributo);
		case "Float":
			return new Float(atributo);
		case "Double":
			return new Double(atributo);
		default:
			return null;
		}
	}
}

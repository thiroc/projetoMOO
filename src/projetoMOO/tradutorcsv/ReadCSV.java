package projetoMOO.tradutorcsv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import projetoMOO.dao.AbstractDAO;
import projetoMOO.dao.Campo;
import projetoMOO.model.TipoOcorrencia;

/**
 * Classe que faz a leitura do CSV.
 * 
 * @author Daniel Magalh�es
 * */

public class ReadCSV {
    
    /**
     * Retorna um atributo inicializado com o tipo
     * correto. OBS: Somente os tipos declarados
     * abaixo est�o sendo suportados, acrescentar
     * mais se precisar.
     * 
     * @param classeCampo
     *            Classe do objeto sendo setado.
     * @param atributo
     *            Atributo no formato String.
     * @return Objeto preenchido.
     * */
    private Object converterValor(Class<?> classeCampo, String atributo) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        String tipo = StringUtils.getTipo(classeCampo.getName());
        if (atributo.isEmpty()) {
            atributo = "0";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        
        switch (tipo) {
            case "String":
                return atributo;
            case "int":
            case "Integer":
                return new Integer(atributo);
            case "long":
            case "Long":
                return new Long(atributo);
            case "float":
            case "Float":
                return new Float(atributo);
            case "double":
            case "Double":
                return new Double(atributo);
            case "Date":
                try {
                    return format.parse(atributo);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            case "TipoOcorrencia":
                return TipoOcorrencia.buscarTipoPorNumero(Integer.valueOf(atributo));
            default:
                return null;
        }
    }
    
    /**
     * Instancia um objeto da classe desejada, e
     * preenche os atributos com os valores
     * fornecidos.
     * 
     * @param atributoValor
     *            Map com o valor dos atributos.
     * @return O objeto desejado com os atributos
     *         preenchidos.
     * */
    private Object instanciarObjeto(Class<?> classeObjeto, Map<String, String> atributoValor)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        Object objRetorno = null;
        Class<?> c = Class.forName(classeObjeto.getName());
        objRetorno = c.newInstance();
        return preencherValores(objRetorno, atributoValor);
    }
    
    /**
     * L� um arquivo CSV, faz a inst�ncia��o dos
     * objetos do par�metro e salva no BD.
     * 
     * @param arquivoCSV
     *            Arquivo de entrada.
     * @param objetoClasse
     *            Class que representa o objeto
     *            sendo lido.
     * */
    public void lerCSV(File arquivoCSV, Class<?> objetoClasse) {
        String linha = null;
        List<String> campos = null;
        List<String> linhaValores = null;
        Map<String, String> atributoValorAnotacao = null;
        Map<String, String> atributoValor = null;
        
        AbstractDAO<Object> dao = new AbstractDAO<Object>();
        
        try {
            
            BufferedReader br = Arquivo.getReaderArquivo(arquivoCSV);
            
            // Faz a leitura da primeira linha que
            // possui os campos do CSV
            if ((linha = Arquivo.lerLinha(arquivoCSV, br)) != null) {
                campos = Arquivo.lerCampos(linha);
            }
            
            // Faz a leitura do restante dos
            // arquivos
            if (!campos.isEmpty()) {
                while ((linha = Arquivo.lerLinha(arquivoCSV, br)) != null) {
                    linhaValores = Arquivo.lerCampos(linha);
                    atributoValorAnotacao = recuperarAtributoValorAnotacao(objetoClasse);
                    atributoValor = recuperarAtributoValorObjeto(campos, atributoValorAnotacao, linhaValores);
                    Object entidade = instanciarObjeto(objetoClasse, atributoValor);
                    
                    // DAO generico Object
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
    
    /**
     * Faz o Set de cada atributo fornecido. OBS:
     * O objeto precisa possuir um m�todo Set
     * p�blico para cada atributo fornecido.
     * 
     * @param obj
     *            Objeto que vai ter os campos
     *            preenchidos.
     * @param Map
     *            (nome_atributo,
     *            valor_do_atributo) para fazer os
     *            Sets.
     * @return Objeto preenchido.
     * */
    private Object preencherValores(Object obj, Map<String, String> atributoValores) throws IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, ClassNotFoundException, InstantiationException {
        String atributoValor, nomeMetodo;
        Class<?> c = obj.getClass();
        Field[] atributos = c.getDeclaredFields();
        Method[] metodos = c.getDeclaredMethods();
        for (Field f : Arrays.asList(atributos)) {
            if (f.isAnnotationPresent(Campo.class)) {
                atributoValor = atributoValores.get(f.getName());
                nomeMetodo = f.getName();
                nomeMetodo = "set" + nomeMetodo.substring(0, 1).toUpperCase() + nomeMetodo.substring(1);
                for (Method m : Arrays.asList(metodos)) {
                    if (m.getName().contains(nomeMetodo)) {
                        m.invoke(obj, converterValor(f.getType(), atributoValor));
                        break;
                    }
                }
            }
        }
        return obj;
    }
    
    /**
     * Recupera da classe desejada, os valores das
     * anota��es Campo e faz uma
     * map(valor_anotacao, nome_do_atributo).
     * 
     * @param objeto
     *            Class que representa o objeto
     *            desejado.
     * @return Map com as associa��es desejadas.
     * */
    private Map<String, String> recuperarAtributoValorAnotacao(Class<?> objeto) throws ClassNotFoundException {
        Map<String, String> atributoAnotacao = new HashMap<>();
        for (Field f : objeto.getDeclaredFields()) {
            Campo anotacao = f.getAnnotation(Campo.class);
            if (anotacao != null) {
                atributoAnotacao.put(anotacao.value(), f.getName());
            }
        }
        return atributoAnotacao;
    }
    
    /**
     * Recupera da lista de campos, do map de
     * valor das anota��es por atributo e dos
     * valores presente em uma linha os valores
     * para atribuir a um objeto.
     * 
     * @param campos
     *            Lista com todos os campos do
     *            CSV.
     * @param atributoValorAnotacao
     *            Map(valor_anotacao_campo,
     *            nome_do_atributo).
     * @param linhaValores
     *            Lista com os valores separados
     *            dos campos de uma linha.
     * @return Map(nome_atributo,
     *         valor_do_atributo).
     * */
    private Map<String, String> recuperarAtributoValorObjeto(List<String> campos,
            Map<String, String> atributoValorAnotacao, List<String> linhaValores) {
        Map<String, String> atributoValorObjeto = new HashMap<>();
        int i = 0;
        for (String campo : campos) {
            atributoValorObjeto.put(atributoValorAnotacao.get(campo), linhaValores.get(i));
            i++;
        }
        return atributoValorObjeto;
    }
    
}

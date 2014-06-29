package projetoMOO.anotacoes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Anotação para mapear um campo do arquivo CSV para um atributo de um objeto. * 
 * @author Daniel Magalhães
 * */
@Retention(RetentionPolicy.RUNTIME)
public @interface Campo {

	String value();
	
}

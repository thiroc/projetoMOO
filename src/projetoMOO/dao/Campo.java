package projetoMOO.dao;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Anota��o para mapear um campo do arquivo CSV
 * para um atributo de um objeto. *
 * 
 * @author Daniel Magalh�es
 * */
@Retention(RetentionPolicy.RUNTIME)
public @interface Campo {
    
    String value();
    
}

package projetoMOO.model;

import java.io.Serializable;

import projetoMOO.dao.Campo;

public class Cidade implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Campo("tmucodigo")
    private Integer           codigo;
    @Campo("tmuuf")
    private String            estado;
    @Campo("tmudenominacao")
    private String            nome;
    
    public final String       tipo             = "cidade";
    
    public Cidade() {
    }
    
    public Cidade(String nome) {
        this.nome = nome;
    }
    
    public final Integer getCodigo() {
        return codigo;
    }
    
    public final String getEstado() {
        return estado;
    }
    
    public final String getNome() {
        return nome;
    }
    
    public final void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    
    public final void setEstado(String estado) {
        this.estado = estado;
    }
    
    public final void setNome(String nome) {
        this.nome = nome;
    }
    
}

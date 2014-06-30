package projetoMOO.model;

import projetoMOO.dao.Campo;

public class Posto {
    
    @Campo("unilotacao")
    private Integer    capacidade;
    @Campo("uniid")
    private Integer    codigo;
    @Campo("unisigla")
    private SiglaPosto sigla;
    
    // Há informação de município, mabuscarCidades
    // os campos a partir de certo ponto no
    // arquivo ficam bagunçados
    // private Cidade cidade;
    
    public Integer getCapacidade() {
        return capacidade;
    }
    
    public Integer getCodigo() {
        return codigo;
    }
    
    public SiglaPosto getSigla() {
        return sigla;
    }
    
    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }
    
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    
    public void setSigla(SiglaPosto sigla) {
        this.sigla = sigla;
    }
    
    @Override
    public String toString() {
        return "Posto [codigo=" + codigo + ", capacidade=" + capacidade + ", sigla=" + sigla + "]";
    }
    
}

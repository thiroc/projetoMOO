package projetoMOO.model;

public class SiglaPosto {
    
    public static SiglaPosto parse(String atributo) {
        if (atributo.contains("DEL")) {
            return new SiglaPosto(atributo, "RJ");
        } else if (atributo.contains("PRFGO")) {
            return new SiglaPosto(atributo, "GO");
        } else if (atributo.contains("/")) {
            
            int lastIndexOf = atributo.lastIndexOf("/");
            String estado = atributo.substring(lastIndexOf + 1);
            String codigo = atributo.substring(0, lastIndexOf);
            SiglaPosto sigla = new SiglaPosto(codigo, estado);
            return sigla;
        } else if (atributo.contains("_")) {
            String[] split = atributo.split("_");
            SiglaPosto sigla = new SiglaPosto(split[0], split[1]);
            return sigla;
        } else {
            return null;
        }
    }
    
    private String codigo;
    
    private String estado;
    
    public SiglaPosto(String codigo, String estado) {
        this.codigo = codigo;
        this.estado = estado;
    }
    
    /**
     * @return o codigo
     */
    public String getCodigo() {
        return codigo;
    }
    
    /**
     * @return o estado
     */
    public String getEstado() {
        return estado;
    }
    
    /**
     * @param codigo
     *            o codigo a ser setado
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    /**
     * @param estado
     *            o estado a ser setado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return "SiglaPosto [codigo=" + codigo + ", estado=" + estado + "]";
    }
    
}

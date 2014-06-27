package projetoMOO.model;

import java.io.Serializable;

public class Cidade implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Double            latitude;
    
    private Double            longitude;
    private String            nome;
    
    public Cidade() {
    }
    
    public Cidade(String nome) {
        this.nome = nome;
    }
    
    public Cidade(String nome, double lat, double longi) {
        this.nome = nome;
        latitude = lat;
        longitude = longi;
        
    }
    
    public Double getLatitude() {
        return latitude;
    }
    
    public Double getLongitude() {
        return longitude;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString() {
        return "Cidade [latitude=" + latitude + ", longitude=" + longitude + ", nome=" + nome + "]";
    }
    
}

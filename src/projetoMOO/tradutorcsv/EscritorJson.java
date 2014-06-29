package projetoMOO.tradutorcsv;

import java.util.ArrayList;

import projetoMOO.model.Ocorrencia;

public interface EscritorJson {
    
    void escreverArquivos(ArrayList<Ocorrencia> arrayList);
}

/**
 * 
 */
package projetoMOO.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import projetoMOO.model.Cidade;

/**
 * @author Guilherme
 * 
 */
@SuppressWarnings("javadoc")
public class ReadExcel {
    
    // public static void main(String[] args)
    // throws IOException {
    // ReadExcel test = new ReadExcel();
    // test.setInputFile("Tabela Municipio Geo.xlsx");
    // test.read();
    // }
    private final EstadoUFDao dao = new EstadoUFDao();
    private String       inputFile;
    
    public void read() throws IOException {
        File inputWorkbook = new File(inputFile);
        
        if (inputWorkbook.exists()) {
            FileInputStream file = new FileInputStream(inputWorkbook);
            try {
                XSSFWorkbook workbook = new XSSFWorkbook(file);
                // Get first/desired sheet from
                // the workbook
                XSSFSheet sheet = workbook.getSheetAt(0);
                
                // Iterate through each rows one
                // by one
                Iterator<Row> rowIterator = sheet.iterator();
                rowIterator.next();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    // For each row, iterate
                    // through all the columns
                    Iterator<Cell> cellIterator = row.cellIterator();
                    String nome = cellIterator.next().getStringCellValue();
                    double lat = cellIterator.next().getNumericCellValue();
                    double longi = cellIterator.next().getNumericCellValue();
                    Cidade mooEntidade = new Cidade(nome, lat, longi);
                    dao.salvar(mooEntidade);
                    System.out.println(mooEntidade);
                }
                file.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }
    
}
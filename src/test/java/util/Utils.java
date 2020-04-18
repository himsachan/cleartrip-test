package util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class Utils {

	
	
    public void write(String tripCost) {
        HSSFWorkbook workbook = new HSSFWorkbook(); 
  
        HSSFSheet sheet = workbook.createSheet("Expense Details"); 
  
        Map<String, Object[]> data = new HashMap<String, Object[]>(); 
        data.put("1", new Object[]{ "ID", "Category", "cost" }); 
        data.put("2", new Object[]{ 1, "Trip to Hyderabad", tripCost }); 
        data.put("3", new Object[]{ 2, "Cab fares for the trip", "1200" }); 
        data.put("4", new Object[]{ 3, "Food expenses in Hyderabad", "1500" }); 
        data.put("5", new Object[]{ 4, "Miscellaneous expenses", "800" }); 
  
        Set<String> keyset = data.keySet(); 
        int rownum = 0; 
        for (String key : keyset) { 
            Row row = sheet.createRow(rownum++); 
            Object[] objArr = data.get(key); 
            int cellnum = 0; 
            for (Object obj : objArr) { 
                Cell cell = row.createCell(cellnum++); 
                if (obj instanceof String) 
                    cell.setCellValue((String)obj); 
                else if (obj instanceof Integer) 
                    cell.setCellValue((Integer)obj); 
            } 
        } 
        try { 
            // this Writes the workbook gfgcontribute 
            FileOutputStream out = new FileOutputStream(new File("ExpenseReport.xls")); 
            workbook.write(out); 
            workbook.close();
            out.close(); 
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 
    
}

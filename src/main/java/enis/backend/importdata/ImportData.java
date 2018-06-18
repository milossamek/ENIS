package enis.backend.importdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import enis.backend.service.VuzService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import enis.backend.model.LozneRozmery;
import enis.backend.model.Podvozek;
import enis.backend.model.Revize;
import enis.backend.model.Rozmery;
import enis.backend.model.RucniBrzda;
import enis.backend.model.StavKm;
import enis.backend.model.Vuz;
import enis.backend.model.VzduchovaBrzda;
import enis.backend.service.VuzServiceImpl;


public class ImportData {
    private static final int COLUMNCOUNT=20;
    private String filename;
    private VuzService service;



    public ImportData(String filename,VuzService service) {
        this.filename = filename;
        this.service=service;
    }

    public boolean convertExcelToObjects() throws Exception {
        HashMap<Integer, List<ExcelData>> excelData = readFile();


        if (excelData == null || excelData.size() == 0) {
            throw new Exception("Invalid filename");
        }

        Iterator it = excelData.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            LozneRozmery loznerozmery = new LozneRozmery();
            Podvozek podvozek = new Podvozek();
            Revize revize = new Revize();
            Rozmery rozmery = new Rozmery(loznerozmery);
            RucniBrzda rucnibrzda = new RucniBrzda();
            StavKm stavKm = new StavKm();
            VzduchovaBrzda vzduchovabrzda = new VzduchovaBrzda();
            Vuz vuz = new Vuz(podvozek,revize,rozmery,rucnibrzda,vzduchovabrzda,stavKm);


            List<Object> object = new ArrayList<>();
            object.add(loznerozmery);
            object.add(podvozek);
            object.add(revize);
            object.add(rozmery);
            object.add(rucnibrzda);
            object.add(stavKm);
            object.add(vzduchovabrzda);
            object.add(vuz);
            List<ExcelData> values = (List<ExcelData>) pair.getValue();

            for (Object o : object) {
                List<String> strings = showFields(o);
                for (String searchAttribute : strings) {
                    for (ExcelData value : values) {
                        if (value.getColumnName().toLowerCase().equals(searchAttribute.toLowerCase())) {
                            if (!value.getColumnData().trim().equals("")) {
                                ((IXLSSIMPORT) o).setAttribute(value.getColumnName(), value.getColumnData());
                            }
                            break;
                        }
                    }
                }
            }

            service.saveVuz(vuz);
            it.remove(); // avoids a ConcurrentModificationException
        }

        return true;
    }

    public List<String> showFields(Object o) {
        List<String> attributeList = new ArrayList<>();
        Class<?> clazz = o.getClass();

        for(Field field : clazz.getDeclaredFields()) {
            //you can also use .toGenericString() instead of .getName(). This will
            //give you the type information as well.

            attributeList.add(field.getName());
        }

        return attributeList;
    }

    private HashMap<Integer,List<ExcelData>> readFile(){
        HashMap<Integer,List<ExcelData>> dataList = new HashMap<>();
        List<String> headerList = null;
        File myFile = new File(filename);
        try {
            FileInputStream fis = new FileInputStream(myFile);
            XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
            XSSFSheet mySheet = myWorkBook.getSheetAt(0);
            Iterator<Row> rowIterator = mySheet.iterator();

            //nasbirame header do listu
            if (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                headerList = getHeaderList(row);
                System.out.println(validateHeader(headerList));
            }

            int rowIndex=0;
            //naplnime strukturu
            while (rowIterator.hasNext()) {
                rowIndex++;
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                int index=0;
                List<ExcelData> list = new ArrayList<>();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellTypeEnum()) {
                        case STRING:
                            list.add(new ExcelData(headerList.get(index), cell.getStringCellValue().trim()));
                            break;
                        case NUMERIC:
                            Double value = cell.getNumericCellValue();
                            list.add(new ExcelData(headerList.get(index), String.valueOf(value.intValue())));
                            break;
                        case BOOLEAN:
                            list.add(new ExcelData(headerList.get(index), Boolean.toString(cell.getBooleanCellValue())));
                            break;
                        default :
                    }
                    index++;
                }
                dataList.put(rowIndex,list);
            }
            
            myWorkBook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }

    private List<String> getHeaderList(Row row) {
        List<String> headList = new ArrayList<>();
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();

            if(cell.getCellTypeEnum().equals(CellType.STRING) && !cell.getStringCellValue().trim().equals("")) {
            	headList.add(cell.getStringCellValue().trim());
            }
        }
        return headList;
    }

    private Boolean validateHeader(List<String> header) {
        return (header.size() == COLUMNCOUNT);
    }

    private static class ExcelData {
        private String columnName;
        private String columnData;

        public ExcelData(String columnName, String columnData) {
            this.columnName = columnName;
            this.columnData = columnData;
        }

        public String getColumnName() {
            return columnName;
        }

        public String getColumnData() {
            return columnData;
        }
    }

}

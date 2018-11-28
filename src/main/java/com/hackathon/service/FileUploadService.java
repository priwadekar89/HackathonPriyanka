package com.hackathon.service;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.hackathon.dao.UploadDAO;
import com.hackathon.model.Questions;
 
public class FileUploadService {
 
    @Autowired
    UploadDAO uploadDao;
 
    public String uploadFileData(String inputFilePath){
    	
        try
        {
        	FileInputStream 	input_document = new FileInputStream(new File(inputFilePath));
            // Load workbook 
    		XSSFWorkbook workbook = new XSSFWorkbook(input_document);
            /* Load worksheet */
    		XSSFSheet sheet = workbook.getSheetAt(0);
           // workbook = getWorkBook(new File(inputFilePath));
            //sheet = workbook.getSheetAt(0);
 
           //Build the header portion of the Output File
            String headerDetails= "gq_id, gq_question, gq_marks, gq_level, gq_op1, gq_op1_ans, gq_op2, gq_op2_ans, gq_op3, gq_op3_ans, gq_op4, gq_op4_ans,  gq_tech";
            String headerNames[] = headerDetails.split(",");
 
            /*Read and process each Row*/
            ArrayList<Questions> questionList = new ArrayList<Questions>();
            Iterator<Row> rowIterator = sheet.iterator();
 
            while(rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                //Read and process each column in row
                Questions excelTemplateVO = new  Questions();
                int count=0;
                while(count<headerNames.length){
                    String methodName = "set"+headerNames[count];
                    String inputCellValue = getCellValueBasedOnCellType(row,count++);
                    setValueIntoObject(excelTemplateVO, Questions.class, methodName, "java.lang.String", inputCellValue);
                }
 
                questionList.add(excelTemplateVO);
            }
            uploadDao.saveFileDataInDB(questionList);
 
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
 
        return "Success";
    }
 
    public static Workbook getWorkBook(File fileName)
    {
        Workbook workbook = null;
        try {
            String myFileName=fileName.getName();
            String extension = myFileName.substring(myFileName.lastIndexOf("."));
            if(extension.equalsIgnoreCase(".xls")){
                workbook = new HSSFWorkbook(new FileInputStream(fileName));
            }
            else if(extension.equalsIgnoreCase(".xlsx")){
                workbook = new XSSFWorkbook(new FileInputStream(fileName));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return workbook;
    }
 
    public static String getCellValueBasedOnCellType(Row rowData,int columnPosition)
    {
        String cellValue=null;
        Cell cell = rowData.getCell(columnPosition);
        if(cell!=null){
            if(cell.getCellType()==Cell.CELL_TYPE_STRING)
            {
                String inputCellValue=cell.getStringCellValue();
                if(inputCellValue.endsWith(".0")){
                    inputCellValue=inputCellValue.substring(0, inputCellValue.length()-2);
                }
                cellValue=inputCellValue;
            }
            else if (cell.getCellType()==Cell.CELL_TYPE_NUMERIC)
            {
                Double doubleVal = new Double(cell.getNumericCellValue());
                cellValue= Integer.toString(doubleVal.intValue());
            }
        }
        return cellValue;
    }
 
    private static <T> void setValueIntoObject(Object obj, Class<T> clazz, String methodNameForField, String dataType, String inputCellValue)
            throws SecurityException, NoSuchMethodException, ClassNotFoundException, NumberFormatException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
 
        Method meth = clazz.getMethod(methodNameForField, Class.forName(dataType));
        T t = clazz.cast(obj);
 
        if("java.lang.Double".equalsIgnoreCase(dataType))
        {
            meth.invoke(t, Double.parseDouble(inputCellValue));
        }else if(!"java.lang.Integer".equalsIgnoreCase(dataType))
        {
            meth.invoke(t, inputCellValue);
        }
        else
        {
            meth.invoke(t, Integer.parseInt(inputCellValue));
        }
    }
 
}
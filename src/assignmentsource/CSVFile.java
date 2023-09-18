/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Tan Jee Schuan, Cha Wan Xun
 */
public class CSVFile extends FileHandler 
{
    public static final int HEADER_INDEX = 0;
    
    public static ArrayList<String[]> getAllRowByType(String filename, int columnIndex, String value) {
        //get column index of 2d array to search
        ArrayList<String[]> fileContent = FileHandler.readFileToArray(filename);
        ArrayList<String[]> foundRows = new ArrayList<>();
        
        //iterate thorugh selected column index of all rows to find value
        for (int i = 1; i < fileContent.size(); i++) {
            String[] row = fileContent.get(i);
            if (row[columnIndex].equals(value)) {
                foundRows.add(fileContent.get(i));
            }
        }

        return foundRows;
    }
    
    //return first row with column name with value
    public static ArrayList<String> getRowByType(String filename, String type, String value) {
        //get column index of 2d array to search
        ArrayList<String[]> fileContent = FileHandler.readFileToArray(filename);        
        int index = indexOfElement(fileContent.get(HEADER_INDEX), type);

        return getRowByType(filename, index, value);
    }
    
    //return first row of column [index] with value
    public static ArrayList<String> getRowByType(String filename, int columnIndex, String value) {
        ArrayList<String[]> fileContent = FileHandler.readFileToArray(filename);
        ArrayList<String> foundRow = new ArrayList<>();
        
        //iterate thorugh selected column index of all rows to find value
        for (int i = 1; i < fileContent.size(); i++) {
            String[] row = fileContent.get(i);
            if (row[columnIndex].equals(value)) {
                foundRow.addAll(Arrays.asList(fileContent.get(i)));
                break;
            }
        }
        return foundRow;
    }
    
    //return all values in column name
    public static ArrayList<String> getColumnByType(String filename, String type) {
        //find index with inputted header
        ArrayList<String[]> fileContent = FileHandler.readFileToArray(filename);
        int index = indexOfElement(fileContent.get(HEADER_INDEX), type);

        return getColumnByType(filename, index);
    }
    
    public static ArrayList<String> getColumnByType(String filename, int index) {
        ArrayList<String[]> fileContent = FileHandler.readFileToArray(filename);
        ArrayList<String> column = new ArrayList<>();
        
        for (int i = 1; i < fileContent.size(); i++) {
            column.add(fileContent.get(i)[index]);
        }
        return column;
    }
    
    //get index of String array with value, returns -1 if doesn't exist
    public static int indexOfElement(String[] header, String type) {
        int index = 0;
        for (int i = 0; i < header.length; i++) {
            if (header[i].equals(type)) {
                index = i;
                return index;
            }
        }
        return -1;
    }

    public static ArrayList<String> getRowByMainID(String filename, String id) {
        return getRowByType(filename, HEADER_INDEX, id);
    }
    
    //returns true if id exists
    public static boolean checkIDExist(String filename, String id) {
        ArrayList<String> fileContent = getRowByMainID(filename, id);
        return fileContent.size() > 0;
    }

    //=====================================================
    public static boolean updateDataByRow(String filename, String[] oldRow, String[] newRow)
    {
        ArrayList<String[]> fileContent = FileHandler.readFileToArray(filename);
        
        if (!fileContent.isEmpty())
        {
            for (int i = 0; i < fileContent.size(); i++)
            {
                String[] row = fileContent.get(i);
                
                if(Arrays.equals(oldRow, row))
                {
                    fileContent.set(i, newRow);
                    
                    FileHandler.rewriteFile(filename, fileContent);
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public static boolean updateDataByRow(String filename, ArrayList<String> oldRow, ArrayList<String> newRow)
    {
        ArrayList<String[]> fileContent = FileHandler.readFileToArray(filename);
        
        if (!fileContent.isEmpty())
        {
            for (int i = 0; i < fileContent.size(); i++)
            {
                String[] row = fileContent.get(i);
                
                if(oldRow.get(0).equals(row[0]))
                {
                    fileContent.set(i, newRow.toArray(new String[newRow.size()]));
                    
                    FileHandler.rewriteFile(filename, fileContent);
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public static boolean updateDataByID(String filename, String id, String type, String data) {
        ArrayList<String[]> fileContent = FileHandler.readFileToArray(filename);
        
        int index = indexOfElement(fileContent.get(HEADER_INDEX), type); //get index of target column
        
        for (int i = 1; i < fileContent.size(); i++) {
            String[] row = fileContent.get(i);
            
            if (row[0].equals(id)) { //if id match input
                row[index] = data;          //input column index = data
                
                fileContent.set(i, row);
                break;
            }
        }
        return FileHandler.rewriteFile(filename, fileContent);
    }
    
    public static boolean removeRow(String filename, ArrayList<String> targetRow) {
        ArrayList<String[]> fileContent = FileHandler.readFileToArray(filename);
        for (int i = 1; i < fileContent.size(); i++) {
            if (fileContent.get(i)[0].equals(targetRow.get(0))) {
                fileContent.remove(i);
                break;
            }
        }
        return FileHandler.rewriteFile(filename, fileContent);
    }

    public static boolean removeRowByID(String filename, String id) {
        ArrayList<String[]> fileContent = FileHandler.readFileToArray(filename);
        for (int i = 1; i < fileContent.size(); i++) {
            if (fileContent.get(i)[0].equals(id)) {
                fileContent.remove(i);
                break;
            }
        }
        return FileHandler.rewriteFile(filename, fileContent);
    }
    
    public static int getLastRowID(String filename) {
        ArrayList<String[]> fileContent = FileHandler.readFileToArray(filename);
        //remove header
        fileContent.remove(HEADER_INDEX);
        if (fileContent.size() == 0) {
            return -1;
        }
        
        String lastLineID = fileContent.get(fileContent.size() - 1)[0];
        String lastID = lastLineID.replaceAll("[^0-9]", "");
        return Integer.parseInt(lastID);
    }

    public static boolean rewriteFileObj(String filename, ArrayList<?> objects) {
        File myFile = new File(filePath + filename + ".txt");
        myFile.delete();
        ArrayList<String[]> tempArr = new ArrayList<String[]>();
        for (Object o : objects) {
            tempArr.add(o.toString().split(","));
        }
        return writeArrToFile(filename, tempArr);
    }

    public static boolean rewriteFileObj(String filename, ArrayList<?> objects, String[] header) {
        File myFile = new File(filePath + filename + ".txt");
        myFile.delete();
        ArrayList<String[]> tempArr = new ArrayList<String[]>();
        tempArr.add(header);
        for (Object o : objects) {
            tempArr.add(o.toString().split(","));
        }
        return writeArrToFile(filename, tempArr);
    }
}

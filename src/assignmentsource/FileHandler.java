package assignmentsource;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

//use to manage the data and textfile
//id all is int only
public class FileHandler{
    //file path
    private static final String filePath = "src/data/";
    
    //textfilename
    public static final String INVENTORY_DB = "inventory";
    public static final String CUSTOMER_DB = "customers";
    public static final String SALES_DB = "sales";
    public static final String TRANSACTION_DB = "transactions";
 
    //reportname
    //cus activity log 
    //datetime,description 
    public static final String ACTIVITY_LOG = "activities-log";   
    public static final String SALES_LOG = "sales-log";   
    public static final String PAYMENTS_LOG = "payments-log";
    //customerid ,updat time
    public static final String BLACKLIST_DB = "blacklist";
//=====================================================
//read
    public static ArrayList<String> readFile(String path){
        ArrayList<String> content = new ArrayList<>();
        
        try
        {
            File myFile = new File(path);
            //for read file
            Scanner myReader = new Scanner(myFile);
            
            //loop until no hasNextLine
            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                content.add(data);
            }
            myReader.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return content;
    }

    //read CSV to array
    public static ArrayList<String[]> readFileToArray(String filename){
        String path = filePath + filename + ".txt";
        ArrayList<String[]> content = new ArrayList<>();
        ArrayList<String> fileContent = readFile(path);
        
        for(String ele : fileContent){
            content.add(ele.split(","));
        }
        return content;
    }
//=====================================================    
//write
    
    //write string to file (auto newline)
    public static void writeFile(String filename, String content){
        try{
            String path = filePath + filename + ".txt";
            File myFile = new File(path);
             //check the present of the file
            if(!myFile.exists()){
                //if no exist will auto create a file
                myFile.createNewFile();
            }
            //to add new content
            FileWriter myWriter = new FileWriter(path, true);
            //line by line content
            myWriter.write(content + "\n");
            myWriter.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void writeArrToFile(String filename, String[] data){
        String content = String.join(",", data);
        writeFile(filename, content);
    }

    public static boolean writeArrToFile(String filename, ArrayList<String[]> data){
        for(String[] ele : data){
            writeArrToFile(filename, ele);
        }
        return true;
    }
//=====================================================
    public static boolean updateDataByID(String filename, String id, String type, String data){
        ArrayList<String[]> fileContent = readFileToArray(filename);
        String[] header = fileContent.get(0);
        int index = getDataColumn(header, type);

        for(int i = 1; i < fileContent.size(); i++){
            if(fileContent.get(i)[0].equals(id)){
                fileContent.get(i)[index] = data;
                break;
            }
        }
        return rewriteFile(filename, fileContent);
    }

    public static ArrayList<String> getRowByMainID(String filename, String id) {
        ArrayList<String[]> fileContent = readFileToArray(filename);
        ArrayList<String> row = new ArrayList<>();
        for(int i = 1; i < fileContent.size(); i++){
            if(fileContent.get(i)[0].equals(id)){
                Collections.addAll(row, fileContent.get(i));
                break;
            }
        }
        return row;
    }

    //return first row with column with value
    public static ArrayList<String> getRowByType(String filename, String type, String value) {
        ArrayList<String[]> fileContent = readFileToArray(filename);
        ArrayList<String> foundRow = new ArrayList<>();
        
        //get column index of 2d array to search
        int index = getDataColumn(fileContent.get(0), type);
        
        //iterate thorugh selected column index of all rows to find value
        for(int i = 1; i < fileContent.size(); i++){
            if(fileContent.get(i)[index].equals(value)){
                foundRow.addAll(Arrays.asList(fileContent.get(i)));
                break;
            }
        }
        return foundRow;
    }

    //return all columns of header name
    public static ArrayList<String> getColumnByType(String filename, String type) {
        ArrayList<String[]> fileContent = readFileToArray(filename);
        ArrayList<String> column = new ArrayList<>();
        int index = getDataColumn(fileContent.get(0), type);
        for(int i = 1; i < fileContent.size(); i++){
            column.add(fileContent.get(i)[index]);
        }
        return column;
    }

    public static boolean rewriteFile(String filename, ArrayList<String[]> data){
        File myFile = new File(filePath + filename + ".txt");
        myFile.delete();
        return writeArrToFile(filename, data);
    }

    public static int getDataColumn(String[] header, String type){
        int index = 0;
        for(int i = 0; i < header.length; i++){
            if(header[i].equals(type)){
                index = i;
                break;
            }
        }
        return index;
    }
    
    //custom
    public static boolean checkColumnExist(String filename, String column){
        ArrayList<String> fileContent = getColumnByType(filename, column);
        return fileContent.size() > 0;
    }

    public static boolean checkIDExist(String filename, String id){
        ArrayList<String> fileContent = getRowByMainID(filename, id);
        return fileContent.size() > 0;
    }

    public static int getLastRowID(String filename){
        ArrayList<String[]> fileContent = readFileToArray(filename);
        //remove header
        fileContent.remove(0);
        
        if (fileContent.size() == 0) //if is empty, return 0
        {
            return 0;
        }
        
        String lastLineID = fileContent.get(fileContent.size() - 1)[0];
        String lastID = lastLineID.replaceAll("[^0-9]", "");
        
        return Integer.parseInt(lastID);
    }

    public static boolean removeRowByID(String filename, String id){
        ArrayList<String[]> fileContent = readFileToArray(filename);
        for(int i = 1; i < fileContent.size(); i++){
            if(fileContent.get(i)[0].equals(id)){
                fileContent.remove(i);
                break;
            }
        }
        return rewriteFile(filename, fileContent);
    }
}

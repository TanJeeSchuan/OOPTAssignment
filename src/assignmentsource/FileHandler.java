/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;

/**
 *
 * @author CHA WAN XUN
 */
//testing
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
//for searching
import java.util.Collections;
import java.util.Scanner;
//for automatically to create content ,write file 
public class FileHandler{
    //filepath
    private static final String filePath = "src/data";
    //file name
    public static final String CUSTOMER_DB = "customers";
    public static final String SALES_DB = "sales";
    public static final String TRANSACTION_DB = "transactions";

    
    //read the file from specific file path
    public static ArrayList<String> readFile(String path){//file name
        ArrayList<String> content = new ArrayList<>();//the new array list

        try{
            //myfile for reading the specific file
            File myFile = new File(path);
            Scanner myReader = new Scanner(myFile);

            //loop to check whether new line to read
            //if yes ,add the content to list
            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                content.add(data);
            }
            myReader.close();//close the scanner
        }catch(Exception e){
            //print message if reading error 
            System.out.println(e.getMessage());
        }
        return content;
    }
//read file to array 
    public static ArrayList<String[]> readFileToArray(String filename){
       //normally filename will be “src/customerfile.txt
        String path = filePath + filename + ".txt";
        ArrayList<String[]> content = new ArrayList<>();
        ArrayList<String> fileContent = readFile(path);
        //check the end of the variable
        for(String ele : fileContent){
            //seperate the each variable with ","
            content.add(ele.split(","));
        }
        return content;
    }
//write file for create file
    public static void writeFile(String filename, String content){
        try{
            String path = filePath + filename + ".txt";
            File myFile = new File(path);
            //check the present of the file
            if(!myFile.exists()){
                //if no exist will auto create a file
                myFile.createNewFile();
            }
        
            FileWriter myWriter = new FileWriter(path, true);
            //line by line content
            myWriter.write(content + "\n");
            myWriter.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
//for update data
     public static boolean updateDataByID(String filename, String id, String type, String data){
        ArrayList<String[]> fileContent = readFileToArray(filename);
        String[] header = fileContent.get(0);
        int index = getDataColumn(header, type);
        //i=1 ,to skip the title
        for(int i = 1; i < fileContent.size(); i++){
            //check id element
            if(fileContent.get(i)[0].equals(id)){
                fileContent.get(i)[index] = data;
                break;
            }
        }
        //true for updating success
        return rewriteFile(filename, fileContent);
    }

//for searching
     public static ArrayList<String> getRowByType(String filename, String type, String value) {
        ArrayList<String[]> fileContent = readFileToArray(filename);
        ArrayList<String> row = new ArrayList<>();
        int index = getDataColumn(fileContent.get(0), type);
        for(int i = 1; i < fileContent.size(); i++){
            if(fileContent.get(i)[index].equals(value)){
                row.addAll(Arrays.asList(fileContent.get(i)));
                break;
            }
        }
        return row;
    }

    public static ArrayList<String> getColumnByType(String filename, String type) {
        ArrayList<String[]> fileContent = readFileToArray(filename);
        ArrayList<String> column = new ArrayList<>();
        //check for the type
        int index = getDataColumn(fileContent.get(0), type);
        for(int i = 1; i < fileContent.size(); i++){
            column.add(fileContent.get(i)[index]);
        }
        return column;
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

    public static boolean rewriteFile(String filename, ArrayList<String[]> data){
        File myFile = new File(filePath + filename + ".txt");
        myFile.delete();
        return writeArrToFile(filename, data);
    }

    //private？
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

    public static boolean checkIDExist(String filename, String id){
        ArrayList<String> fileContent = getRowByMainID(filename, id);
        return fileContent.size() > 0;
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
    
    public static int getLastRowID(String filename){
        ArrayList<String[]> fileContent = readFileToArray(filename);
        //remove header
        fileContent.remove(0);
        if (fileContent.size() == 0) {
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


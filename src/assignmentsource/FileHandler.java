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
    protected static final String filePath = "src/data/";
    
    //textfilename
    public static final String INVENTORY_DB = "inventory";
    public static final String CUSTOMER_DB = "customers";
    public static final String SALES_DB = "sales";
    public static final String TRANSACTION_DB = "transactions";
    public static final String USER_DB = "users";
    public static final String SOLD_ITEM_DB = "soldItems";
  
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

    //read CSV to 2d array
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
    
    //write string to file, append string to file (auto newline)
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

    //wrtie array to file
    public static void writeArrToFile(String filename, String[] data){
        String content = String.join(",", data);
        writeFile(filename, content);
    }

    //write 2d array to file
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
    
    public static boolean rewriteFileString(String filename, ArrayList<String> data){
        File myFile = new File(filePath + filename + ".txt");
        myFile.delete();
        
        for(String s: data){
            writeFile(filename, s);
        }
        
        return true;
    }
    
}

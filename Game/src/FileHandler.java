import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

	String filename;
	File file;
	
	public FileHandler(String name){
		this.filename = name;
		file = new File(filename);
	}
	
	public boolean check(){
		
		boolean status = false;
		
		if(file.exists()){
			status = true;
		}
		else{
			try {
				status = file.createNewFile();
			} catch (IOException e) {
				status = false;
	            System.out.println(
	                    "Error reading file '" 
	                    + filename + "'");   
			}
		}
		
		return status;	
	}
	
	public String read(){
		String out = "";
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(filename);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            out = bufferedReader.readLine();

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                filename + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + filename + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
	
        
        return out;
	}
	
	public boolean write(String w){
		boolean completed = true;
		try{
			FileWriter fileWriter = new FileWriter(filename);
			
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			bufferedWriter.write(w);
			
			bufferedWriter.close();
		}
		catch(FileNotFoundException ex){
			completed = false;
            System.out.println(
                    "Unable to open file '" + 
                    filename + "'");
		}
		catch(IOException ex){
			completed = false;
            System.out.println(
                    "Error reading file '" 
                    + filename + "'");   
		}
		
		
		return completed;
		
	}
	
}

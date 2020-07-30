import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class AchivManger {
	
	static ArrayList<String> ach = new ArrayList<String>();
	static ArrayList<String> achName = new ArrayList<String>();
	static ArrayList<String> achDesc = new ArrayList<String>();
	static String[] achivName;
	static String[] achivDesc;
	static String appName;
	
	public static void CAL() { //clear Array lists
		ach.clear();
		achName.clear();
		achDesc.clear();
		
	}
	public static void GADB(String dbloc,String appid)  { // get Achievement from DataBase (.db file)
		try {
		File file=new File(dbloc+appid+".db"); 
		if (file.exists()) {
		FileReader fr=new FileReader(file);   
		BufferedReader br=new BufferedReader(fr);    
		StringBuffer sb=new StringBuffer(); 

		int np=0; //App Name Position
		int indice =0;
		String line;  
		while((line=br.readLine())!=null)  
		{ 	
			
			if(line.replaceAll("\"","").replaceAll(",","").contains("name:")){
				if(np == 0) {ach.add(line.replaceAll("\"name\":","").replaceAll("\"","").replaceAll(",","").trim());np=1;}
				else{ach.add(line.replaceAll("\"name\":","").replaceAll("\"","").replaceAll(",","").replaceAll(" ",""));}
				
				}
			if(line.replaceAll("\"","").replaceAll(",","").contains("displayName")){
				achName.add(line.replaceAll("\"displayName\":","").replaceAll("\"","").replaceAll(",","").trim());
				achDesc.add("No description");
				indice = achName.size();
				
				}
			if(line.replaceAll("\"","").replaceAll(",","").contains("description")){
				
					achDesc.add(indice-1,line.replaceAll("\"description\":","").replaceAll("\"","").replaceAll(",","").trim());
					
				}
			
			
			
		} np=0;
			fr.close();
			
			
			appName = ach.get(0);
			ach.remove(0);
			
			
		}else {
			JOptionPane.showMessageDialog(null, "unable to find .db file launche Achivment Watcher then close it");
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		
		
	}
	public static void addAchiv(String datloc) {
		try {
			int i =0;
		    
		    FileWriter fileWriter = new FileWriter(datloc,true);
		    PrintWriter printWriter = new PrintWriter(fileWriter);
		    while(i < ach.size()) {

		    printWriter.print(ach.get(i)+"=0");
		    printWriter.println();
		    i++;
		    }
		    
		    printWriter.close();
		   
		  } catch (IOException e) {
			  JOptionPane.showMessageDialog(null,"An error occurred.");
		    e.printStackTrace();
		  }
	}
	public static void Unlock(String filePath,String oldString, String newString){
        File fileToBeModified = new File(filePath);
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            //Reading all the lines of input text file into oldContent
            String line = reader.readLine();
            while (line != null) 
            {
            	oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
             //Replacing oldString with newString in the oldContent
             String newContent = oldContent.replaceAll(oldString, newString);
             //Rewriting the input text file with newContent
             writer = new FileWriter(fileToBeModified);
             writer.write(newContent);
        }
        catch (IOException e){e.printStackTrace();}
        finally{
            try
            {
                //Closing the resources
                reader.close();
                writer.close();
            } 
            catch (IOException e){e.printStackTrace();}
        }
    }

	public static void FillTable() {
		
	}
	
}

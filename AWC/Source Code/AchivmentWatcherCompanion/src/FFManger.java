import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FFManger {
	static int spi=0;//save path indice 
public static void dirC(String dirloc) throws IOException { // directory creation
		
		File directory = new File(dirloc+"AWC");
	    if (! directory.exists()){
	        directory.mkdir();
	    	
	    }
		}
	public static void GFC(String dirloc,String appid) { // game folder creation
		
		File directory = new File(dirloc+appid);
	    if (! directory.exists()){
	        directory.mkdir();
	    }
		}
	public static void GFile(String dirloc) { // game file .dat creation
		File GF = new File(dirloc+"achieve.dat");
		  try {
			  FileWriter fileWriter = new FileWriter(GF);
			    PrintWriter printWriter = new PrintWriter(fileWriter);
			    printWriter.print("[ACHIEVE_DATA]");
			    printWriter.println();
			    printWriter.close();
			  }catch (IOException e) {e.printStackTrace();}
			
			}//[ACHIEVE_DATA]
	public static String FCP() { //file chooser selected path 
		JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if(f.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
        	return(f.getSelectedFile().getAbsolutePath()+"\\");
        
        	}
        else return null;
		
	}
	public static void savePath(String Path,int indice) throws IOException {
		// TODO Auto-generated method stub
		File Pathfile = new File("C:\\Users\\Public\\Documents\\Paths.txt");
        FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(Pathfile,true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> lines = Files.readAllLines(Pathfile.toPath());
		if(spi < 2) {
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.print(Path);
			printWriter.println();
			printWriter.close();
			spi =spi+1;
	    					}
		else {
			lines.set(indice, Path);
			Files.write(Pathfile.toPath(), lines);
			
		}
	    
		
	}
	public static String[]  GetPathFile(String path){//
		String[] paths = { null , null };
		try {
		File file=new File(path+"Paths.txt"); 
		if (file.exists()) {
			List<String> lines = Files.readAllLines(file.toPath() );
			if(lines.isEmpty()) {
				lines.add(null);
				lines.add(null);
			}
			paths[0] = lines.get(0);
			paths[1] = lines.get(1);
				return(paths);
		}
		else JOptionPane.showMessageDialog(null, "no paths are specified "); return (paths);
		}catch (IOException e) {
			
			e.printStackTrace();
			return (paths);
		}
		
		
		
	}
}

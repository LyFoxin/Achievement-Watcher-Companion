import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Controller {
	
	static String appidS;
	static String AWCdir = null;
	static String AWDBS = null;
	static String AU = null;
	static String[] paths = { null , null };
	
	public static void CreateAWCFolder() {
		AWCdir = FFManger.FCP();
		
        try {
        	FFManger.dirC(AWCdir);
        	FFManger.savePath(AWCdir,0);
    	    
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	public static void GetDataBaseFolder() {
		if(AWCdir == null) {
			JOptionPane.showMessageDialog(null, "Create AWC Folder First.");
		}else {
			AWDBS=FFManger.FCP();
        	try {
				FFManger.savePath(AWDBS,1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	System.out.println(AWDBS);
        }
		
	}
	public static void GetPathFile() {
		paths = FFManger.GetPathFile("C:\\Users\\Public\\Documents\\");
		AWCdir = paths[0];
		AWDBS = paths[1];
	}
	public static void CAFF() { //Create Achiv Folder & File for App
		
		FFManger.GFC(AWCdir+"AWC\\",appidS);
		FFManger.GFile(AWCdir+"AWC\\"+appidS+"\\");
		AchivManger.GADB(AWDBS,appidS);
		AchivManger.addAchiv(AWCdir+"AWC\\"+appidS+"\\"+"achieve.dat");
		
	} 
	public static void SAC(){ // Select and create btn
		List<String> lines = null;
		Controller.appidS = UI.textField.getText();
		File AchivFile = new File(Controller.AWCdir+"AWC\\",Controller.appidS);
		if(!AchivFile.exists()) {Controller.CAFF();}
		else {
			AchivManger.GADB(Controller.AWDBS,Controller.appidS);
			AchivManger.addAchiv(AWCdir+"AWC\\"+appidS+"\\"+"achieve.dat");
		}
		File File = new File(AWCdir+"AWC\\"+appidS+"\\"+"achieve.dat");
			try {
				lines = Files.readAllLines(File.toPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		 int i=0;
		    while(i<AchivManger.ach.size()) {
		        UI.model.addRow(new Object[0]);
		        
		        if(lines.get(i+1).contains("=1")) {
		        	UI.model.setValueAt(true,i,0);
		        }
		        else{
		        	UI.model.setValueAt(false,i,0);
		        	}
		        UI.model.setValueAt(AchivManger.achName.get(i), i, 1);
		        UI.model.setValueAt(AchivManger.achDesc.get(i), i, 2);
		        i++;
		    }
		    
		
	}
	public static void ClearModel() {
		
		int RowCount =UI.model.getRowCount();
		for(int i = RowCount - 1; i >= 0; i--) {
	    	
			UI.model.removeRow(i);
	        
	       
	    }
		
	}
}

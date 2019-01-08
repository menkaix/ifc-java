package ifc.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import ifc.data.IfcObject;
import ifc.data.IfcProject;

public class IfcFileLoader {
	
	public static void load(String fileName, IfcProject project) {
		
		try {
			BufferedReader reader =  new BufferedReader(new FileReader(fileName));
			
			String strSource = null ;
			
			do {
				
				strSource = reader.readLine();
				
				if(strSource!= null) {
					
					if(strSource.startsWith("#")) {
						
						IfcObject ifcObj = new IfcObject(strSource);
						
						project.ifcData.addElement(ifcObj);
						
						if(!project.types.contains(ifcObj.IFCName)) {
							
							project.types.add(ifcObj.IFCName);
							
						}
						
					}else {
						
						project.nonData.add(strSource);
						
					}
				}
				
				
				
			}while (strSource != null);			
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}

package ifc.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import ifc.data.IfcObject;
import ifc.data.IfcProject;

public class IfcFileWriter {
	
	public static void write(String fileName, IfcProject project) {
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,false));
			
			for (String str : project.nonData) {
				if(str.contains("DATA")) {
					writer.write(str+"\n");
					
					for (IfcObject obj : project.ifcData) {
						writer.write(obj.toString()+"\n");
					}
					
				}
				else {
					writer.write(str+"\n");
				}
			}
			
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}

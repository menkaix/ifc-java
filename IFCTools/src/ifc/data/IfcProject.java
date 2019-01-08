package ifc.data;

import java.util.Vector;

import ifc.io.IfcFileLoader;
import ifc.io.IfcFileWriter;

public class IfcProject {
	

	
	
	public Vector<String> types = new Vector<String>();
	
	public Vector<String> nonData = new Vector<String>();
	
	public Vector<IfcObject> ifcData = new Vector<IfcObject>();
	
	
	
	public void loadFile(String path) {
		
		IfcFileLoader.load(path, this);
		System.out.println("done");
		
	}
	
	public void saveFile(String path) {
		IfcFileWriter.write(path, this);
		System.out.println("done");
	}
	
	
	public void displayCountData() {
		System.out.println(ifcData.size());
	}
	
	
	
	
	public void displayCountTypes() {
		System.out.println(types.size());
	}
	
	
	
	
	
	
	
	
	
	public void echo() {
		System.out.println("here !");
	}
	
	public void echo(String str) {
		System.out.println(str);
	}
	
	
	

}

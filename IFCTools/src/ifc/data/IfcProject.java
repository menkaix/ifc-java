package ifc.data;

import java.util.Vector;

import ifc.io.IfcFileLoader;

public class IfcProject {
	

	public Vector<IfcObject> ifcData = new Vector<IfcObject>();
	
	public Vector<String> types = new Vector<String>();
	
	public void loadFile(String path) {
		try {
			IfcFileLoader.load(path, this);
			System.out.println("done");
		}catch(Exception e) {
			e.printStackTrace();
		}
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

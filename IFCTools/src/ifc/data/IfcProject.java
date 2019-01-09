package ifc.data;

import java.util.Vector;

import ifc.io.IfcFileLoader;
import ifc.io.IfcFileWriter;

public class IfcProject {
	

	private Vector<IfcObject> tempData = new Vector<IfcObject>(); 
	
	public Vector<String> types = new Vector<String>();
	
	public Vector<String> nonData = new Vector<String>();
	
	public Vector<IfcObject> ifcData = new Vector<IfcObject>();
	
	
	public void apply() {
		if(tempData.size()>0) {
			ifcData.clear();
			ifcData.addAll(tempData);
		}
	}
	
	public void edit() {
		
		tempData.clear();
		
		tempData.addAll(ifcData);
	}

	public void clear() {

		tempData.clear();
	}
	
	public void loadFile(String path) {
		
		IfcFileLoader.load(path, this);
		System.out.println("done");
		
	}
	
	public void scaleCartesianPoints(String factor) {
		try {
			float f = Float.parseFloat(factor.trim());
			
			System.out.println("scaling all points by : "+f);
			
			for(IfcObject obj : tempData) {
				
				if(obj.IFCName.equalsIgnoreCase("IFCCARTESIANPOINT")) {
					
					IFCCARTESIANPOINT point = (IFCCARTESIANPOINT)obj.pull();
					point.scale(f);
					obj.push(point);
										
				}
				
			}
		}catch(Exception e) {
			System.out.println(e.getStackTrace()[0].getFileName()+" : "+e.getStackTrace()[0].getLineNumber()+"\n"+e.getMessage());
		}
		
				
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

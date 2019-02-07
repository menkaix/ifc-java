package ifc.management;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import ifc.data.IFCAXIS2PLACEMENT3D;
import ifc.data.IFCCARTESIANPOINT;
import ifc.data.IFCLOCALPLACEMENT;
import ifc.io.IfcFileLoader;
import ifc.io.IfcFileWriter;

public class IfcProject {
	

	private Vector<IfcObject> tempData = new Vector<IfcObject>(); 
	
	public Vector<String> types = new Vector<String>();
	
	public Vector<String> nonData = new Vector<String>();
	
	public Vector<IfcObject> ifcData = new Vector<IfcObject>();
	
	
	public void show(String ID) {
		
		for(IfcObject obj : ifcData) {
			
			try {
				if(obj.ID == Integer.parseInt(ID.trim())) {
					System.out.println(obj.toString());
				}
			}catch(Exception e) {
				System.err.println(e.getClass().getSimpleName() + " "+e.getMessage());
			}
			
		}
	}
	
	public void showDependencies(String ID) {
		
		for(IfcObject obj : ifcData) {
			if(obj.ID == Integer.parseInt(ID)) {
				String temp = obj.IFCParam.replaceAll("[^\\.#,0123456789]","");
				
				String [] splits = temp.split(",");
				
				for(String s : splits) {
					if(s.startsWith("#"))
					show(s.trim().substring(1));
				}
				
				
			}
		}
		
	}
	
	public void findUsesOf(String ID) {
		for(IfcObject obj : ifcData) {
			int inHere = obj.IFCParam.split("#"+ID+"(?![0-9])").length-1 ;	
			if(inHere>0) {
				
				show(obj.ID+"");
				
			}
			
		}
	}

	public void findFirstUseOf(String ID) {
		for(IfcObject obj : ifcData) {
			int inHere = obj.IFCParam.split("#"+ID+"(?![0-9])").length-1 ;	
			if(inHere>0) {
				show(obj.ID+"");
				break ;
			}
			
		}
		
	}
	
	public void findFirstOfType(String type) {
		
		for(IfcObject obj : ifcData) {
			
			if(obj.IFCName.equalsIgnoreCase(type)) {
				System.out.println(obj.ID);
				
				break ;
			}
			
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unused" })
	public void findUnknown() {
		
		for(IfcObject obj : ifcData) {
			
			try {
				
				Class myClass = Class.forName("ifc.data."+obj.IFCName);
				
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				
				System.out.println("class not found : "+obj.IFCName);				
				
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	public void countObjectsOfType(String type) {
		int count = 0 ;
				
		for(IfcObject obj : ifcData) {
				
			if(obj.IFCName.equalsIgnoreCase(type)) {
				count ++ ;
			}
			
		}
		
		System.out.println(count);
	}
	
	public void countObjectReference(String id) {
		int count = 0 ;
		
		for(IfcObject obj : ifcData) {
			int inHere = obj.IFCParam.split("#"+id+"(?![0-9])").length-1 ;			
			
			count += inHere ;
			
			if(inHere>0) {
				System.out.println(obj.toString());
			}
			
		}
		
		System.out.println(count);
		
	}
	
	public void geometryStats() {
		
		int pointCount = 0 ;
		int originCount = 0 ;
		int loacalPlacementCount = 0 ;
		double minX = Double.POSITIVE_INFINITY ;
		double minY = Double.POSITIVE_INFINITY ;
		double minZ = Double.POSITIVE_INFINITY ;
		double maxX = Double.NEGATIVE_INFINITY ;
		double maxY = Double.NEGATIVE_INFINITY ;
		double maxZ = Double.NEGATIVE_INFINITY ;
		
		for(IfcObject obj : ifcData) {
			
			if(obj.IFCName.equals(IFCCARTESIANPOINT.class.getSimpleName())) {
				
				pointCount++;
				IFCCARTESIANPOINT point = (IFCCARTESIANPOINT)obj.pull();
				
				minX = Math.min(minX, point.getX()) ;
				minY = Math.min(minY, point.getY()) ;
				minZ = Math.min(minZ, point.getZ()) ;
				maxX = Math.max(maxX, point.getX()) ;
				maxY = Math.max(maxY, point.getY()) ;
				maxZ = Math.max(maxZ, point.getZ()) ;
													
			}//IFCAXIS2PLACEMENT3D
			else if (obj.IFCName.equals(IFCAXIS2PLACEMENT3D.class.getSimpleName())){
				originCount++ ;
			}
			else if (obj.IFCName.equals(IFCLOCALPLACEMENT.class.getSimpleName())){
				loacalPlacementCount++ ;
			}
		}
		
		System.out.println(pointCount+" vertex found");
		System.out.println();
		System.out.println("minX : "+minX);
		System.out.println("minY : "+minY);
		System.out.println("minZ : "+minZ);
		System.out.println("maxX : "+maxX);
		System.out.println("maxY : "+maxY);
		System.out.println("maxZ : "+maxZ);
		System.out.println();
		System.out.println("origin count : "+originCount);
		System.out.println("local placement : "+loacalPlacementCount);
		
		
		
	}
	
	public void scaleCartesianPoints(String factor) {
		try {
			float f = Float.parseFloat(factor.trim());
			
			System.out.println("scaling all points by : "+f);
			
			for(IfcObject obj : tempData) {
				
				if(obj.IFCName.equals(IFCCARTESIANPOINT.class.getSimpleName())) {
					
					IFCCARTESIANPOINT point = (IFCCARTESIANPOINT)obj.pull();
					point.scale(f);
					obj.push(point);
										
				}
				
			}
		}catch(Exception e) {
			System.out.println(e.getStackTrace()[0].getFileName()+" : "+e.getStackTrace()[0].getLineNumber()+"\n"+e.getMessage());
		}
		
				
	}

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
	
	
	public void displayTypesList() {
		for(String str : types) {
			System.out.println(str);
		}
	}
	
	
	
	
	
	
	public void echo() {
		System.out.println("here !");
	}
	
	public void echo(String str) {
		System.out.println(str);
	}
	
	
	

}

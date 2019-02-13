package ifc.management;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import ifc.data.IFCAXIS2PLACEMENT3D;
import ifc.data.IFCCARTESIANPOINT;
import ifc.data.IFCLOCALPLACEMENT;
import ifc.io.IfcFileLoader;
import ifc.io.IfcFileWriter;

public class IfcProject {
	
	public Vector<String> types = new Vector<String>();
	
	public Vector<String> nonData = new Vector<String>();
	
	public Vector<IfcObject> ifcData = new Vector<IfcObject>();
	
	
	private void createClass(String ifcName) {
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter("src/ifc/data/"+ifcName+".java",false));
			
			writer.write("package ifc.data;");writer.write("\n");
			writer.write("import ifc.management.IfcObject;");writer.write("\n");
			
			writer.write("public class "+ifcName+" extends IfcObject {");writer.write("\n");

			writer.write("	public "+ifcName+"(String source) {");writer.write("\n");
			writer.write("super(source);");writer.write("\n");
			writer.write("// TODO Auto-generated constructor stub");writer.write("\n");
			writer.write("}");writer.write("\n");

			writer.write("public "+ifcName+"(int id, String param) {");writer.write("\n");
			writer.write("super(id, param);");writer.write("\n");
			writer.write("// TODO Auto-generated constructor stub");writer.write("\n");
			writer.write("}");writer.write("\n");
			writer.write("}");writer.write("\n");

			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
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
	
	public void showGeometryStats() {
		
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

	public void showDependencies(String ID) {
		System.out.println("dependencies of #"+ID);
		for(IfcObject obj : ifcData) {
			if(obj.ID == Integer.parseInt(ID)) {
				for(int i : obj.dependencies()) {
					show(""+i) ;
				}
			}
		}
	}
	
	public void showDependencyTree(String ID) {
		
	}

	public void showUsesOf(String ID) {
		for(IfcObject obj : ifcData) {
			int inHere = obj.IFCParam.split("#"+ID+"(?![0-9])").length-1 ;	
			if(inHere>0) {
				
				show(obj.ID+"");
				
			}
			
		}
	}

	public void showCountData() {
		System.out.println(ifcData.size());
	}

	public void showTypesList() {
		for(String str : types) {
			System.out.println(str);
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
				
				createClass(obj.IFCName);
				
				
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
	
	public void countTypes() {
		System.out.println(types.size());
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
	
	public void loadFile(String path) {
		
		IfcFileLoader.load(path, this);
		System.out.println("done");
		
	}
	
	public void saveFile(String path) {
		IfcFileWriter.write(path, this);
		System.out.println("done");
	}
	
	
	
	public void echo() {
		System.out.println("here !");
	}
	
	public void echo(String str) {
		System.out.println(str);
	}
	
	
	

}

package ifc.management;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.StringTokenizer;

public class IfcObject {
	
	private IfcObject nature ;
	
	public int ID ;
	public String IFCName ;
	public String IFCParam ;
	
	public IfcObject(String source) {
		
		StringTokenizer stk = new StringTokenizer(source, "=");
		String token = stk.nextToken();
		
		if(token.startsWith("#")) {
						
			ID = Integer.parseInt(token.substring(1).trim());
			
		}else {
			System.err.println("invalid ID");
		}
		
		int b = source.indexOf("=");
		int e = source.indexOf("(");
		
		IFCName = source.substring(b+1, e).trim();
		IFCParam = source.substring(e).trim();
				
	}
	
	public IfcObject (int id, String param) {
		ID = id ;
		IFCParam = param ;
		IFCName = this.getClass().getSimpleName() ;
	}
	
	public IfcObject pull() {
		
		
		
		try {
			
			Class myClass = Class.forName("ifc.data."+this.IFCName);
			
			Constructor constructor;
			
			constructor = myClass.getConstructor(int.class, String.class);
			
			nature = ((IfcObject) constructor.newInstance(this.ID, this.IFCParam));		
			
			
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nature;		
	}
	
	public void push(IfcObject o) {
		
		if(o.IFCName.equals(IFCName)) {
			
			IFCParam = o.IFCParam ;
			
		}
		
	}
	
	
	
	public String toString() {
		
		return "#"+ID+" = "+IFCName+IFCParam ;
		
	}

}

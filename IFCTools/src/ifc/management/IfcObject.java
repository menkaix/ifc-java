package ifc.management;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.StringTokenizer;
import java.util.Vector;

public class IfcObject {
	
	private IfcObject nature ;
	
	public int ID ;
	public String IFCName ;
	public String IFCParam ;
	
	public Vector<String> paramArgs = new Vector<String>() ;
	
	private void updateParamArgs() {
		paramArgs.clear();
		
		StringTokenizer stk = new StringTokenizer(IFCParam, "(,);") ;
		
		while(stk.hasMoreTokens()) {
			paramArgs.add(stk.nextToken());
		}
		
	}
	
	public void applyParamChanges() {
		
	}
	
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
		
		updateParamArgs();
				
	}
	
	public IfcObject (int id, String param) {
		ID = id ;
		IFCParam = param ;
		IFCName = this.getClass().getSimpleName() ;
		
		updateParamArgs() ;
	}
	
	public String toString() {
		
		return "#"+ID+" = "+IFCName+IFCParam ;
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
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
	
	public Vector<Integer> dependencies(){
		
		Vector<Integer> list = new Vector<Integer>();
		
		String temp = IFCParam.replaceAll("[^\\.#,0123456789]","");
		
		String [] splits = temp.split(",");
		
		for(String s : splits) {
			if(s.startsWith("#")) {
				list.add(Integer.parseInt(s.trim().substring(1))) ;
			}					
		}	
		
		return list ;
	}

}

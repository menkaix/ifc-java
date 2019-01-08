package ifc.data;

import java.util.StringTokenizer;

public class IfcObject {
	
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
	
	public String toString() {
		
		return "#"+ID+" = "+IFCName+IFCParam ;
		
	}

}

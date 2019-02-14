package ifc.data;

import java.util.StringTokenizer;

/**
 * limite ou frontière d'une face
 * 
 * @author mendrika
 *
 */
public class IFCFACEOUTERBOUND extends IFCFACEBOUND {

	public int bound ;
	public boolean orientation ;
	

	public IFCFACEOUTERBOUND(int id, String param) {
		super(id, param);
		
		int d = param.indexOf("((");
		int f = param.indexOf("))");
		
		if(d>=0) {
			
			String data = param.substring(d+2, f);
			
			StringTokenizer stk = new StringTokenizer(data, ",");
			
			
			if(stk.countTokens()==2) {
				String loopRef = stk.nextToken() ;
				String direction = stk.nextToken() ;
				
				bound = Integer.parseInt(loopRef.substring(1)) ;
				orientation = direction.trim().equals(".T.");
				
			}
			
			
		}
		
	}

}

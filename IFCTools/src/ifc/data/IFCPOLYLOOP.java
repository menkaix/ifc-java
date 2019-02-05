package ifc.data;

import java.util.StringTokenizer;
import java.util.Vector;

import ifc.management.IfcObject;

public class IFCPOLYLOOP extends IFCLOOP {

	public Vector<Integer> pointIDs ;

	public IFCPOLYLOOP(int id, String param) {
		super(id, param);
		
		int d = param.indexOf("((");
		int f = param.indexOf("))");
		
		if(d>=0) {
			
			String data = param.substring(d+2, f);
			
			StringTokenizer stk = new StringTokenizer(data, ",");
			
			while(stk.hasMoreTokens()) {
				String pointID = stk.nextToken() ;
				
				pointIDs.add(Integer.parseInt(pointID.trim().substring(1)));
				
			}
			
		}
		
	}

}

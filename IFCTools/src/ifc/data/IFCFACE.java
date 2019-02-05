package ifc.data;

import java.util.StringTokenizer;

import ifc.logic.IfcTopologicalRepresentationItem;
import ifc.management.IfcObject;

/**
 * face geometrique
 * @author mendrika
 *
 */
public class IFCFACE extends IfcTopologicalRepresentationItem {

	//id for the IFCFACEOUTERBOUND
	public int boundID ;

	public IFCFACE(int id, String param) {
		super(id, param);
		
		int d = param.indexOf("((");
		int f = param.indexOf("))");
		
		if(d>=0) {
			
			String stringID = param.substring(d+2, f);
			
			if(stringID.startsWith("#")) {
				boundID = Integer.parseInt(stringID.substring(1)); // on enlève de #
			}
			
		}
		
	}

}

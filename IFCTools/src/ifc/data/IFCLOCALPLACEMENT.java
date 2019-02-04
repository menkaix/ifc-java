package ifc.data;

import ifc.logic.IfcObjectPlacement;

/**
 * Placement local des objets
 * 
 * deux paramètres :
 * 	PlacementRelTo : OPTIONAL IfcObjectPlacement;
 *	RelativePlacement : IfcAxis2Placement;
 * 
 * @author mendrika
 *
 */
public class IFCLOCALPLACEMENT extends IfcObjectPlacement {

	public IFCLOCALPLACEMENT(int id, String param) {
		super(id, param);
		// TODO Auto-generated constructor stub
	}

}

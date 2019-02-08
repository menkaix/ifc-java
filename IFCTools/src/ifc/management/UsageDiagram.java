package ifc.management;

public class UsageDiagram {

	public String userType ;
	public String usedType ;
	
	public UsageDiagram(String pUser, String pUsed) {
		
		userType = pUser ;
		usedType = pUsed ;
		
	}

	@Override
	public boolean equals(Object other) {
		
		try {
			UsageDiagram o = (UsageDiagram)other ;
			
			if(o.usedType.equals(usedType) && o.userType.equals(userType)){
				return true;
			}else {
				return false ;
			}
		} catch (ClassCastException e) {
			return false ;
		}
		
		
		
	}

}

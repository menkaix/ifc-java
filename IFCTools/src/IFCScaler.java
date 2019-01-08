import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

import ifc.IfcObject;

public class IFCScaler {

	
	public static Vector<String> types = new Vector<String>();
	public static Vector<String> allowedTypes = new Vector<String>();
	public static Vector<String> deniedTypes = new Vector<String>();
	public static Vector<IfcObject> ifcData = new Vector<IfcObject>();
	
	public static void main(String[] args) {
		
		Scanner kbd = new Scanner(System.in);
		
		try {
			BufferedReader reader =  new BufferedReader(new FileReader("COF_A10T_SAINT_ARNOULT.ifc"));
			BufferedWriter writer = new BufferedWriter(new FileWriter("COF_A10T_SAINT_ARNOULT2.ifc",false));
			
			String strSource ;
			
			do {
				strSource = reader.readLine();;
				
				if(strSource != null) {	
					
					if(strSource.startsWith("#")) {
					
						IfcObject ifcObj = new IfcObject(strSource);
						
						
						
						if(!types.contains(ifcObj.IFCName)) {
							
							types.add(ifcObj.IFCName);
							
							
							System.out.println("new class : " + ifcObj.IFCName+"\nAllow ?");
							
							String userAns = kbd.nextLine() ;
							
							if(userAns.startsWith("y")|| userAns.startsWith("Y")) {
								allowedTypes.add(ifcObj.IFCName);
							}else {
								deniedTypes.add(ifcObj.IFCName);
								
							}
							
						}
						
						if(ifcObj.ID>0 && allowedTypes.contains(ifcObj.IFCName)) {
							ifcData.addElement(ifcObj);
							writer.write(ifcObj.toString()+"\n");
						}
						
						
						
					}else {
						writer.write(strSource+"\n");
					}
					
					
					//--------------------------------------------------
					//if(strSource.contains("IFCCARTESIANPOINT")) {
					//	writer.write(scale(strSource, 0.01f)+"\n");
					//}else {
					//	
					//	writer.write(strSource+"\n");
					//}
					//--------------------------------------------------		
					
				}		
				
			}while (strSource != null);
			
			
			reader.close();
			writer.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

	private static String scale(String string, float scale) {
		
		int d = string.indexOf("((");
		int f = string.indexOf("))");
		
		if(d>0) {
			String ans = string.substring(0, d) ;
			
			String triplet = string.substring(d+2, f);
			
			StringTokenizer stk = new StringTokenizer(triplet, ",");
			
			if(stk.countTokens() !=3) {
				return string ;
			}
			
			float x = Float.parseFloat(stk.nextToken()) * scale;
			float y = Float.parseFloat(stk.nextToken()) * scale;
			float z = Float.parseFloat(stk.nextToken()) * scale;
			
			ans +="(("+ x + ", "+ y + ", "+z + "));" ; 
						
			return ans ;
			
		}else {
			
			return string ;
			
		}
		
	}

}

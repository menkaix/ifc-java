import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import ifc.data.IfcProject;

public class IFCTools {

	public static void main(String[] args) {
		
		Scanner kbd = new Scanner(System.in);
		
		String line = "";
		
		IfcProject project = new IfcProject() ;
		
		System.out.println("Welcome To IFC-Tools");
		
		while(!line.trim().equalsIgnoreCase("exit")) {
			
			line = kbd.nextLine() ;
			
			String[] arguments = line.split(" ");
			
			if(arguments.length>0) {
				try {
					if(arguments.length==1) {
						project.getClass().getMethod(arguments[0]).invoke(project);
					}else {
						project.getClass().getMethod(arguments[0], line.getClass()).invoke(project, line.substring(line.indexOf(" ")+1));
					}
					
					
				} catch (IllegalAccessException e) {
					System.err.println("IllegalAccessException : "+e.getMessage());
				} catch (IllegalArgumentException e) {
					System.err.println("IllegalArgumentException : "+e.getMessage());
				} catch (InvocationTargetException e) {
					System.err.println("InvocationTargetException : "+e.getMessage());
				} catch (NoSuchMethodException e) {
					System.err.println("NoSuchMethodException : "+e.getMessage());
				} catch (SecurityException e) {
					System.err.println("SecurityException : "+e.getMessage());
				}
			}
			
			
			
		}
		
		kbd.close();

	}

}

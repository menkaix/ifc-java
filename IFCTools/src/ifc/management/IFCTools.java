package ifc.management;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import ifc.management.IfcProject;

public class IFCTools {
	
	
	public static void executeLine(Object context, String line) {

		if(line.length()<=0) {
			return ;
		}
		
		String[] arguments = line.split(" ");
		
		if(arguments.length>0 && !line.trim().equalsIgnoreCase("exit")) {
			try {
				if(arguments.length==1) {
					context.getClass().getMethod(arguments[0]).invoke(context);
				}else {
					context.getClass().getMethod(arguments[0], line.getClass()).invoke(context, line.substring(line.indexOf(" ")+1));
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
	

	public static void main(String[] args) {
		
		Scanner kbd = new Scanner(System.in);
		
		String line = "";
		
		IfcProject project = new IfcProject() ;
		
		System.out.println("Welcome To IFC-Tools");
		
		while(!line.trim().equalsIgnoreCase("exit")) {
			System.out.println("command : ");
			line = kbd.nextLine() ;
			//System.out.println("["+line+"]");
			
			 executeLine(project, line);
			
			
		}
		
		kbd.close();

	}

}

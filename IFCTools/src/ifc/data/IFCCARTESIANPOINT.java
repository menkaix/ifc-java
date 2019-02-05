package ifc.data;

import java.util.StringTokenizer;

import ifc.logic.IfcPoint;
import ifc.management.IfcObject;
/**
 * Représente un point dans l'espace
 * @author mendrika
 *
 */
public class IFCCARTESIANPOINT extends IfcPoint{
	
	private double x ;
	private double y ;
	private double z ;
	
	private void apply() {
		IFCParam = "((" + x + ", " + y + ", " + z+"));" ;
		
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public void scale(double f) {
		x*= f ;
		y*= f ;
		z*= f ;
		apply();
	}
	
	public void translate(double tx,double ty, double tz) {
		x+= tx ;
		y+= ty ;
		z+= tz ;
		apply();
	}
	
	public String toString() {
		System.out.println(IFCParam);
		return "#"+ID+" = "+IFCName+"((" + x + ", " + y + ", " + z+"));" ;
	}

	public IFCCARTESIANPOINT(int id, String param) {
		super(id,param);
		
		int d = param.indexOf("((");
		int f = param.indexOf("))");
		
		if(d>=0) {
			
			String triplet = param.substring(d+2, f);
			
			StringTokenizer stk = new StringTokenizer(triplet, ",");
			
			if(stk.countTokens() ==3) {
				
				x = Float.parseFloat(stk.nextToken().trim()) ;
				y = Float.parseFloat(stk.nextToken().trim()) ;
				z = Float.parseFloat(stk.nextToken().trim()) ;			
			
			}
			
		}
	}

}

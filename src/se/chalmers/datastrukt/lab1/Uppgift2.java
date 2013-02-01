package se.chalmers.datastrukt.lab1;
/**
 * 
 * @author tomassellden and Anton Palmqvist group 36
 *
 */
public class Uppgift2 {

	public static double binarySqrt(double sqr, double eps) {
		if (sqr <= 1) {
			throw new IllegalArgumentException(
					"The number you want to square must be over =>1");
		}
		return help(sqr, eps, 1, sqr);

	}

	private static double help(double sqr, double eps, double low, 
			double high) {

		if (Math.abs(high - low) < eps) {
			return (high + low) / 2;
		} else {
			double tmpSqr = ((high - low) / 2) + low;
			if (Math.pow(tmpSqr, 2) > sqr) {
				high = tmpSqr;
			} else {
				low = tmpSqr;
			}
			return help(sqr, eps, low, high);
		}
	}

	public static void main(String[] args) {
		double a = 12354;
		double eps = 10e-6;
		// Test with Math.sqrt
		System.out.println("* Beräknat värde=" + binarySqrt(a, eps) + " # bör vara nära " +
				"Math.sqrt-värde=" + Math.sqrt(a)+ "\nmed en felmarginal på " + eps + "\n");
		
		//Test without Math.sqrt
		double b = 100;
		double bRot = binarySqrt(b, eps);
		System.out.println("* Beräknat rotvärdevärde gånger sig självt=" + bRot*bRot + " # " +
				"bör vara nära ursprungsvärdet=" + b + "\nmed en felmarginal på " + eps + "\n");

		double c = 37;
		double cRot = binarySqrt(c, eps);
		System.out.println("* Beräknat rotvärdevärde gånger sig självt=" + cRot*cRot + " # " +
				"bör vara nära ursprungsvärdet=" + c + "\nmed en felmarginal på " + eps + "\n");
	}
}

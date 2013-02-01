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
		System.out.println(Math.sqrt(a));
		a = binarySqrt(a, 10e-6);
		System.out.println(a);
	}
}

package se.chalmers.datastrukt.lab1;

public class Uppgift2 {

	public static double binarySqrt(double sqr, double eps) {
		if (sqr <= 1) {
			throw new IllegalArgumentException();// ta bort???
		}
		double tmpsqr = ((sqr - 1) / 2) + 1;
		if (Math.pow(tmpsqr, 2) > sqr) {
			return help(sqr, eps, 1, tmpsqr);
		} else {
			return help(sqr, eps, tmpsqr, sqr);
		}

	}

	private static double help(double sqr, double eps, double low, double high) {

		if (Math.abs(high - low) < eps) { // behšvs Math.abs?
			return (high + low) / 2; // sqr ?
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
		double a = 1.75;
		System.out.println(Math.sqrt(a));
		a = binarySqrt(a, 10e-6);
		System.out.println(a);
	}
}

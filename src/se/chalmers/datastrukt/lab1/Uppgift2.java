package se.chalmers.datastrukt.lab1;

/**
 * 
 * @author tomassellden and Anton Palmqvist group 36
 * 
 */

public class Uppgift2 {
	/**
	 * check if the input sqr is higher than 1 and then the method call the
	 * helpmethod help
	 * 
	 * @param sqr
	 *            the value you want to calculate the squareroot from
	 * @param eps
	 *            the margin of error for the calculated square root
	 * @return
	 */
	public static double binarySqrt(double sqr, double eps) {
		if (sqr <= 1) {
			throw new IllegalArgumentException(
					"The number you want to square must be over 1");
		}
		return help(sqr, eps, 1, sqr);

	}

	/**
	 * Find the square root of given number sqr with a margin of error eps
	 * 
	 * @param sqr
	 *            the value you want to calculate the squareroot from
	 * @param eps
	 *            the margin of error for the calculated square root
	 * @param low
	 *            the lowest value the square root can be
	 * 
	 * @param high
	 *            the highest value the square root can be
	 * @return double the square root of sqr with the margin of error eps
	 */
	//Remark: declared private since it is only called within the class
	private static double help(double sqr, double eps, double low, 
			double high){
		
		double tmpSqr = ((high - low) / 2) + low;
		if (Math.abs(Math.pow(tmpSqr, 2)-(sqr)) < eps) {
			return tmpSqr;
		} else {
			if (Math.pow(tmpSqr, 2) > sqr) {
				high = tmpSqr;
			} 
			else {
				low = tmpSqr;
			}
			return help(sqr, eps, low, high);
		}
	}

	public static void main(String[] args) {
		double a = 12354;
		double eps = 10e-6;
		// Test with Math.sqrt
		System.out.println("* Beräknat värde=" + binarySqrt(a, eps) + " # " +
				"bör " + "vara nära " + "Math.sqrt-värde=" + Math.sqrt(a)+ 
				"\nmed en " + "felmarginal på " + eps);
		if(((binarySqrt(a, eps)))-(Math.sqrt(a))<eps){
			System.out.println("* Inom intervallet # Korrekt!\n");
		}
		else{
			System.out.println("* Utanför intervallet # FELAKTIGT!\n");
		}


		//Test without Math.sqrt
		double b = 100;
		double bRot = binarySqrt(b, eps);
		System.out.println("* Beräknat rotvärdevärde gånger sig självt=" + 
				bRot*bRot + " # " + "bör vara nära ursprungsvärdet=" + b + 
				"\nmed en felmarginal på " + eps);
		if((b-(bRot*bRot))<eps){
			System.out.println("* Inom intervallet # Korrekt!\n");
		}
		else{
			System.out.println("* Utanför intervallet # FELAKTIGT!\n");
		}

		double c = 37;
		double cRot = binarySqrt(c, eps);
		System.out.println("* Beräknat rotvärdevärde gånger sig självt=" + 
				cRot*cRot + " # " + "bör vara nära ursprungsvärdet=" + c + 
				"\nmed en felmarginal på " + eps);
		if((c-(cRot*cRot))<eps){
			System.out.println("* Inom intervallet # Korrekt!\n");
		}
		else{
			System.out.println("* Utanför intervallet # FELAKTIGT!\n");
		}
		
		double d = 9;
		double dRot = binarySqrt(d, eps);
		System.out.println("* Beräknat rotvärdevärde gånger sig självt=" + 
				dRot*dRot + " # " + "bör vara nära ursprungsvärdet=" + d + 
				"\nmed en felmarginal på " + eps);
		if((d-(dRot*dRot))<eps){
			System.out.println("* Inom intervallet # Korrekt!\n");
		}
		else{
			System.out.println("* Utanför intervallet # FELAKTIGT!\n");
		}

		//Test of exception
		//binarySqrt(-2, 1000);
		/*Exception "IllegalArgumentException: The number you want to 
		 * square must be over =>1"
			kastas på rätt sätt. (Bortkommenterad för att koden skall köras)*/
	}
}

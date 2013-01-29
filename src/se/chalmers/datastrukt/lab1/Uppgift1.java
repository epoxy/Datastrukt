package se.chalmers.datastrukt.lab1;


public class Uppgift1 {

	private String[] stringArr;
	private String[] tmpStringArr;
	private int numberOfElements = 0;

	public Uppgift1(int length) {
		this.stringArr = new String[length];
	}

	public void addFirst(String element) {
		int tmpCount = 1;
		// int number = numberOfElements(stringArr);
		tmpStringArr = new String[numberOfElements + 1];
		for (int i = 0; i < stringArr.length; i++) {
			if (stringArr[i] != null) {
				tmpStringArr[tmpCount] = stringArr[i];
				tmpCount++;
			}
			
		}
		tmpStringArr[0] = element;
		stringArr = tmpStringArr;

		numberOfElements++;

	}

	// private int numberOfElements(String[] strArray) {
	// int number = 0;
	// for (int i = 0; i < strArray.length; i++) {
	// if (strArray[i] != null) {
	// number++;
	// }
	// }
	// return number;
	// }

	public boolean empty() {
		for (int i = 0; i < stringArr.length; i++) {
			if (stringArr[i] != null) {
				return false;
			}
		}
		return true;
	}

	public String getFirst() {
		return stringArr[0];
	}

	public void removeFirst() {
		if (!empty()) {
			int nbrOfElem = numberOfElements - 1;
			tmpStringArr = new String[nbrOfElem];
			System.arraycopy(stringArr, 1, tmpStringArr, 0, nbrOfElem);
			stringArr = tmpStringArr;
		}
		if (stringArr.length > 0) {
			numberOfElements--;
		}
	}

	public boolean existP(String elem) {
		for (int i = 0; i < stringArr.length; i++) {
			if (elem.equals(stringArr[i])) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < stringArr.length; i++) {
			stringBuilder.append(stringArr[i]).toString();
			if (i < stringArr.length - 1) {
				stringBuilder.append(" , ");
			}
		}
		String str = "[ " + stringBuilder.toString();
		str += " ] ";

		return str;
	}

	public static void main(String[] args) {
		int stringLength = 10;
		Uppgift1 uppg = new Uppgift1(stringLength);

		// First test for method empty
		System.out.println("*" + uppg.empty() + " # bšr vara true");

		// Test for method addFirst
		uppg.addFirst("hej");
		uppg.addFirst("Tomas");
		uppg.addFirst("Anton");
		uppg.addFirst("Henrik");
		uppg.addFirst("hejsan");
		System.out.println("*" + uppg + " # bšr vara hejsan fšrst");

		// Second test for method empty
		System.out.println("*" + uppg.empty() + " # bšr vara false");

		// Test for method getFirst
		System.out.println("*" + uppg.getFirst() + " # bšr vara hejsan");

		// Test for method removeFirst
		uppg.removeFirst();
		System.out.println("*" + uppg + " # bšr vara Henrik fšrst");

		// Test for method existP
		System.out.println("*" + uppg.existP("Anton") + " # bšr vara true");
		System.out.println("*" + uppg.existP("Adam") + " # bšr vara false");
	}
}

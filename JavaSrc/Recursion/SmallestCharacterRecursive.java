import java.util.*;

public class SmallestCharacterRecursive {
	public static void main (String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Input a string: ");
		String string = "empty";
		
		while(!string.equals("0")) {
			string = input.next();
			int minChar = Integer.MAX_VALUE;
			System.out.println("The smallest character is: " + minChar(string, 0, minChar));
		}
		System.out.println("System exit.");
	}
	public static char minChar(String a, int counter, int minChar) {
		if(counter == a.length()) {
			return (char)(minChar);
		}
		else {
			if((int)(a.charAt(counter)) < minChar) {
				minChar = (int)(a.charAt(counter));
			}
			return minChar(a, (counter+1), minChar);
		}
	}
}

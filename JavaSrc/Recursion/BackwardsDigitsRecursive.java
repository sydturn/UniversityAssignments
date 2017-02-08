import java.util.Scanner;

public class BackwardsDigitsRecursive {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("First");
		reverseInt(input.nextInt());
		System.out.println();
		System.out.println("Second(you may input a negative)");
		reverseIntRevised(input.nextInt(), false);
		System.out.println("Third");
		System.out.println(reverseIntRevisedTwo(input.nextInt(), 0));
	}
	public static void reverseInt(int n) {
		if (n == 0) {
			//do nothing
		}
		else {
			System.out.print(n%10);
			reverseInt((n/10));
		}
	}
	
	public static void reverseInt2(int n) {
		if(n<0){
			reverseInt2(-n);
			System.out.println("-");
			return;
		}
		if (n == 0) {
			//do nothing
		}
		else {
			System.out.print(n%10);
			reverseInt((n/10));
		}
	}
	public static void reverseIntRevised(int n, boolean checkNegative) {
		if (n == 0) {
			if(checkNegative) {
				System.out.println("-");
			}
		}
		else {
			if (n < 0) {
				n = n * - 1;
				checkNegative = true;
			}
			System.out.print(n%10);
			reverseIntRevised((n/10), checkNegative);
		}
	}
	
	public static int reverseIntRevisedTwo(int n, int newint) {
		if(n<0) return -reverseIntRevisedTwo(-n, newint);
		if (n == 0) {
				return newint/10;
		}
		else {
			newint = (newint + (n%10))*10;
			n = n/10;
			return reverseIntRevisedTwo((n), newint);
		}
	}
}

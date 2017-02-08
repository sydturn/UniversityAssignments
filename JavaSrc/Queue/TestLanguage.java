/**
 * Created by Sydney on 7/22/2016.
 */
import java.util.Scanner;

public class TestLanguage {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s = "";


        while(!s.equals("exit")) {
            System.out.print("Input a word: ");
            s = input.nextLine().toLowerCase();
            if(s.equals("exit")){
                break;
            }
            System.out.println((check(s) ? "Yes! " + s + " is " : "No. " + s + " is not ") + "in the language.\n");
        }
    }

    public static boolean check(String s) {
        Language stack = new Language();
        if (s.length() % 2 != 0) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == s.charAt(0)) {
                stack.push(s.charAt(i));
            }
            else if (i >= s.length()/2 && s.charAt(i)==s.charAt(s.length()/2)) {
                stack.pop();
            }
            else {
                return false;
            }
        }
        if (stack.peek()!= null) {
            return false;
        }
        return true;
    }
}

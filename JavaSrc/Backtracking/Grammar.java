import java.util.Scanner;

/**
 * Created by Sydney on 8/3/2016.
 * This program evaluates whether an expression is a valid infix expression or not.
 */
public class Grammar {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input a test expression: ");
        String s = "";

        while (!s.toLowerCase().equals("exit")) {
            s = input.nextLine();
            if(s.toLowerCase().equals("exit")) {
                break;
            }
            if (findExpression(s, 0, s.length() - 1) == s.length() - 1) {
                System.out.println("Yes, '" + s + "' is a grammatically correct infix expression.");
            } else {
                System.out.println("No, '" + s + "' is not a grammatically correct infix expression.");
            }
            System.out.println("Input a test expression: ");
        }
        System.out.println("System exit.");
    }

    /*Find an expression:
    * The grammar specifies that an expression is either a single term
    * followed by a + or -, which must  be followed by a second term*/
    private static int findExpression(String str, int start, int end) {

        //returns the int of the position of the last character of the string or -1 if the string is not a valid expression
        if(start < 0 || start > end) {
            return -1;   //invalid starting value or ran out of characters to process
        }
        start = findTerm(str, start, end);  //returns the last character of the term or -1 if can't find a term
        if(start == -1) {
            return -1;
        }
        if(str.charAt(start+1) == '+' || str.charAt(start+1) == '-') {
            return findTerm(str, start + 2, end);
        }

        return start;
    }

    /*Find a term: The grammar specifies that a term is either a single factor or
    a factor followed by a  or a /, which must then be allowed by a second factor*/
    private static int findTerm(String s, int start, int end) {
        start = findFactor(s, start, end);
        if(s.charAt(start+1) == '*' || s.charAt(start+1) == '/') {
            return findFactor(s, start + 2, end);
        }
        return -1;
    }

    /*Find a factor: The grammar specifies that a factor is either a single letter (the base case)
     or an expression enclosed in parentheses. */
    private static int findFactor(String s, int start, int end) {
        if((s.charAt(start) >= 'A' && s.charAt(start) <= 'Z') || (s.charAt(start) >= 'a' && s.charAt(start) <= 'z')) {
            return start;
        }
        else if(s.charAt(start) == '(') {
            findExpression(s, start+1, end);
            //check for ')'
        }
        return -1;
    }
}

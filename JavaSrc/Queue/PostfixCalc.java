/**
 * Created by Sydney on 7/22/2016.
 */
import java.util.*;
public class PostfixCalc {
    public static void main(String[]args) {
        Scanner input = new Scanner(System.in);
        String eqn = "";
        StackPostfix stack = new StackPostfix();

        while(eqn!="exit") {
            System.out.print("Input a postfix expression with spaces between all operators and operands or type 'exit' to leave: ");
            eqn = input.nextLine().toLowerCase();
            if(eqn.equals("exit")) {
                System.out.println("System exit.");
                break;
            }
            String[] array = eqn.split(" ");


            for (String character : array) {
                try {
                    stack.push(Double.parseDouble(character));
                } catch (Exception e) {
                    switch (character) {
                        case "*":
                            double operand2 = stack.pop();
                            double operand1 = stack.pop();
                            double result = operand1 * operand2;
                            stack.push(result);
                            break;
                        case "/":
                            operand2 = stack.pop();
                            operand1 = stack.pop();
                            result = operand1 / operand2;
                            stack.push(result);
                            break;
                        case "+":
                            operand2 = stack.pop();
                            operand1 = stack.pop();
                            result = operand1 + operand2;
                            stack.push(result);
                            break;
                        case "-":
                            operand2 = stack.pop();
                            operand1 = stack.pop();
                            result = operand1 - operand2;
                            stack.push(result);
                            break;
                        case "%":
                            operand2 = stack.pop();
                            operand1 = stack.pop();
                            result = operand1 % operand2;
                            stack.push(result);
                            break;
                        case "^":
                            operand2 = stack.pop();
                            operand1 = stack.pop();
                            result = Math.pow(operand1, operand2);
                            stack.push(result);
                            break;
                    }
                }
            }
            System.out.println("The expression (" + eqn + ") evaluates to " + stack.peek());
        }
    }
}

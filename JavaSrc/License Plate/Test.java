import java.util.Scanner;

/**
 * Created by Sydney on 7/12/2016.
 * Create a test class to demonstrate all the features including advancing and reducing over month and year boundaries.
 */
public class Test {
    public static void main(String[] args) throws ContainsException {
        Scanner input = new Scanner(System.in);
        System.out.printf("Input the month: ");
        int day = input.nextInt();
        System.out.printf("Input the day: ");
        int month = input.nextInt();
        System.out.printf("Input the year: ");
        int year = input.nextInt();

        Date date = new Date(month, day, year);

        System.out.println("By preforming date.toNumbers() function, I can get the date in number format: " + date.toNumbers());
        System.out.println("By preforming date.toWords() function, I can get the date in word format: " +date.toWords());
        date.reverseDay();
        System.out.println("By preforming date.reverseDay() function, we go back a day: " + date.toWords());
        date.advanceDay();
        System.out.println("By preforming date.advanceDay() function, we go ahead a day: " + date.toWords());
        System.out.println("I can also get the day: " + date.getDay());
        System.out.println("I can also get the month: " + date.getMonth());
        System.out.println("And I can also get the year: " + date.getYear());

        Date advanceMonth = new Date(1, 31, 2016);
        System.out.println("Here we show advancing over a month! The current month is: " + advanceMonth.toWords());
        advanceMonth.advanceDay();
        System.out.println("After advancing the day, the new month is: " + advanceMonth.toWords());

        Date reduceMonth = new Date(2, 1, 2016);
        System.out.println("Here we show reducing over a month! The current month is: " + reduceMonth.toWords());
        reduceMonth.reverseDay();
        System.out.println("After reversing the day, the new month is: " + reduceMonth.toWords());

        Date advanceYear = new Date(12, 31, 2016);
        System.out.println("Here we show advancing over a year! The current year is: " + advanceYear.toWords());
        advanceYear.advanceDay();
        System.out.println("After advancing the day, the new year is: " + advanceYear.toWords());

        Date reverseYear = new Date(1, 1, 2016);
        System.out.println("Here we show reducing over a year! The current year is: " + reverseYear.toWords());
        reverseYear.reverseDay();
        System.out.println("After revering the day, the new year is: " + reverseYear.toWords());

        System.out.println("Finally, why not try inputting an incorrect date? Watch what happens!\n");

        System.out.printf("Input the month, day, and year: ");
        Date date1 = new Date(input.nextInt(), input.nextInt(), input.nextInt());
    }
}

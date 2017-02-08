/**
 * Created by Sydney on 7/12/2016.
 * Design and implement an ADT that represents a calendar date.
 * You can represent a date's month, day, and year as integers
 * (for example, 5/15/2011). Include operations that advance the
 * date by one day and provide two operations to display the date
 * by using either numbers(05/15/2011) or words for the months
 * (May 16, 2011). As an enhancement, include the name of the day.
 * Include an additional operation to reduce the date by one day.
 */
public class Date {
    private int day = 12;
    private int month = 12;
    private int year = 2016;

    private int[] daysInMonth = {31,february(),31,30,31,30,31,31,30,31,30,31};
    private String[] monthName = {"January", "February","March","April","May","June","July",
                         "August","September","October","November","December"};
    private String[] dayName = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};

    public Date(int month, int day, int year)throws ContainsException {
        setDay(day);
        setMonth(month);
        setYear(year);
    }
    private int february() {
        if (year%4 == 0 && (year%100 != 0 || year%400 == 0)) {
            return 29;
        }
        else {
            return 28;
        }
    }
    public void advanceDay() {
        if(day < daysInMonth[month-1]) {
            day = day + 1;
        }
        else {
            if(month == 12) {
                month = 1;
                day = 1;
                year = year + 1;
            }
            else {
                month = month + 1;
                day = 1;
            }
        }
    }
    public void reverseDay() {
        if(day > 1) {
            day = day - 1;
        }
        else {
            if(month == 1) {
                month = 12;
                year = year - 1;
                day = daysInMonth[month-1];
            }
            else {
                month = month - 1;
                day = daysInMonth[month - 1];
            }
        }
    }
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }
    public int getDay(){
        return day;
    }
    /* This ain't working bro. Fix this shit. Halp. */
    private String dayName() {
        int arrayDay = (day + month + year + (year/4) + (year/100) % 4) % 7;
        return dayName[arrayDay];
    }

    private void setYear(int year)throws ContainsException  {
        if (year < 0) {
            throw new ContainsException("You cannot go back that far in time");
        }
        this.year = year;
    }

    private void setMonth(int month)throws ContainsException {
        if (month < 1 || month > 12) {
            throw new ContainsException("Your must enter a valid month");
        }
        this.month = month;
    }

    private void setDay(int day) throws ContainsException {
        if (day < 1 || day > daysInMonth[month-1]) {
            throw new ContainsException("Your must enter a valid day");
        }
        if (month == 2 && (year%4 == 0 && (year%100 != 0 || year%400 == 0)) && day > 29) {
            throw new ContainsException("There are only 29 possible days in this month.");
        }
        else if (month == 2 && day > 28 && year%4 != 0 && (year%100 == 0 || year%400 != 0)){
            throw new ContainsException("There are only 28 possible days in this month as it is not a leap year.");
        }
        this.day = day;
    }

    public String toWords() {
        return "Date{" + dayName() + " " + monthName[month-1] + " " + day + ", " + year + '}';
    }

    public String toNumbers() {
        return "Date{" + day + "/" + month + "/" + year + "}";
    }
}

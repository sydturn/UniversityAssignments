/*
Sydney Turnbull
Assignment 1 Question 2.19
*/
#include <iostream>
using namespace std;

int main() {

    int num1;  // first number to be read from user
    int num2;  // second number to be read from user
    int num3;  // third number

    cout << "Input three different integers:\n";
    cin >> num1 >> num2 >> num3;   // read three integers

    cout << "Sum is " << num1 + num2 + num3 << endl;
    cout << "Average is " << (num1 + num2 + num3) / 3 << endl;
    cout << "Product is " << num1 * num2 * num3 << endl;
    cout << "Smallest is ";
    
    //this doesn't handle duplicates however the numbers inputted are all differnt
    if (num1 < num2 && num3) {
        cout << num1 << endl;
    }
    else if (num2 < num1 && num3) {
        cout << num2 << endl;
    }
    else {
        cout << num3 << endl;
    }
    
    //breaks if duplicate numbers
    cout << "Largest is ";
    if (num1 > num2 && num3) {
        cout << num1 << endl;
    }
    else if (num2 > num1 && num3) {
        cout << num2 << endl;
    }
    else {
        cout << num3 << endl;
    }
}

/*
Sydney Turnbull
*/
#include <iostream>
#include <cstdlib>
#include "Weight.h"
using namespace std;

int main() {

    Weight a, b, c, d{ 12, 1 }, e{ 4, 15 };

    c.setPounds(3);
    c.setOunces(6);
    cout << c.getPounds() << endl;			// output 3
    cout << c.getOunces() << endl;			// output 6

    cout << "Please enter the first weight: ";
    cin >> a;		        // assume the user enters 8lb9oz

    cout << "Please enter the second weight: ";
    cin >> b;		        // assume the user enters 3lb8oz

    cout << a;		        // output 8lb9oz
    cout << b;			  // output 3lb8oz

    c = a + b;		        // test overloaded operator +
    cout << "The sum is " << c << endl;	  //output The sum is 12lb1oz

    if (c == d)
        cout << "The objects are equal" << endl;    //output The objects are equal


    cout << e++;       //output 4lb15oz
    cout << e;         //output 5lb0oz

    cout << static_cast<int>(e) << endl;  //output 5

}
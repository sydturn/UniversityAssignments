/*
Question 13.17 (Printing a Table of ASCII Values)
*/

#include <iostream>
using namespace std;

int main() {
    cout << "Oct Hex  Dec  Char" << endl;

    for (int x = 33; x <= 126; x++) {
        cout << oct << x << "   ";
        cout << hex << x << "   ";
        cout << dec << x << "   ";
        cout << char(x) << endl;
    }
}
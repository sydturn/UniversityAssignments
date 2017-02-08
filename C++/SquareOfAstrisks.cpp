/*
Sydney Turnbull
Assignment 1 Question 4.26
*/
#include <iostream>
using namespace std;

int main() {
    int size{0};
    cout << "Enter the size of the sides of the square: ";
    cin >> size;
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            if (i > 0 && i < size - 1 && j !=0 && j != size-1) {
                cout << " ";
                continue;
            }
            cout << "*";
        }
        cout << "\n";
    }
}
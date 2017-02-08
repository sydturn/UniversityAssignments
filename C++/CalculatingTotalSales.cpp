/*
Sydney Turnbull
Assignment 1 Question 5.17
*/
#include <iostream>
using namespace std;

int main() {
    int productNum{ 1 };
    int quantity{};
    double cost{};
    double sum{};

    while (productNum != 0) {
        cout << "Enter the product number: ";
        cin >> productNum;
        if (productNum == 0) {
            break;
        }
        cout << "Enter the quantity sold: ";
        cin >> quantity;
        cout << endl;

        switch (productNum) {
        case 1:
            cost = 2.98;
            break;
        case 2:
            cost = 4.50;
            break;
        case 3: 
            cost = 9.98;
            break;
        case 4: 
            cost = 4.49;
            break;
        case 5: 
            cost = 6.87;
            break;
        }
        sum += cost*quantity;
    }
    cout << "The total cost comes to $" << sum << endl;
}
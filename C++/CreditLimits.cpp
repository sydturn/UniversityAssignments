/*
Sydney Turnbull
Assignment 1 Question 4.14
*/
#include <iostream>
using namespace std;

int main() {
    int accountNumber{0};
    double balance;
    double charges;
    double credits;
    double creditLimit;
    double newBalance;

    while (accountNumber != -1) {
        cout << "\nEnter the account number (or -1 to quit): ";
        cin >> accountNumber;
        if (accountNumber == -1) {
            break;
        }
        cout << "Enter beginning balance: ";
        cin >> balance;
        cout << "Enter total charges: ";
        cin >> charges;
        cout << "Enter total credits: ";
        cin >> credits;
        cout << "Enter credit limit: ";
        cin >> creditLimit;
        newBalance = balance + charges - credits;
        cout << "New balance is: " << newBalance << endl;
        
        if (newBalance > creditLimit) {
            cout << "Account: " << accountNumber << endl;
            cout << "Credit limit: " << creditLimit << endl;
            cout << "Balance: " << newBalance << endl;
            cout << "Credit Limit Exceeded" << endl;
        }
    }
}
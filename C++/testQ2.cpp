/*
Sydney Turnbull
*/
#include <iostream>
using namespace std;

double calcCost(int vehicleType) {
    double cost{ 0 };
    switch (vehicleType) {
    case 1:
        cost = 4.05;
        break;
    case 2:
        cost = 5.85;
        break;
    case 3:
        cost = 6.10;
        break;
    default:
        cout << "Class type doesn't exist. Program exit." << endl;
        exit(1);
    }
    return cost;
}

int main() {
    int vehicleType{ 0 };
    
    cout << "Enter the clas type of the vehicle: ";
    cin >> vehicleType;

    cout << "The toll charge for your vehicle is: $" << calcCost(vehicleType) << endl;

}
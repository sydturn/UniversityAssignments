/*
Sydney Turnbull
*/
#include <iostream>
using namespace std;

int main() {
    int sum{0};
    int x{};
    //cout << "Input a sequence of integers: " << endl;

    //I'm not sure if you want them to input an initial sequence or if you want a prompt for each input. 
    //if you want an initial sequence entered just comment out line 16 and uncomment line 10. 

    while (x >= 0) {
        cout << "Input an integer: ";
        cin >> x;
        if (x < 0) {
            break;
        }
        if(x % 2 == 0) {
            sum++;
        }
    }
    cout << "The total number of even integers is: " << sum << endl;
}
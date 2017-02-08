/*
Sydney Turnbull
*/
#include <iostream>
#include <cstdlib>
#include <time.h>
using namespace std;

int main() {
   // srand(time(NULL));
    int a = 0;
    int b = 0;
    int c = 0;
    bool same = false;
    int tries = 0;

    while (!same) {
 
        a = (1 + (rand() % 9));
        b = (1 + (rand() % 9));
        c = (1 + (rand() % 9));
        tries++;

        cout << "The test combination is " << a << " " << b << " " << c << " ";

        if (a == 1 && b == 6 && c == 8) {
            cout << "Andy gets to ride his bike after " << tries << " tries!" << endl;
            same = true;
        }
        else {
            cout << "Wrong combination!" << endl;
        }
    }
}
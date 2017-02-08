/*
Sydney Turnbull
*/
#include <iostream>
#include <cstdlib>
#include <time.h>
using namespace std;

void printArray(int x[]) {
    for (int i = 0; i < sizeof(x); i++) {
        cout << x[i] << ", ";
    }
}


int main() {
    srand(time(NULL));
    int testNum[3] = { };
    int a = (1 + (rand() % 9) + 1);
    int b = (1 + (rand() % 9) + 1);
    int c = (1 + (rand() % 9) + 1);
    int properCombo[] = { 1, 6, 8 };
    bool same = false;
    int tries{ 0 };
    int x = 0;

    while (!same) {
        for (int i = 0; i < sizeof(testNum); i++) {
            if (testNum[i] == properCombo[i]) {
                if (i == sizeof(testNum) - 1) {
                    cout << "The test combination is ";
                    printArray(testNum);
                    cout << "Andy gets to ride his bike after " << tries << " tires!" << endl;
                    same = true;
                }
                continue;
            }
            else {
                cout << "The test combination is ";
                printArray(testNum);
                cout << "Wrong combination!" << endl;
                tries++;
                for (int i = 0; i < sizeof(testNum); i++) {
                    testNum[i] =  (1 + (rand() % 9) + 1);
                }
                cin >> x;
                break;
            }
            

        }
    }

    

}
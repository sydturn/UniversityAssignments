/* Design a function template that returns the maximum element from an array. Test using array of int, double, and strings.
*/
#include <iostream>
#include <string>
using namespace std;

template <typename T>
T largest(T array [], int n) {
    T largest{array[0]};
    for (int i = 1; i < n; i++) {
        if (array[i] > largest) {
            largest = array[i];
        }
    }
    return largest;
}

int main() {
    int integers[3] = { 9, 7, 10 };
    double doubles[3] = { 3, 9, 5 };
    string words[3] = { "orange", "apple", "peach" };

    cout << "Integers largest = " << largest(integers, 3) << endl;
    cout << "Double largest = " << largest(doubles, 3) << endl;
    cout << "String largest = " <<  largest(words, 3) << endl;

}
#include <iostream>
#include <fstream>
#include <string>
using namespace std; 

int main() {
    ofstream write;  
    try {
        write.open("data-size.dat");
        write << "char                   " << sizeof(char) << endl
            << "unsigned char          " << sizeof(unsigned char) << endl
            << "short int              " << sizeof(short int) << endl
            << "unsigned short int     " << sizeof(unsigned short int) << endl
            << "int                    " << sizeof(int) << endl
            << "unsigned int           " << sizeof(unsigned int) << endl
            << "long int               " << sizeof(long int) << endl
            << "unsigned long int      " << sizeof(unsigned long int) << endl
            << "float                  " << sizeof(float) << endl
            << "double                 " << sizeof(double) << endl
            << "long double            " << sizeof(long double) << endl;
        write.close();
    }
    catch (exception e) {
        cout << "Something happened(or didn't happen)";
    }

    try {
        string line;
        ifstream read("data-size.dat");
        while (getline(read, line)) {
            cout << line << endl;
        }

    }
    catch (exception e) {
        cout << "I can't open that. Try entering a file name that isn't wrong.";
    }
}
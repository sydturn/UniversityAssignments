#ifndef WEIGHT_WEIGHT_H
#define WEIGHT_WEIGHT_H
#include <iostream>
using namespace std;

class Weight {
    friend ostream &operator<<(ostream&, const Weight&);
    friend istream &operator >> (istream&, Weight&);
public:
    explicit Weight(int = 0, int = 0);// constructor
    void setPounds(int);
    void setOunces(int);
    int getPounds();
    int getOunces();
    Weight operator+(const Weight&) const; //addition
    Weight operator++(int); //incrementation
    bool operator==(const Weight&) const; //equal-to operator
    operator int() const; //not-equal-to operator

private:
    int pounds;
    int ounces; 
};

#endif //WEIGHT_WEIGHT_H

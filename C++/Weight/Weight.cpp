
#include <string>
#include "Weight.h"
using namespace std;

//constructor
Weight::Weight(int pounds, int ounces) {
    if (pounds <= 0 || ounces <= 0) {
        //throw std::invalid_argument("Both pounts and ounces must be greater than 0.");
    }
    if (ounces >= 16) {
        int temp = ounces / 16;
        this->pounds = pounds + temp;
        this->ounces = ounces % 16;
    }
    this->pounds = pounds;
    this->ounces = ounces;
}

void Weight::setPounds(int pounds)
{
    this->pounds = pounds;
}

void Weight::setOunces(int ounces)
{
    this->ounces = ounces % 16;
    this->pounds += ounces / 16;
}

int Weight::getPounds()
{
    return pounds;
}

int Weight::getOunces()
{
    return ounces;
}

//addition operator
Weight Weight::operator+(const Weight& operand2) const {
    int temp = 0;
    if (operand2.ounces + ounces > 16) {
        temp = (operand2.ounces + ounces) / 16;
    }
    return Weight{ pounds + operand2.pounds + temp, (ounces + operand2.ounces) % 16 };
}

//incrementation
Weight Weight::operator++(int)
{
    if (ounces + 1 == 16) {
        return Weight{ pounds + 1, 0 };
    }
    return Weight{ pounds, ounces + 1 };
}

//equal-to operator
bool Weight::operator==(const Weight& operand2) const {
    return (operand2.pounds == pounds || operand2.ounces == ounces);
}

//integer conversion
Weight::operator int() const
{
    return pounds;
}
//read operator (cin)
istream &operator >> (istream &input, Weight &number) {
    input >> number.pounds;
    input.ignore(2);
    input >> number.ounces;
    input.ignore(2);
    return input;
}
//write operator (cout)
ostream &operator<<(ostream &output, const Weight &number)
{
    output << number.pounds<< "lb" << number.ounces << "oz";
    return output;
}


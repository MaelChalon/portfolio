#include <iostream>
using namespace std;

void echange(int&, int&);
int main()
{
	int x = 5, y = 10;
	cout << "x=" << x << " et y=" << y << endl;
	echange(x, y);
	cout << "x=" << x << " et y=" << y << endl;
	return 0;
}
void echange(int& a, int& b)
{
	int tmp;
	cout << "a=" << a << " et b=" << b << endl;
	tmp = a;
	a = b;
	b = tmp;
	cout << "a=" << a << " et b=" << b << endl;
}
#include <iostream>
#include<ctime>
using namespace std;

int algo(int a, int b) {
	int d = b, e = a, c = 1;
	while (d > 0) {
		if (d % 2 == 0) {
			d = d / 2;
		}
		else {
			d = (d - 1) / 2;
			c = c * e;
		}
		e = e * e;
	}
	return c;
}

int main() {
	int a, b, c;
	for (a = 1; a < 11; a++) {
		for (b = 0; b < 6; b++) {
			c = algo(a, b);
			cout << c << endl;
		}
	}
	return 0;
}
/*
premier programme
*/

#include<iostream>
using namespace std;

int main() {
	int a, b,q;
	cout << "entrer les deux entier positif\n";
	cin >> a >> b;
	q = 0;
	while (a >= b) {
		a = a - b;
		q += 1;
	}
	cout << q << "\n" << a;
	return 0;
}
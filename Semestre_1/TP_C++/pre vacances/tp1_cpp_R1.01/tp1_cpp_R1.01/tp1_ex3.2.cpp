/*
premier programme
*/

#include<iostream>
using namespace std;

int main() {
	int n,i,r;
	cout << "entrer un entier\n";
	cin >> n;
	r = 0;
	for (i = 1; i <= n; i += 1) {
		r += i;
		}
	cout << r;
	return 0;
}
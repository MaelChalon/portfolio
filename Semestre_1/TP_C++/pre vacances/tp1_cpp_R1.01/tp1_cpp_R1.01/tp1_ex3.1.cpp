/*
premier programme
*/

#include<iostream>
using namespace std;

int main() {
	int x;
	cout << "entrer un entier \n";
	cin >> x;
	if (x < 0) {
		cout << "votre entier est négatif";
	}
	else {
		if (x == 0) {
			cout << "votre entier est nul";
		}
		else {
			cout << "votre entier est positif";
		}
	}
	return 0;
}
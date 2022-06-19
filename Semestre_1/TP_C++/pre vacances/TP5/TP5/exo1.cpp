#include<iostream>
#include<iomanip>
using namespace std;

bool premier_ente_eux(int x, int y) {
	int i;
	for (i = 2; i <= x; i++) {
		if (x % i == 0 and y % i == 0) {
			return false;
		}
	}
	return true;
}

double racine(int b) {
	double x = 1.0, y=0.0;
	while(abs(x-y)>0.000001) {
		y = x;
		x = 0.5 * (x + (b / x));
	}
	return x;
}

int liste_vers9(int x) {
	int a1, a0;
	if (x < 10 || x>=100 ) {
		cout << "nombre ne possedant pas seulement 2 chiffres ";
		return 0;
	}
	a1 = x / 10;
	a0 = x - (a1 * 10);
	if (a1 == a0) {
		cout << "ce nombre possede deux fois le meme chiffre";
		return 0;
	}
	cout << x << endl;
	int symetrique;
	while (x > 9) {
		a1 = x / 10;
		a0 = x - (a1 * 10);
		symetrique = (a0 * 10) + a1;
		x = abs(x - symetrique);
		cout << x << endl;

	}
	return 0;
}

int main() {
	int x=0;
	int a,b;
	bool c;
	while (x != 4) {
		cout << "faite votre choix en entrant l'un des 4 nombres :" << endl;
		cout << "1. Racine carree" << endl;
		cout << "2. liste vers 9" << endl;
		cout << "3. nombre premier entre eux" << endl;
		cout << "4. quittter" << endl;
		cout << endl;
		cin >> x;
		if (x == 1) {
			cout << "entrer un nombre: ";
			cin >> a;
			cout << racine(a) << endl<<endl;
		}
		if (x == 2) {
			cout << "entrer un entier a 2 chiffres seulement: ";
			cin >> a;
			liste_vers9(a);
			cout << endl;
		}
		if (x == 3) {
			cout << "entrer deux entiers: ";
			cin >> a >> b;
			c = premier_ente_eux(a, b);
			if (c == false) {
				cout << "ils sont premiers entre eux" << endl << endl;
			}
			else {
				cout << "ils ne sont pas premiers entre eux" << endl << endl;
			}
		}
	}
	return 0;
}
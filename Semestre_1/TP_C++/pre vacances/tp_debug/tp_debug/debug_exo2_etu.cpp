#include<iostream>

using namespace std;

int main() {

	int u0, un;
	int i = 0;
	int tpsVol, tpsVolAltitude, altMax, cptr=0;

	tpsVol = 0;
	tpsVolAltitude = 0;
	altMax = 0;

	//saisie de la valeur initiale
	cout << "Donner la valeur initiale: ";
	cin >> u0;
	cout << "u0 = " << u0 << endl;
	altMax = u0;

	//parcours des différents éléments de la suite
	un = u0;
	while (un != 1) {
		/***** partie 1 *****/
		i++;
		if (un % 2 == 0) {
			un = un / 2;
		}
		else {
			un = 3 * un + 1;
		}
		cout << "u" << i << " = " << un << endl;
		/***** partie 2 *****/
		if (un <= u0 && cptr !=1) {
			tpsVolAltitude = i - 1;
			cptr = 1;
		}
		if (un > altMax){
			altMax = un;
		}
	}
	tpsVol = i;

	cout << "Temps de vol: " << tpsVol << endl;
	cout << "Temps de vol en altitude: " << tpsVolAltitude << endl;
	cout << "Altitude max: " << altMax << endl;
	return 0;
}
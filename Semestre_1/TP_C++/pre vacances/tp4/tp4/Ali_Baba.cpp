#include <iostream>
#include<ctime>
using namespace std;

void initialise(int t[], int taille) {
	int i;
	for (i = 0; i < taille; i++) {
		t[i] = 1;
	}
}


void recherche(int T[], int taille, int& indice) {
	int cptr = 0;
	while (cptr < 3) {

		if (T[indice] == 1) {
			cptr++;
		}

		if (cptr < 3) {
			indice++;
			if (indice == taille) {
				indice = 1;
			}
		}
	}
}


int main() {
	const int N = 9;
	int tab[N + 1], cptr = N, indice = 3, i;
	initialise(tab, N + 1);
	while (cptr > 2) {
		tab[indice] = 0;
		cptr = cptr - 1;
		recherche(tab, N + 1, indice);
	}
	for (i = 1; i < N + 1; i++) {
		if (tab[i] == 1) {
			cout << "la personne avec le nombre " << i << " gagne" << endl;
		}
	}
}
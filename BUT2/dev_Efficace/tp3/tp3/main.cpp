#include "complexite.h"

void initTab1D(complexite& c, int taille, int* tab, string nom) {
	for (int i = 0; i < taille; i++) {
		c.ajoutBoucle();
		tab[i] = rand();
		c.ajoutOperationUnitaire();
	}
	c.finComplexite();
	c.toCSV(nom);
}

void initTab2D(complexite& c, int tailleI, int tab[][100], string nom) {
	
	for (int i = 0; i < tailleI; i++) {
		c.ajoutBoucle();
		for (int j = 0; j < tailleI; j++) {
			c.ajoutBoucle();
			tab[i][j] = rand();
			c.ajoutOperationUnitaire();
		}
	}
	c.finComplexite();
	c.toCSV(nom);
}

void diagonalMaticeNonOpti(complexite& c, int tailleI, int tab[][100], string nom) {
	for (int i = 0; i < tailleI; i++) {
		c.ajoutBoucle();
		for (int j = 0; j < tailleI; j++) {
			c.ajoutBoucle();
			if (i == j) {
				tab[i][j] = 1;
				c.ajoutOperationUnitaire();
			}
			c.ajoutTests();
		}
	}
	c.finComplexite();
	c.toCSV(nom);
}

void diagonalMaticeOpti(complexite& c, int tailleI, int tab[][100], string nom) {
	for (int i = 0; i < tailleI; i++) {
		c.ajoutBoucle();
		tab[i][i] = 1;
		c.ajoutOperationUnitaire();
	}
	c.finComplexite();
	c.toCSV(nom);
}

int factorielIteratif(complexite& c, int nb, string nom) {
	int retour = 1;
	c.ajoutOperationUnitaire();
	c.ajoutOperationUnitaire();
	for (int i = 1; i <= nb; i++) {
		retour = retour * i;
		c.ajoutOperationUnitaire();
		c.ajoutOperationUnitaire();
		c.ajoutBoucle();
	}
	c.finComplexite();
	c.toCSV(nom);
	return retour;
}

int factorielRecur(complexite& c, int nb) {
	c.ajoutTests();
	if (nb == 0) {
		return 1;
	}
	else {
		c.ajoutOperationUnitaire();
		c.ajoutOperationUnitaire();
		return factorielRecur(c, nb - 1) * nb;
	}
}

int main(void) {

	complexite comp;

	cout << factorielRecur(comp, 5);
	comp.finComplexite();
	comp.toCSV("factorielRecur.csv");

	return EXIT_SUCCESS;
}
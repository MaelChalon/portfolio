#include "listeChaine.h"

int main(void) {

	listeChaine l;
	l.ajouterDebut(1);
	l.ajouterDebut(1);
	l.ajouterFin(1);
	l.ajouterK(200, 3);
	l.ajouterK(5, 3);
	l.affiche();
	l.supprElement(1);
	cout << endl;
	l.affiche();


	return 0;
}
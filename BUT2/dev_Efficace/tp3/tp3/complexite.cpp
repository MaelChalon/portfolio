#include "complexite.h"

complexite::complexite()
{
	NB_boucle = Nb_operation_unitaires = Nb_appel_fonctions = Nb_tests = 0;
	Time = clock();
}

void complexite::ajoutBoucle()
{
	NB_boucle++;
}

void complexite::ajoutOperationUnitaire()
{
	Nb_operation_unitaires++;
}

void complexite::ajoutAppelFonction()
{
	Nb_appel_fonctions++;
}

void complexite::ajoutTests()
{
	Nb_tests++;
}

void complexite::finComplexite()
{
	Time = clock() - Time;
}

void complexite::toCSV(string nom)
{
	ofstream fichier(nom);
	if (fichier.is_open()) {
		fichier << string("NB_boucle;Nb_operation_unitaires;Nb_appel_fonctions;Nb_tests;Time\n");
		fichier << NB_boucle << ";" << Nb_operation_unitaires << ";"
			<< Nb_appel_fonctions << ";" << Nb_tests << ";" << Time << "\n";
	}
}


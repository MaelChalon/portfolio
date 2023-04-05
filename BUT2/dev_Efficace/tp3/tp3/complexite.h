#pragma once
#include "string"
#include <ctime>
#include <fstream>
#include<iostream>

using namespace std;

class complexite
{
private:
	int NB_boucle, Nb_operation_unitaires,
		Nb_appel_fonctions, Nb_tests;
	clock_t Time;

public:
	complexite();
	void ajoutBoucle();
	void ajoutOperationUnitaire();
	void ajoutAppelFonction();
	void ajoutTests();
	void finComplexite();

	void toCSV(string);
};


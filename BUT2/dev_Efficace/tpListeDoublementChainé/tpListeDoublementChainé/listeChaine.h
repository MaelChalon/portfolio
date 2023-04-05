#pragma once
#include <iostream>

using namespace std;

struct Cell {
	int element;
	Cell* suivant;
	Cell* precedent;
};

class listeChaine
{
private:
	Cell* listeDoublementChaine;

public:
	listeChaine() { listeDoublementChaine = nullptr; };
	~listeChaine() { delete listeDoublementChaine; };
	bool at_begining();
	void begining_of();

	bool at_end();
	void end_of();

	bool liste_vide();

	int longueur_totale();
	int Longueur_fin();
	int longueur_debut();

	void affiche();

	void ajouterDebut(int);
	void ajouterFin(int);
	bool ajouterK(int, int);

	int supprDebut();
	int supprFin();
	int supprK(int);
	int supprCourant();
	void supprElement(int);
};


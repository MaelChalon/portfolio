#include <iostream>
#include<fstream>
using namespace std;

struct Etudiant {
	int annee; //BUT 1, 2 ou 3
	int numero; //numero etudiant unique
	Etudiant* parrain = NULL; // pointe vers son parrain-NULL si pas de parrain
	char nom[30];
	char prenom[30];
};

const int TAILLE = 179;

void charger_liste_etudiants(const char nom_fichier[], Etudiant tab[]) {
	int i = 0;
	ifstream entree(nom_fichier);
	char ligne[100];
	if (!entree) {
		cout << "erreur d'entree";
	}
	else {
		while (!entree.eof() && i<TAILLE) {
			entree.getline(ligne, 100, ';');
			tab[i].numero = atoi(ligne);
			entree.getline(ligne, 100, ';');
			strcpy_s(tab[i].prenom, ligne);
			entree.getline(ligne, 100, ';');
			strcpy_s(tab[i].nom, ligne);
			entree.getline(ligne, 100);
			tab[i].annee = atoi(ligne);
			i++;
		}
		entree.close();
	}
}

void compte(Etudiant tab[], int& a1, int& a2, int& a3) {
	a1 = 0;
	a2= 0;
	a3 = 0;
	for (int i = 0; i < TAILLE; i++) {
		if (tab[i].annee == 1) {
			a1++;
		}
		if (tab[i].annee == 2) {
			a2++;
		}
		if (tab[i].annee == 3) {
			a3++;
		}
	}
}

Etudiant* trouver(Etudiant tab[], int num) {
	for (int i = 0; i < TAILLE; i++) {
		if (tab[i].numero == num) {
			return tab + i;
		}
	}
	return NULL;
}

void ajoute_parrain(Etudiant tab[], char nom_fichier[]) {
	Etudiant* etu_actu, etu_parain;
	ifstream entree(nom_fichier);
	char ligne[100];
	if (!entree) {
		cout << "erreur d'entre";
	}
	else {
		while (!entree.eof()) {
			entree.getline(ligne, 100, ';');
			etu_actu = trouver(tab, atoi(ligne));
			entree.getline(ligne, 100);
			if (strcmp(ligne, "") != 0){
				(*etu_actu).parrain = trouver(tab, atoi(ligne));
			}
		}
	}
}

void affiche_etu(Etudiant& etu) {
	cout << "Prenom: " << etu.prenom << " Nom :" << etu.nom << " Annee :" << etu.annee;
	if (etu.parrain == NULL) {
		cout << " parrain : non";
	}
	else {
		cout << " parrain : oui";
	}
}

void affiche_parrain_1A(Etudiant tab[]) {
	for (int i = 0; i < TAILLE; i++) {
		if (tab[i].annee == 1) {
			if (tab[i].parrain != NULL) {
				cout << tab[i].prenom << ' ' << tab[i].nom << " a pour parrain/marraine " << tab[i].parrain->prenom << ' ' << tab[i].parrain->nom;
				cout << endl;
			}
		}
		
	}
}

void changerParrain(Etudiant& etu1, Etudiant& etu2) {
	Etudiant* tmp = etu1.parrain;
	etu1.parrain = etu2.parrain;
	etu2.parrain = tmp;
}

void affiche_filleuls_2A(Etudiant tab[]) {
	Etudiant* etu_actu;
	int cptr = 0;
	for (int i = 0; i < TAILLE; i++) {
		if (tab[i].annee == 2) {
			etu_actu = tab + i;
			cptr = 0;
			for (int j = 0; j < TAILLE; j++) {
				if (tab[j].parrain == etu_actu) {
					if (cptr == 0) {
						cout << tab[i].prenom << ' ' << tab[i].nom << " a pour filleul.e.s " << tab[j].prenom << ' ' << tab[j].nom;
						cptr++;
					}
					else {
						cout << ' ' << tab[j].prenom << ' ' << tab[j].nom;
					}
				}
			}
			if (cptr == 0) {
				cout << tab[i].prenom << ' ' << tab[i].nom << " n'a pas de filleul.e.";
			}
			cout << endl;
		}
	}
}

void affiche_parrain_meme_annnee(Etudiant tab[]) {

	for (int i = 0; i < TAILLE; i++) {
		if (tab[i].parrain != NULL) {
			if (tab[i].annee == tab[i].parrain->annee) {
				cout << tab[i].nom << ' ' << tab[i]. prenom<< " et " << tab[i].parrain->nom << ' ' << tab[i].parrain->prenom << endl;
			}
		}
	}
}

int parrain_et_filleul(Etudiant tab[]) {
	int cptr = 0;
	Etudiant* etu[TAILLE];
	int j = 0;
	for (int i = 0; i < TAILLE; i++) {
		bool test = false;

		if (tab[i].parrain != NULL) {

			if (tab[i].parrain->parrain != NULL) {

				for (int k = 0; k < TAILLE; k++) {

					if (etu[k] == tab[i].parrain) {

						test = true;
					}
				}
				if (test ==false) {
					cptr++;
					etu[j] = tab[i].parrain;
					j++;
				}

			}
		}
	}
	return cptr;
}

int main() {
	const char nom_fichier[100] = { "liste.csv" };
	char nom_fichier2[100] = { "parrains.csv" };
	Etudiant tab[TAILLE];
	int a1 = 0, a2 = 0, a3 = 0;
	charger_liste_etudiants(nom_fichier, tab);
	ajoute_parrain(tab, nom_fichier2);
	cout<<parrain_et_filleul(tab);

	return 0;
}
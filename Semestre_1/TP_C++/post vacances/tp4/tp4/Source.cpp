#include "TP4.h"
#include <iostream>
using namespace std;

void initHoraire(Horaire& horaire, int h, int m) {
	horaire.h = h;
	horaire.m = m;
}

int horComp(Horaire& h1, Horaire& h2) {
	if (h1.h == h2.h) {
		if (h1.m == h2.m) {
			return 0;
		}
		if (h1.m < h2.m) {
			return -1;
		}
		if (h1.m > h2.m) {
			return 1;
		}
	}
	if (h1.h < h2.h) {
		return -1;
	}
	if (h1.h > h2.h) {
		return 1;
	}
}

void initDate(Date& d, int j, int m, int a) {
	d.jour = j;
	d.mois = m;
	d.annee = a;
}

int dateComp(Date& d1, Date& d2) {
	if (d1.annee == d2.annee) {
		if (d1.mois == d2.mois) {
			if (d1.jour == d2.jour) {
				return 0;
			}
			if (d1.jour > d2.jour) {
				return 1;
			}
			if (d1.jour < d2.jour) {
				return -1;
			}
		}
		if (d1.mois > d2.mois) {
			return 1;
		}

		if (d1.mois < d2.mois) {
			return -1;
		}
	}

	if (d1.annee > d2.annee) {
		return 1;
	}
	if (d1.annee < d2.annee) {
		return -1;
	}
}

void initEvenement(Evenement& e, const char titre[], int jour, int mois, int annee, int heure_debut, int minute_debut, int duree) {
	
	strcpy_s(e.titre, titre);
	initHoraire(e.horaire, heure_debut, minute_debut);
	initDate(e.date, jour, mois, annee);
	e.duree = duree;
}

void initEvenement(Evenement& e, const char titre[], int jour, int mois, int annee, int heure_debut, int minute_debut, int heure_fin, int minute_fin) {
	
	if (heure_fin < heure_debut) {
		cout << "un evenement ne peux pas avoir de duree negative";
	}
	else {
		strcpy_s(e.titre, titre);
		initHoraire(e.horaire, heure_debut, minute_debut);
		initDate(e.date, jour, mois, annee);
		e.duree = (heure_fin * 60 + minute_fin) - (heure_debut * 60 + minute_debut);
	}

}

void afficheEvenement(Evenement& e) {
	cout << e.titre << endl;
	
	if (e.date.jour < 10) {
		if (e.date.mois < 10) {
			cout << "date : 0" << e.date.jour << "/0" << e.date.mois << "/" << e.date.annee << endl;
		}
		else {
			cout << "date : 0" << e.date.jour << "/" << e.date.mois << "/" << e.date.annee << endl;
		}
	}
	else {
		if (e.date.mois < 10) {
			cout << "date : " << e.date.jour << "/0" << e.date.mois << "/" << e.date.annee << endl;
		}
		else {
			cout << "date : " << e.date.jour << "/" << e.date.mois << "/" << e.date.annee << endl;
		}
	}
	cout << "horaire : " << e.horaire.h << "h" << e.horaire.m << endl;
	cout << "duree : " << e.duree << endl;
}

void copieEvenement(Evenement& dest, Evenement& src) {
	dest.duree = src.duree;

	initDate(dest.date, src.date.jour, src.date.mois, src.date.annee);

	initHoraire(dest.horaire, src.horaire.h, src.horaire.m);

	strcpy_s(dest.titre, src.titre);
	
}

bool ajouterEvenement(Evenement agenda[], int&compteur, Evenement&e) {
	int i;

	if (compteur >= TAILLE) {
		cout << "agenda plein" << endl;
		return false;
	}

	if (compteur == 0) {
		copieEvenement(agenda[compteur], e);
		cout << "ajout effectué" << endl;
		compteur++;
		return true;
	}

	for (i = 0; i < compteur; i++) {
		Horaire fin_agenda, fin_evenement;
		if (dateComp(e.date, agenda[i].date) == 0) {
			fin_agenda.h = agenda[i].horaire.h + (agenda[i].horaire.m + agenda[i].duree) / 60;
			fin_agenda.m = (agenda[i].horaire.m + agenda[i].duree) %60;

			fin_evenement.h = e.horaire.h + (e.horaire.m + e.duree) / 60;
			fin_evenement.m = (e.horaire.m + e.duree) % 60;

			if (not(horComp(fin_evenement, agenda[i].horaire) <= 0) && (not(horComp(fin_agenda, e.horaire) >= 0))){
				cout << "plage horaire deja reservee" << endl;
				return false;
			}
		}
	}

	copieEvenement(agenda[compteur], e);
	cout << "ajout effectué" << endl;
	compteur++;
	return true;
	
}

/*int main() {
	
	Evenement agenda[TAILLE];
	int cptr = 0;

	Evenement e1, e2;
	initEvenement(e1, "rdv medecin", 2, 12, 2021, 12, 30, 45);
	copieEvenement(e2, e1);

	afficheEvenement(e2);

	return 0;
}*/

int main() {
	Evenement t[TAILLE];
	Evenement e;
	Date date;
	int n = 0;

	initEvenement(e, "Soiree reveillon", 1, 1, 2017, 1, 0, 9, 0);
	ajouterEvenement(t, n, e);
	initEvenement(e, "RDV medecin", 12, 10, 2016, 8, 0, 120);
	ajouterEvenement(t, n, e);
	initEvenement(e, "TP algo", 12, 11, 2016, 14, 0, 120);
	ajouterEvenement(t, n, e);
	initEvenement(e, "Soutien", 12, 11, 2016, 11, 0, 90);
	ajouterEvenement(t, n, e);
	initEvenement(e, "Resto avec Pauline", 12, 11, 2016, 12, 45, 14, 0);
	ajouterEvenement(t, n, e);
	initEvenement(e, "CM Reseau", 11, 12, 2016, 11, 0, 90);
	ajouterEvenement(t, n, e);


	//ne doit pas s'ajouter (recouvrement avec rdv medecin)
	initEvenement(e, "erreur3", 12, 10, 2016, 9, 0, 30);
	ajouterEvenement(t, n, e);
	//ne doit pas s'ajouter (recouvrement avec rdv medecin)
	initEvenement(e, "erreur4", 12, 10, 2016, 9, 0, 120);
	ajouterEvenement(t, n, e);
	//ne doit pas s'ajouter (recouvrement avec rdv medecin)
	initEvenement(e, "erreur5", 12, 10, 2016, 7, 0, 120);
	ajouterEvenement(t, n, e);
	//ne doit pas s'ajouter (recouvrement avec rdv medecin)
	initEvenement(e, "erreur6", 12, 10, 2016, 7, 0, 240);
	ajouterEvenement(t, n, e);

	initEvenement(e, "Resto avec Alain", 12, 8, 2017, 12, 45, 14, 0);
	ajouterEvenement(t, n, e);
	initEvenement(e, "Repas grand maman", 1, 1, 2017, 12, 0, 110);
	ajouterEvenement(t, n, e);


	return 0;
}
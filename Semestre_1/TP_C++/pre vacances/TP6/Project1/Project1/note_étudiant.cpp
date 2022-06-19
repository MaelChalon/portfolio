#include<iostream>
#include<ctime>
#include "TP6_notes.h"
using namespace std;

const int NBetu = 25, NBnote = 5;
const int TAILLE = NBetu * NBnote;


void init_alea(float t[], int taille) {
	int i;
	float x;
	for (i = 0; i < taille; i++) {
		x = rand() % 2001;
		x = x / 100.0;
		t[i] = x;
	}
}

void affiche(int n, float t[], int taille) {
	int i;
	cout << "l etudiant numeros " << n << " a eu: ";
	for (i = n-1; i < taille; i = i + NBetu) {
		cout<< t[i] << " ";
	}
}

float meilleur_note(int n, float t[], int taille) {
	int i;
	float max = 0.0;
	for (i = n - 1; i < taille; i = i + NBetu) {
		if (max < t[i]) {
			max = t[i];
		}
	}
	return max;
}

int numerosControle_note_mauvaise(int n, float t[], int taille) {
	int i, cptr=0, indice=0;
	float min = 21.0;
	for (i = n - 1; i < taille; i = i + NBetu) {
		cptr++;
		if (min > t[i]) {
			min = t[i];
			indice = cptr;
		}
	}
	return indice;
}

float moyenne_ameliore(int n, float t[], int taille) {
	int mauvaise, i, cptr=0;
	float moyenne=0.0;
	mauvaise = numerosControle_note_mauvaise(n, t, taille);
	for (i = n - 1; i < taille; i = i + NBetu) {
		cptr++;
		if (mauvaise != cptr) {
			moyenne = moyenne + t[i];
		}
	}
	moyenne = moyenne / ((float)NBnote-1);
	return moyenne;
}

void moyenne_eleve(float t[], int taille) {
	int i;
	for (i = 0; i < taille; i++) {
		t[i] = moyenne_ameliore(i+1, Notes_bis, TAILLE);
	}
}

void moyenne_controle(float t[], int taille) {
	int i, j;
	float moyenne = 0.0;
	for (i = 0; i < taille; i++) {
		moyenne = 0.0;
		for (j = 0; j < NBetu; j++) {
			moyenne = moyenne + Notes_bis[i * (NBetu) + j];
		}
		moyenne = moyenne / (float)NBetu;
		t[i] = moyenne;
	}
}

int nb_plus_moyenne(int n) {
	float tab1[NBnote];
	int cptr=0, i, notePlus=0;
	moyenne_controle(tab1, NBnote);
	for (i = n - 1; i < TAILLE; i = i + 25) {
		if (Notes_bis[i] > tab1[cptr]) {
			notePlus++;
		}
		cptr++;
	}
	return notePlus;
}

float ecart_note(int n) {
	int i;
	float ecart, min = 21.0, max=0.0, ind_min, ind_max;
	for (i = 0; i < NBetu; i++) {
		if (Notes_bis[((n-1) * NBetu) + i]<min) {
			min = Notes_bis[((n-1) * NBetu) + i];
		}
		if (Notes_bis[((n-1) * NBetu) + i] > max) {
			max = Notes_bis[((n - 1) * NBetu) + i];
		}
	}
	ecart = max - min;
	return ecart;
}

int plus_gd_ecart() {
	float gd_ecart= ecart_note(1);
	int controle, i;
	for (i = 1; i <= NBnote; i++) {
		if (gd_ecart < ecart_note(i)) {
			gd_ecart = ecart_note(i);
			controle = i;
		}
	}
	return controle;
}

float recherche_note(int controle, int etu) {
	return Notes_bis[(controle - 1) * NBetu + etu - 1];
}

bool etu_valide(int n) {
	float moyenne_amelio = moyenne_ameliore(n, Notes_bis, TAILLE),
		ind_min = numerosControle_note_mauvaise(n, Notes_bis, TAILLE),
		min;

	if (moyenne_amelio < 10.0) {
		return false;
	}
	else {
		min = recherche_note(ind_min, n);
		if (min < 4) {
			return false;
		}
	}
	return true;
}

void liste_valide() {
	bool t[NBetu];
	int i;
	for (i = 0; i < NBetu; i++) {
		t[i] = etu_valide(i + 1);
	}
	for (i = 0; i < NBetu; i++) {
		if (t[i] == true) {
			cout << i + 1 << '\t';
		}
	}
	cout << endl << endl;
}

int main() {
	int i, ind_min, n,notePlus, gd_ecart;
	float moy, max, m1[NBetu], m2[NBnote];
	cout << "entrer un numero d'etudiant: ";
	cin >> n;
	/*while (n != 0) {
		if (n < 26) {
			ind_min = numerosControle_note_mauvaise(n, Notes_bis, TAILLE);
			max = meilleur_note(n, Notes_bis, TAILLE);
			moy = moyenne_ameliore(n, Notes_bis, TAILLE);
			cout << "sa note max est: " << max << ", lors du controle " << ind_min << " il a eu sa pire note, la moyenne ameliore est: " << moy << endl;
		}
		else {
			cout << "numero d'etudiant superieur a 25" << endl;
		}
		cout << "entrer un numero d'etudiant: ";
		cin >> n;
	}
	moyenne_eleve(m1, NBetu);
	for (i = 0; i < NBetu; i++) {
		cout << m1[i] << endl;
	}
	cout << endl << endl << endl;
	moyenne_controle(m2, NBnote);
	for (i = 0; i < NBnote; i++) {
		cout << m2[i] << endl;
	}
	notePlus = nb_plus_moyenne(25);
	cout << notePlus;

	gd_ecart = plus_gd_ecart();
	cout << gd_ecart;*/
	cout << "les etudiants ayant valide leur module sont: " << endl;
	liste_valide();
	
	return 0;
}

#include<iostream>

using namespace std;

//ajoute 2 à toutes les valeurs du tableau
void modifierTableau(int t[], int taille) {
	for (int i = 0; i < taille; ++i) {
		t[i] += 2;
	}
}

void afficherTableau(int t[], int taille) {
	for (int i = 0; i < taille; ++i) {
		cout << t[i] << " , ";
	}
	cout << endl;
}

float somme(int t[], int taille) {
	float somme = 0.0;
	for (int i = 0; i < taille; ++i) {
		somme += t[i];
	}
	return somme;
}

float moyenne(int t[], int taille) {
	float s = somme(t, taille);
	float m = s / taille;
	return m;
}

int main() {
	const int N = 5;
	int v, tab[N];
	float moy, sum;

	cout << "TP de debugage, ";
	cout << "tutoriel" << endl;
	
	//on remplit le tableau
	for (int i = 0; i < N; ++i) {
		v = 44 * pow(-1, i) + i;
		tab[i] = v;
	}
	//on affiche le tableau
	afficherTableau(tab, N);

	//on modifie le tableau
	modifierTableau(tab, N);
	//on affiche le tableau
	afficherTableau(tab, N);

	//on calcule la moyenne des éléments
	moy = moyenne(tab, N);
	cout << "Moyenne=" << moy << endl;

	//test: 2 appels à une fonction
	sum = somme(tab, 2) + moyenne(tab, N);
	cout << sum << endl;

	return 0;
}
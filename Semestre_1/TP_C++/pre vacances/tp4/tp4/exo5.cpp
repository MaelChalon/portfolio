#include <iostream>
#include<ctime>
using namespace std;


void affiche(int t[], int taille) {
	int i;
	for (i = 0; i < taille; i++) {
		cout << "t[" << i << "]= " << t[i] << endl;
	}
}

void init_alea(int t[], int taille) {
	int i;
	srand(time(NULL));
	for (i = 0; i < taille; i++) {
		t[i] = (rand()%401)-200;
	}
}

int indice_max(int t[], int taille) {
	int ind_max = 0, max = t[0], i;
	for (i = 0; i < taille; i++) {
		if (max < t[i]) {
			max = t[i];
			ind_max = i;
		}
	}
	return ind_max;
}

int main() {
	const int N = 20;
	int tab[N], ind_max;
	init_alea(tab, N);
	affiche(tab, N);
	ind_max = indice_max(tab, N);
	cout << "l'indice du maximum est: " << ind_max << " et le maximum vaut " << tab[ind_max] << endl;
	return 0;
}
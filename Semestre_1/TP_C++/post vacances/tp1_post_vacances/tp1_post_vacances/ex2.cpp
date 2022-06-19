#include<iostream>
#include<cstring>
#include <algorithm>
using namespace std;

const int N = 3;

void init_carre(int carre[N][N]) {
	int i, j, x;
	bool deja_fournis[N * N + 1];

	for (i = 0; i <= N * N; i++) {
		deja_fournis[i] = false;
	}

	cout << "nous allons initialiser le carre magique: " << endl;
	for (i = 0; i < N; i++) {
		for (j = 0; j < N; j++) {
			cout << "case " << i << ", " << j << " = ";
			cin >> x;
			while (x > N * N || deja_fournis[x] == true) {
				if (x > N * N) {
					cout << "valeur trop grande pour le carre" << endl;
					cout << "case " << i << ", " << j << " = ";
					cin >> x;
				}
				if (deja_fournis[x] == true) {
					cout << "valeur deja fournis" << endl;
					cout << "case " << i << ", " << j << " = ";
					cin >> x;
				}
			}
			cout << endl;
			carre[i][j] = x;
			deja_fournis[x] = true;
		}
	}
}

void affiche(int carre[N][N]) {
	cout << "le carre est de la forme: " << endl;
	int i, j;
	for (i = 0; i < N; i++) {
		for (j = 0; j < N; j++) {
			cout << carre[i][j] << " ";
		}
		cout << endl;
	}
}

bool presque_magique(int carre[N][N]) {
	int i, j;
	int ref = 0, colone=0, ligne=0, diago1=0, diago2=0;
	for (i = 0; i < N; i++) {
		ref = ref + carre[0][i];
	}
	for (i = 0; i < N; i++) {
		for (j = 0; j < N; j++) {
			ligne = ligne + carre[i][j];
			colone = colone + carre[j][i];
			if (i == j) {
				diago1 = diago1 + carre[i][j];
			}
			if (i + j == N - 1) {
				diago2 = diago2 + carre[i][j];
			}
		}
		if (ligne != ref || colone != ref) {
			return false;
		}
		else {
			ligne=0;
			colone = 0;
		}
	}
	if (diago1 != ref || diago2 != ref) {
		return false;
	}
	else {
		return true;
	}
}

bool est_magique(int carre[N][N]) {
	int nb[N * N], i, j;

	if (presque_magique(carre) == false) {
		return false;
	}
	
	for (i = 0; i < N*N; i++) {
		nb[i] = 0;
	}

	for (i = 0; i < N; i++) {
		for (j = 0; j < N; j++) {
			if (carre[i][j] > N * N) {
				return false;
			}
			nb[carre[i][j]-1]++;
		}
	}

	for (i = 0; i < N*N; i++) {
		if (nb[i] == 0) {
			return false;
		}
	}

	return true;
}

void auto_magique(int carre[N][N]) {
	if (N % 2 == 0) {
		cout << "la construction du carre ne fonctionne qu'avec un ordre impaire";
	}
	else {
		int k = (N - 1) / 2, l = 0, c = 0, i;
		l = k + 2;
		c = k + 1;
		carre[l - 1][c - 1] = 1;

		for (i = 1; i < N * N; i++) {
			
			if (i % N == 0) {
				l = 1 + ((l + 1) % N);
			}
			else {
				l = 1 + (l % N);
				c = 1 + (c % N);
			}
			carre[l-1][c-1] = i+1;
		}
	}
}

bool est_carre_latin(int carre[N][N]) {
	int i, j;
	bool collone[N], ligne[N];
	for (i = 0; i < N; i++) {
		collone[i] = false;
		ligne[i] = false;
	}

	for (i = 0; i < N; i++) {
		for (j = 0; j < N; j++) {
			if (collone[carre[j][i]-1] == true) {
				return false;
			}
			else {
				collone[carre[j][i]-1] = true;
			}
			if (ligne[carre[i][j]-1] == true) {
				return false;
			}
			else {
				ligne[carre[i][j]-1] = true;
			}
		}
		for (j = 0; j < N; j++) {
			collone[j] = false;
			ligne[j] = false;
		}
	}
	return true;
}

int main() {
	
	int carre[N][N]={{1,2,3}, {1,2,3}, {1,2,3}};
	int x[N] = { 1,2,3 };
	/*affiche(carre);*/
	int i, j, k, l,cptr=0, cptr2=0;
	while (next_permutation(carre[0], carre[0] + 3)) {
		while (next_permutation(carre[1], carre[1] + 3)) {
			while (next_permutation(carre[2], carre[2] + 3)) {
				cout << cptr2 << endl;
				if (est_carre_latin(carre)) {
					cptr++;
				}
				affiche(carre);
			}
		}
	}


	cout << cptr << endl;

	/*cout << est_carre_latin(carre) << endl;
	cout << est_magique(carre) << endl;*/
	return 0;
}
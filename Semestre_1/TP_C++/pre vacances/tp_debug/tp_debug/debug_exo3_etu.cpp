#include<iostream>

using namespace std;

const int N = 100; //nombre à calculer
int prems[N]; //liste des nombres premiers <N
int nbPrems = 0; //nombre de valeurs dans le tableau prems
bool eratosthene[N]; //permet le calcul de prems
int mult[N]; //liste des multiples de p et q dans le calcul M(p,q,N)
int nbMult = 0; //nombre de valeurs dans le tableau mult

//calcule le min entre 2 valeurs
int min(int a, int b) {
	if (a > b)
		return b;
	return a;
}

//calcule le max entre 2 valeurs
int max(int a, int b) {
	if (a < b)
		return b;
	return a;
}

//permet de calculer les nombres premiers <N
void calculEratosthene() {
	for (int i = 0; i < N; ++i) {
		eratosthene[i] = true;
	}
	for (int j, i = 2; i < N; ++i) {
		if (eratosthene[i]) {
			for (j = 2 * i; j < N; j += i) {
				eratosthene[j] = false;
			}
		}
	}
	nbPrems = 0;
	for (int i = 2; i < N; ++i) {
		if (eratosthene[i]) {
			prems[nbPrems] = i;
		}
	}
}

/* Détermine si un nombre N est divisible uniquement par 2 nombres premiers */
bool nbreDivisible(int N) {
	int cpt = 0;
	for (int i = 0; i <= nbPrems; ++i) {
		if (N % prems[i] == 0) {
			++cpt;
		}
	}
	return cpt == 2;
}

/* Calcule M(p,q,N) */
int M(int p, int q, int N) {
	//1- on cherche les valeurs divisibles par p et q (donc les multiples de p et q, inférieurs à N)

	int maxi = max(p, q);
	int mini = min(p, q);
	for (int i = maxi; i <= N; i = i + maxi) {
		if (i % mini == 0) {
			mult[nbMult++] = i;
		}
	}
	//2- pour chacune de ces valeurs, on regarde s'il y a d'autres diviseurs
	//on sait que chaque valeur de mult[] est divisible par p et q
	//on les parcourt du plus grand au plus petit car on cherche le max
	for (int i = nbMult; i >= 0; --i) {
		if (nbreDivisible(mult[i])) {
			return mult[i]; //on retourne le nombre si OK (seulement p et q comme diviseurs)
		}
	}
	return 0; //si aucun nombre ne convient
}

/* Calcule S(N) */
unsigned long S(int N) {
	unsigned long somme = 0;
	//on prend chaque couple de nombres premiers
	for (int p = 0; p < nbPrems; ++p) {
		cout << "en cours..." << (p + 1) << "/" << nbPrems << endl; //message pour voir l'avancée de l'algo
		for (int q = p + 1; q < nbPrems; ++q) {
			somme = somme + M(prems[p], prems[q], N);
		}
	}
	return somme;
}

int main() {

	calculEratosthene();
	cout << "S(" << N << ") = " << S(N) << endl;

	return 0;
}
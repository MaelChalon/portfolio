#include <iostream>
using namespace std;

void affiche(int t[], int taille) {
	int i;
	for (i = 0; i < taille; i++) {
		cout << "t[" << i << "]= " << t[i] << endl;
	}
}

void initialise(int t[], int taille) {
	int i;
	for (i = 0; i < taille; i++) {
		t[i] = i + 1;
	}
}
int main() {
	const int N = 10;
	int tab[N];
	initialise(tab, N);
	affiche(tab, N);
	retur n
}
#include<iostream>
#include<iomanip>
#include<math.h>
using namespace std;

const int N = 4;

int taille(int n) {
	int cptr = 1;
	while (n >= 10) {
		n = n / 10;
		cptr++;
	}
	return cptr;
}

void codage(int n, int tab[], int& longueur) {
	int i,a;
	a = taille(n);
	for (i = 0; i < a; i++) {
		tab[i] = n / pow(10, a - 1 - i);
		n = n - tab[i]*pow(10, a - 1 - i);
	}
	longueur = taille(n);
}

int main() {
	int tab[N], x=0, i;
	codage(145, tab, x);
	for (i = 0; i < N; i++) {
		cout << tab[i]<< endl;
	}
	return 0;
}
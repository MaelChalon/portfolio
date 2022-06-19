#include<iostream>
using namespace std;

int t[30] = { 21,-432,-77,84,91,74,45,899,301,2,-1,-987,-456,87,745,79,-4,-56,754,32,145,156,54,-2,-89,963,71,-222,-333,7 };

void trie(int tab[],int taille) {
	int min = tab[1], ind_min = 0;
	for (int i = 0; i < taille; i++) {
		min = tab[i];
		for (int j = i; j < taille; j++) {
			if (tab[j] < min) {
				min = tab[j];
				ind_min = j;
			}
		}
		tab[ind_min] = tab[i];
		tab[i] = min;
	}
}

int dichotomie(int tab[], int taille ,int ind_min, int ind_max, int nb) {
	
	int mid = (ind_max + ind_min) / 2;
	if (taille == 1) {
		if (nb == tab[mid]) {
			return mid;
		}
		else {
			return -1;
		}
	}

	if (nb == tab[mid]){
		return mid;
	}
	else {
		if (nb > tab[mid]) {
			taille = ind_max - mid;
			return dichotomie(tab, taille, mid, ind_max, nb);
		}
		else {
			taille = mid - ind_min;
			return dichotomie(tab, taille, ind_min, mid, nb);
		}
	}
}

int main() {
	trie(t, 30);
	cout << dichotomie(t, 30, 0, 30, -987);
	return 0;


}
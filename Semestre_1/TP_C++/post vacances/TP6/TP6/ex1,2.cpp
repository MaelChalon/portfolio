#include <iostream>
using namespace std;

void compte(char ch[], int& voy, int& cons) {
	int i = 0;
	voy = 0;
	cons = 0;
	int espace = 0;
	char liste_voy[12] = { 'a','e','i','o','u','y','A','E','I','O','U','Y' };
	while (ch[i] != '\0') {
		for (int j = 0; j < 12; j++) {
			if (ch[i] == liste_voy[j]) {
				voy++;
			}
			
		}
		if (ch[i] == ' ') {
			espace++;
		}
		i++;
	}
	cons = (strlen(ch) - voy) - espace;
	cout << voy << '\t' << cons;
}

void compte2(char ch[], int* voy, int* cons) {
	int i = 0;
	*voy = 0;
	*cons = 0;
	int espace = 0;
	char liste_voy[12] = { 'a','e','i','o','u','y','A','E','I','O','U','Y' };
	while (ch[i] != '\0') {
		for (int j = 0; j < 12; j++) {
			if (ch[i] == liste_voy[j]) {
				*voy+=1;
			}

		}
		if (ch[i] == ' ') {
			espace++;
		}
		i++;
	}
	*cons = (strlen(ch) - *voy) - espace;
	cout << *voy << '\t' << *cons;
}

void max_min(int* tab, int taille) {
	int i = 0;
	int min = *tab, max = *tab;
	while (i < taille) {
		if (*(tab + i) > max) {
			max = *(tab + i);
		}
		if (*(tab + i) < min) {
			min = *(tab + i);
		}
		i++;
	}
	cout << min << '\t' << max;
}



int main() {
	int tab[5] = { 1,5,6,17,3 };
	max_min(tab, 5);
	
	return 0;
}
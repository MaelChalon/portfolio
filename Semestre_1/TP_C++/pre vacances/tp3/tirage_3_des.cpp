#include<iostream>
#include<ctime>

using namespace std;

int main() {
	const int NB_tirages = 60000;
	const int NB_face = 6;
	
	int tirage[3 * NB_tirages], somme[NB_tirages], frequences[(NB_face * 3) + 1], ind, i, j, arondit[(NB_face * 3) + 1],max=0, max2;

	float x = 0.0;

	srand(time(NULL));

	for (i = 0; i < NB_tirages; i++) {
		tirage[3*i] = (rand() % NB_face) + 1;
		tirage[3*i+1] = (rand() % NB_face) + 1;
		tirage[3*i+2] = (rand() % NB_face) + 1;
	 }

	for (i = 0; i < NB_tirages; i++) {
		somme[i] = tirage[3*i] + tirage[3*i + 1] + tirage[3*i + 2];
	}

	for (i = 0; i < (3*NB_face) + 1; i++) {
		frequences[i] = 0;
	}

	for (i = 0; i < NB_tirages; i++) {
		ind = somme[i];
		frequences[ind] = frequences[ind] + 1;
	}
	for (i = 0; i < 3*NB_face + 1; i++) {
		x = (float(frequences[i]) / float(NB_tirages)) * 100;
		cout << "Nombre de " << i << " tires: " << frequences[i] << " soit " << x << "%\n";
	}

	for (i = 0; i < 3 * NB_face + 1; i++) {
		x = (float(frequences[i]) / float(NB_tirages)) * 100;
		arondit[i] = int(x);
		
		if (max<int(x)) {
			max = int(x);
			max2 = max;
		}

		cout << i << ": ";
		
		for (j = 0; j<int(x); j++) {
			cout << "*";
		}

		cout << endl;
	}
	
	for (i = 0; i < max2+1; i++) {
		for (j = 3; j < 3 * NB_face + 1;j++) {
			if (i < max2) {
				if (arondit[j] < max) {
					cout << "   ";
				}
				else {
					cout << " * ";
				}
			}
			else {
				if (j < 10) {
					cout << " " << j << " ";
				}
				else {
					cout << j << " ";
				}
			}
		}
		cout << endl;
		max = max - 1;;
	}

	return 0;
}
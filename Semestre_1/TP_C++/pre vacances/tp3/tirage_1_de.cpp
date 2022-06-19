#include<iostream>
#include<ctime>

using namespace std;



int main() {
	const int NB_tirages = 1000;
	const int NB_face = 12;

	int tab[NB_tirages], i, frequence[NB_face+1],ind;
	
	float x;
	
	srand(time(NULL));
	
	for (i = 0; i < NB_tirages; i++) {
		tab[i] = (rand() % NB_face) + 1;
	}
	
	for (i = 0; i < NB_face + 1; i++) {
		frequence[i] = 0;
	}
	
	for (i = 0; i < NB_tirages; i++) {
		ind = tab[i];
		frequence[ind] = frequence[ind] + 1;
	}
	
	for (i = 1; i < NB_face + 1; i++) {
		x = (float(frequence[i])/float(NB_tirages))*100;
		cout << "Nombre de " << i << " tires: " << frequence[i] << " soit " << x << "%\n";
	}
}
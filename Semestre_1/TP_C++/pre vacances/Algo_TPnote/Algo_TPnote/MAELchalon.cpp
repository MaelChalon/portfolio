#include<iostream>
#include"data.h"
using namespace std;

int min() {
	int i, mini=scores33[0];

	for (i = 0; i < 4 * N33; i++) {
		if (mini > scores33[i]) {
			mini = scores33[i];
		}
	}
	return mini;
}

float moyenne(int nbsup){
	int i, cptr=0;
	float moy = 0.0;
	for (i = 0; i < 4 * N33; i++) {
		if (scores33[i] >= nbsup) {
			moy = moy + scores33[i];
			cptr++;
		}
	}
	moy = moy / (float)cptr;
	return moy;
}

void SupInfEgale(int val, int& inf, int& sup, int& egale) {
	int i;
	inf = 0; sup = 0; egale = 0;
	for (i = 0; i < 4 * N33; i++) {
		if (scores33[i] < val) {
			inf++;
		}
		else {
			if (val == scores33[i]) {
				egale++;
			}
			else {
				sup++;
			}
		}
	}
}

void scoreMatch(int nbMatch) {
	int equipe1 = 0, equipe2 = 0;
	cout << endl;
	cout << "match n " << nbMatch << ": " << equipes33[2 * nbMatch] << " - " << equipes33[2 * nbMatch + 1] << endl;
	cout << "Score premiere mi-temps: " << scores33[4 * nbMatch] << " - " << scores33[4 * nbMatch + 1] << endl;
	cout << "Score deuxieme mi-temps: "<< scores33[4 * nbMatch+2] << " - " << scores33[4 * nbMatch + 3] << endl;
	equipe1 = scores33[4 * nbMatch] + scores33[4 * nbMatch + 2];
	equipe2 = scores33[4 * nbMatch + 1] + scores33[4 * nbMatch + 3];
	cout << "Score final: " << equipe1 << " - " << equipe2<< endl;
	if (equipe1 > equipe2) {
		cout << "victoire de l'equipe " << equipes33[2 * nbMatch];
	}
	else {
		cout << "victoire de l'equipe " << equipes33[2 * nbMatch+1];
	}
	cout << endl;
}

int chercheMatch(char equipe, int tab[]) {
	int i,cptr=0;
	for (i = 0; i < 2 * N33; i++) {
		tab[i] = 0;
	}
	for (i = 0; i < 2*N33; i++) {
		if (equipes33[i] == equipe) {
			if (i % 2 == 0) {
				tab[i / 2] = 1;
				cptr++;
			}
			else {
				tab[i/2]=2;
				cptr++;
			}
			
		}
	}
	return cptr;
}


float scoremoy(char equipe) {
	int match[2 * N33], nbMatch=0, i;
	float moy = 0.0;
	nbMatch = chercheMatch(equipe, match);
	for (i = 0; i < 2*N33; i++) {
		if (match[i] == 1) {
			moy = moy + scores33[4 * i] + scores33[4 * i + 2];
		}
		else {
			if (match[i] == 2) {
				moy = moy + scores33[4 * i+1] + scores33[4 * i + 3];
			}
		}
	}
	moy = moy / (float)nbMatch;
	return moy;
}

int remonte() {
	int i, cptr=0;
	for (i = 0; i < N33; i++) {
		if (scores33[4*i]>scores33[4*i+1]){
			if (scores33[4 * i + 1] + scores33[4 * i + 3] > scores33[4 * i] + scores33[4 * i + 2]) {
				cptr++;
			}
		}
		else {
			if (scores33[4 * i + 1] > scores33[4 * i]) {
				if (scores33[4 * i] + scores33[4 * i + 2] > scores33[4 * i + 1] + scores33[4 * i + 3]) {
					cptr++;
				}
			}
		}
	}
	return cptr;
}

float ratio(char equipe) {
	int match[N33], nbMatch;
	nbMatch=chercheMatch(equipe, match);
	int i;
	float encaisse = 0.0, marque = 0.0, ratio=0.0;
	for (i = 0; i < nbMatch; i++) {
		if (match[i] == 1) {
			marque = marque + scores33[4 * i] + scores33[4 * i + 2];
			encaisse = encaisse + scores33[4 * i + 1] + scores33[4 * i + 3];
		}
		else {
			if (match[i] == 2) {
				encaisse = marque + scores33[4 * i] + scores33[4 * i + 2];
				marque = encaisse + scores33[4 * i + 1] + scores33[4 * i + 3];
			}
		}
	}
	ratio = marque / encaisse;
	return ratio;

}

int main() {

	int mini = min(), sup = 0, inf=0,egale=0;
	float moy = moyenne(35), moy2;
	cout << mini << endl << moy << endl;
	SupInfEgale(25, inf, sup, egale);
	cout << inf << '\t' << egale << '\t' << sup << endl;

	scoreMatch(0);
	scoreMatch(4);

	moy2 = scoremoy('A');
	cout << endl << moy2;

	int remontada;
	remontada = remonte();
	cout << endl << remontada;
	
	float meilleurratio=0.0;
	int i;
	for (i = 0; i < 26; i++) {
		if (ratio('A' + i) > meilleurratio) {
			meilleurratio = ratio('A' + i);
		}
	}
	cout << meilleurratio;

	return 0;
}
#include <ctime>
#include <iostream>
#include<cstring>
#include<fstream>
using namespace std;


using namespace std;

const int N = 25, NB_coup_max = 7;
char mot_trouve[N];
char mot_construit[N];

void creation() {
	int taille = strlen(mot_trouve), i;
	for (i = 0; i < taille; i++) {
		mot_construit[i] = '_';
	}
	mot_construit[taille] = '\0';
}

void affiche(char ch[]) {
	int i;
	for (i = 0; ch[i] != '\0'; i++) {
		cout << ch[i] << ' ';
	}
}

void mot_hasard() {
	int mot = rand() % 27827, i = 0;
	ifstream entree("DicFra.csv", ios::in);
	char ligne[10000];

	if (!entree) {
		cout << "erreur d'ouverture \n";
	}


	while (i < mot - 1) {
		entree.getline(ligne, 100000);
		i++;
	}

	entree.getline(ligne, 1000, ',');
	entree.close();

	for (i = 1; ligne[i] != '"'; i++) {
		mot_trouve[i - 1] = ligne[i];
	}

}


void jouer(char lettre, int& compteur) {

	int i;
	bool test = false;

	for (i = 0; mot_trouve[i] != '\0'; i++) {
		if (mot_trouve[i] == lettre) {
			mot_construit[i] = lettre;
			test = true;
		}
	}
	if (test == false) {
		compteur--;
	}
}

void affichage_pendu(int i) {
	int j;
	cout << '\t' << "PENDU: ";
	for (j = 0; j < i; j++) {
		cout << "*";
	}
}

bool gagner() {
	if (strcmp(mot_trouve, mot_construit) == 0) {
		return true;
	}
	else {
		return false;
	}
}

int main() {

	srand(time(NULL));
	mot_hasard();
	int taille = strlen(mot_trouve), i, cptr = NB_coup_max;
	
	char continuer = 'o';

	creation();

	while (cptr > 0 && continuer == 'o') {
		cout << "Quel lettre ? ";
		char l;
		cin >> l;
		jouer(l, cptr);
		affiche(mot_construit);
		affichage_pendu(cptr);
		cout << endl;
		if (gagner() == true) {
			cout << "gagne ! entrer 'o' pour recommencer ou 'n' pour arreter : ";
			cin >> continuer;
			cptr = 7;
			mot_hasard();
		}
		if (cptr == 0) {
			affiche(mot_trouve);
			cout <<endl<< "perdu ! entrer 'o' pour recommencer ou 'n' pour arreter : ";
			cin >> continuer;
			cptr = 7;
			
			mot_hasard();
		}
	}


	return 0;

}

/**/
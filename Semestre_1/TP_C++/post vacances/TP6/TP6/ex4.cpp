#include <iostream>
#include<fstream>
using namespace std;


bool lettre_diff(char ch[]) {
	bool tab[26] = { false };
	int i = 0;
	while (ch[i] != '\0') {
		if (tab[ch[i] - 'a'] == true) {
			return false;
		}
		else {
			tab[ch[i] - 'a'] = true;
		}
		i++;
	}
	return true;
}

void plus_long_lettre_diff(char nom_fichier[]) {
	ifstream entree(nom_fichier);
	char ligne[100];
	if (!entree) {
		cout << "erreur d'entree";
	}
	else {
		while (!entree.eof()) {
			entree.getline(ligne, 1000);
			cout << ligne;
		}
	}
}

int main() {
	char nom_fichier[50] = { "DicFra.csv" };
	plus_long_lettre_diff(nom_fichier);
	return 0;
}
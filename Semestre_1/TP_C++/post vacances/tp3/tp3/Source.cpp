#include<iostream>
#include<cstring>
#include<fstream>
using namespace std;

const int N = 36678;

struct commune {
	int num_dep;
	char nom[50];
	char code_post[200];
};

void init(commune& c, int dept, const char n[], const char code[]) {
	c.num_dep = dept;
	strcpy_s(c.code_post, code);
	strcpy_s(c.nom, n);
}

void affiche(commune& c) {
	if (c.num_dep<10){
		cout << "departement: 0" << c.num_dep << " nom: " << c.nom << " code postale: " << c.code_post << endl;
	}
	else {
		cout << "departement: " << c.num_dep << " nom: " << c.nom << " code postale: " << c.code_post << endl;
	}
}

int compte_commune(const char nom_fichier[]) {
	int cpt = 0;
	ifstream entree(nom_fichier, ios::in);
	char ligne[1000];
	if (!entree)
		cout << "Probleme d'ouverture \n";
	else {
		while (!entree.eof()) {
			entree.getline(ligne, 1000);
			cpt++;
		}
		entree.close();
	}
	return cpt;
}

void importer(const char nom_fichier[], commune tab[], int taille) {
	int i = 0, cptr = 0;
	int nb;
	char nom[50], code[200];
	ifstream entree(nom_fichier, ios::in);
	char ligne[1000];
	if (!entree)
		cout << "Probleme d'ouverture \n";
	else {
		while (!entree.eof() && cptr<taille) {
				
			nb=atoi (ligne);
			entree.getline(ligne, 50, ';');
			strcpy_s(nom,50, ligne);
			entree.getline(ligne, 200);
			strcpy_s(code, 200, ligne);
			init(tab[cptr], nb, nom, code);
			cptr++;
		}
		entree.close();
	}
}

void affichetab(commune tab[], int taille) {
	int i;
	for (i = 0; i < taille; i++) {
		affiche(tab[i]);
	}
}

void rechercheCP(commune tab[], int taille) {
	char recherche[50];
	int i = 0, cptr = 0;
	cout << "entrer le nom de la ville que vous cherchez: ";
	cin >> recherche;
	while(i<taille){
		if (strcmp(recherche, tab[i].nom) == 0) {
			affiche(tab[i]);
			cout << endl;
			cptr++;
		}
		i++;
	}
	if (cptr == 0) {
		cout << "la ville renseignee ne se trouve pas dans le tableau";
	}
}

void permute(commune& c1, commune& c2) {
	commune tmp;
	strcpy_s(tmp.nom, c1.nom);
	strcpy_s(tmp.code_post, c1.code_post);
	tmp.num_dep = c1.num_dep;

	strcpy_s(c1.nom, c2.nom);
	strcpy_s(c1.code_post, c2.code_post);
	c1.num_dep = c2.num_dep;

	strcpy_s(c2.nom, tmp.nom);
	strcpy_s(c2.code_post, tmp.code_post);
	c2.num_dep = tmp.num_dep;
}

void trie_alpha(commune tab[], int taille) {
	int i, j, ind_max=0;
	for (i = 0; i < taille; i++) {
		for (j = 0; j < taille - i; j++) {
			if (strcmp(tab[ind_max].nom, tab[j].nom) < 0) {
				ind_max = j;
			}
		}
		permute(tab[ind_max], tab[j-1]);
	}
}

int plus_commune(commune tab[], int taille) {
	int i, max = 0, dept_actu = 0, dept=1 ,cptr = 1;
	for (i = 0; i < taille; i++) {
		if (tab[i].num_dep != dept_actu) {
			if (cptr > max) {
				max = cptr;
				dept = dept_actu;
			}
			cptr = 1;
			dept_actu = tab[i].num_dep;
		}
		else {
			cptr++;
		}
	}
	return dept;
}

void meme_nom(commune tab[], int taille) {
	int i, j;
	char tmp[50];
	bool meme_nom_bool[N] = { false };

	for (i = 0; i < taille; i++) {
		strcpy_s(tmp,50, tab[i].nom);
		for (j = i ; j < taille; j++) {
			if (strcmp(tmp, tab[j].nom) == 0) {
				meme_nom_bool[i] == true;
				meme_nom_bool[j] == true;
			}
		}
	}
	for (i = 0; i < taille; i++) {
		if (meme_nom_bool[i] == true) {
			affiche(tab[i]);
		}
	}

}

int main() {
	char fichier[20] = { "villesCopie.csv" };
	commune tab[N];
	importer(fichier, tab, N);

	return 0;
}
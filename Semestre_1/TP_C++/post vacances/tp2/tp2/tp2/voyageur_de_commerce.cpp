#include <ctime>
#include <iostream>
#include<cstring>
#include<fstream>
using namespace std;

const int N = 20;
int dist[N + 1][N + 1];
char NomVille[(N + 1) * 30];
int departVC = 1;


void affiche(char ch[]) {
	int i;
	for (i = 0; ch[i] != '\0'; i++) {
		cout << ch[i] << ' ';
	}
}

void initialise_ville() {

	int i=0, j;

	for (j = 0; j < (N + 1) * 30; j++) {
		NomVille[j] = '_';
	}

	ifstream entree("villes.txt", ios::in);
	char ligne[100];

	if (!entree) {
		cout << "erreur d'ouverture \n";
	}
	else {
		while (i <=20) {
			entree.getline(ligne, 100);
			for (j = 0; ligne[j] != '\0'; j++) {
				if (ligne[j] - 'A' >= 0) {
					NomVille[(i * 30) + j-2] = ligne[j];
				}
			}
			i++;
		}
	}
}



int main() {
	int departEtape, arriveEtape;
	bool dejaVisite[N + 1] = { false };
	int Etape[N + 1];
	int LgCumulee[N + 1];

	int i;
	initialise_ville();
	for (i = 0; i < (N + 1) * 30; i++) {
		cout << NomVille[i];
	}
	return 0;

}
#include <iostream>
#include<ctime>
using namespace std;

const int NB_CARTES=32;
const char couleur[4] = { 'P', 'C', 'K', 'T' }, nombres[8] = { '7', '8', '9', 'X', 'V', 'D', 'R', '1' };
/*----------------------------------------------------------------*/
void initjeu(int jeu[]) /* remplit le tableau avec les 32 cartes */
{
	int i;
	
	for (i = 0; i < NB_CARTES; i++) {
		jeu[i] = i;
	}
}
/*----------------------------------------------------------------*/
void permute(int& carte1, int& carte2) /* permute 2 cartes */
{
	int tmp = carte1;
	carte1 = carte2;
	carte2 = tmp;
}
/*----------------------------------------------------------------*/
void melange(int jeu[]) /* melange les cartes */
{	
	int carte1, carte2,i;
	for (i = 0; i < 100; i++) {
		carte1 = rand() % NB_CARTES;
		carte2 = rand() % NB_CARTES;
		permute(jeu[carte1], jeu[carte2]);
	}
}
/*----------------------------------------------------------------*/
void affiche_carte(int x) /* affiche une carte (valeur,couleur)*/
{
	cout << nombres[x % 8] << couleur[(int)x / 8];
}

/*----------------------------------------------------------------*/
void affiche(int jeu[],int n) /* affiche N cartes */
{
	int i, x;
	for (i = 0; i < n; i++) {
		x = jeu[i];
		affiche_carte(x);
		cout << ' ';
	}
	cout << endl;
}
/*----------------------------------------------------------------*/
void distribution(int jeu[], int jeu1[], int jeu2[])/* distribue les cartes aux deux joueurs */
{
	int i,cptr_jeu1=0, cptr_jeu2=0;
 	for (i = 0; i < NB_CARTES; i++) {
		if (i % 2 == 0) {
			jeu1[cptr_jeu1] = jeu[i];
			cptr_jeu1++;
		}
		else {
			jeu2[cptr_jeu2] = jeu[i];
			cptr_jeu2++;
		}
	}
}
/*----------------------------------------------------------------*/
void jouer1coup( int jeu1[], int jeu2[], int& n1, int& n2) /* gere le resultat d'une bataille (1 coup) */
{
	int joueur1[NB_CARTES], joueur2[NB_CARTES], i, x=0,j;
	joueur1[0] = jeu1[0];
	joueur2[0] = jeu2[0];

	while(n1 >= 0 && n2 >= 0 && (joueur1[x] % 8 == joueur2[x] % 8)){
		x++;
		joueur2[x] = jeu2[0];
		joueur1[x] = jeu1[0];
		n1--;
		n2--;
		for (i = 0; i < n1 + 1; i++) {
			jeu1[i] = jeu1[i + 1];
		}
		for (i = 0; i < n2 + 1; i++) {
			jeu2[i] = jeu2[i + 1];
		}
	}
	
	for (i = 0; i < n1 + 1; i++) {
		jeu1[i] = jeu1[i + 1];
	}
	for (i = 0; i < n2 + 1; i++) {
		jeu2[i] = jeu2[i + 1];
	}

	/*if (joueur1 / 8 < joueur2 / 8) {
		jeu2[n2] = joueur2;
		jeu2[n2 + 1] = joueur1;
		n2=n2+1;
		n1 = n1 - 1;
	}
	else {
		jeu1[n1] = joueur1;
		jeu1[n1 + 1] = joueur2;
		n1=n1+1;
		n2 = n2 - 1;
	}*/

	if (joueur1[x] % 8 < joueur2[x] % 8) {
		for (i = 0; i <= x; i++) {
			jeu2[n2] = joueur2[i];
			jeu2[n2 + 1] = joueur1[i];
			n2 = n2 + 1;
			n1 = n1 - 1;
		}
	}
	else {
		for (i = 0; i <= x; i++) {
			jeu1[n1] = joueur1[i];
			jeu1[n1 + 1] = joueur2[i];
			n1 = n1 + 1;
			n2 = n2 - 1;
		}
	}
}
/*----------------------------------------------------------------*/
int main(){
	srand(time(NULL));
	int jeu[NB_CARTES]; /*ensemble de toutes les cartes*/
	int jeu1[NB_CARTES]; /* cartes du joueur 1 */
	int jeu2[NB_CARTES]; /*cartes du joueur 2*/
	int n1=15; /* indice de la derniere carte joueur 1 */
	int n2=15; /* indice de la derniere carte joueur 2 */
	int i, cptr = 0, cptr_total=0;
	
	//remplissage du tableau jeu avec toutes les cartes
	initjeu(jeu);
	
	//mélange des cartes
	melange(jeu);
	affiche(jeu, NB_CARTES);
	//distribution des cartes dans les tableaux jeu1 et jeu2
	distribution(jeu, jeu1, jeu2);
	affiche(jeu1, n1+1);
	affiche(jeu2, n2+1);
	cout << endl;
	//affichage des deux jeux pour tester vos premières fonctions
	/* tant que la partie n'est pas terminee */
	
	cptr = 0;
	n1 = 15;
	n2 = 15;
	while (n1 != 31 && n2 != 31) {
		jouer1coup(jeu1, jeu2, n1, n2);
		cptr++;
		affiche(jeu1, n1+1);
		affiche(jeu2, n2+1);
		cout << endl;
	}
	
	/* afficher qui gagne et en combien de coups */
	if (n1 == 31) {
		cout << "le joueur 1 gagne en: " << cptr << " coups" << endl;
		affiche(jeu1, NB_CARTES);
	}
	else {
		cout << "le joueur 2 gagne en: " << cptr << " coups" << endl;
		affiche(jeu2, NB_CARTES);
	}
	

	return 0;
}
/*for (i = 0; i < 1000; i++) {
	cptr_total += cptr;
}*/
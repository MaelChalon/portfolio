#include<iostream>
#include<ctime>

using namespace std;

int main() {
	int x, y, cptr = 1, nb_partie=1, nb_essaie;
	char rejouer;
	rejouer = 'O';
	srand(time(NULL));
	while (rejouer == 'O') {
		x = rand() % 1001;
		cout << "partie " << nb_partie << " entrez le nombre d'essaie maximum:";
		cin >> nb_essaie;
		cout<< "entrer un entier entre 0 et 1000\n";
		cin >> y;
		while (y != x && cptr <= nb_essaie) {
			
			if (y > x) {
				cout << "trop grand\n" << "entrer un nouvel entier\n";
				cin >> y;
				cptr++;
			}
			else {
				cout << "trop petit\n" << "entrer un nouvel entier\n";
				cin >> y;
				cptr++;
			}
		}
		if (x == y) {
			cout << "gagne, vous avez trouve en " << cptr << " coup(s)\n";
			nb_partie++;
		}
		if (cptr > nb_essaie) {
			cout << "perdu! vous avez depasse le nombre d'essaie autorise\n";
			nb_partie++;
		}
		cout << "voulez vous rejouer ? (O/N): ";
		cin >> rejouer;
	}
	return 0;
}
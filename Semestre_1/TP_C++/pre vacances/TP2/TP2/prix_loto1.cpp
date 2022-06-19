#include<iostream>
#include<ctime>

using namespace std;

int main() {
	int x, y, cptr=0;
	srand( time ( NULL ) );
	x = rand()%1001;
	cout << "entrer un entier entre 0 et 1000\n";
	cin >> y;
	while (y != x) {
		
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
	cout << "gagne, vous avez trouve en "<< cptr <<  " coup(s)";
	return 0;
}
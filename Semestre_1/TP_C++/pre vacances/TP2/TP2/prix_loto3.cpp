#include<iostream>
#include<ctime>

using namespace std;

int main() {
	int tab[20], x, y, cptr, nb_partie = 0, avant = 500, i;
	srand(time(NULL));
	while (nb_partie <= 20) {
		x = rand() % 1001;
		cptr = 1;
		y = 500;
		avant = 500;
		while (y != x) {

			if (y > x) {
				y = y + avant / 2;
				avant = avant / 2;
				cptr++;
			}
			else {
				y = y - avant / 2;
				avant = avant / 2;
				cptr++;
			}
		}
		tab[nb_partie] = cptr;
		if (x == y) {
			nb_partie++;
		}
	}
	for (i = 0; i < 20; i++) {
		cout << tab[i];
	}
	return 0;
}
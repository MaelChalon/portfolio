#include<iostream>
#include<ctime>

using namespace std;

int main() {
	int tab[1000], i, val_max,max,min;
	float moy;
	moy = 0.0;
	cout << "entrer une valeur maximal superieur a 5000\n";
	cin >> val_max;
	srand(time(NULL));
	for (i = 0; i < 1000; i++) {
		tab[i] = rand() % (val_max+1);
	}
	max = tab[0];
	min = tab[0];
	for (i = 0; i < 1000; i++) {
		moy += tab[i];
		if (tab[i] < min) {
			min = tab[i];
		}
		if (tab[i] > max) {
			max = tab[i];
		}
	}
	moy = moy / 1000.0;
	cout << moy << "\n"<<min<<"\n"<<max;
	return 0;
}
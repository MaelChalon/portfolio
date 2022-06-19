#include<iostream>
#include<ctime>

using namespace std;

int main() {
	int tab[100], i, max_1,max_2,ind_max1,ind_max2;
	max_1 = 0;
	ind_max1 = 0;
	max_2 = 0;
	ind_max2 = 0;
	srand(time(NULL));

	for (i = 0; i < 100; i++) {
		tab[i] = rand();
	}

	for (i = 0; i < 100; i++) {
		if (tab[i] > max_1) {
			max_2 = max_1;
			ind_max2 = ind_max1;
			max_1 = tab[i];
			ind_max1 = i;
			
		}
		if (tab[i] > max_2 && i != ind_max1) {
			max_2 = tab[i];
			ind_max2 = i;
		}
	}
	cout << "le premier maximum: " << max_1 << " et son indice: " << ind_max1 << endl;
	cout << "le deuxieme maximum: " << max_2 << " et son indice: " << ind_max2 << endl;
	return 0;
}

#include<iostream>
using namespace std;

int cptr = 0;

int pgcd(int a, int b, int& c) {
	if (b == 0) {
		return a;
	}
	else {
		c++;
		return pgcd(b, a % b, c);
	}
}



int main() {
	int c = 0;
	int tab[245] = { 0 };
	for (int i = 1; i < 245; i++) {
		c = 0;
		pgcd(244, i, c);
		tab[i] = c;
	}
	int max = 0, ind_max = 0;
	for (int i = 0; i < 245; i++) {
		if (tab[i] >= max) {
			max = tab[i];
			ind_max = i;
		}
		cout << i << '\t' << tab[i] << endl;
	}
	cout << max << '\t' << ind_max;
	return 0;
}
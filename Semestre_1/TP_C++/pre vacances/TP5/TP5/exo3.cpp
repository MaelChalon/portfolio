#include<iostream>
#include<iomanip>
using namespace std;

const int max_taille = 100;

int triangle_pascale(int x) {
	if (x >= max_taille) {
		cout << "nombre de ligne trop important";
		return 0;
	}
	int i, j, t[max_taille], t2[max_taille];
	t[0] = 0;
	t[1] = 1;
	for (i = 2; i < x+1; i++) {
		t[i] = 0;
	}
	for (i = 1; i < x+1; i++) {
		if (t[i] != 0) {
			cout << t[i] << "\t";
		}
	}
	cout << endl;
	t2[0] = 0;
	for (i = 2 ; i <x+1 ;i++) {
		for (j = 1; j < x+1; j++) {
			t2[j] = t[j] + t[j - 1];
			if (t2[j] != 0) {
				cout << t2[j] << "\t";
			}
		}
		cout << endl;
		for (j = 1; j < x+1; j++) {
			t[j] = t2[j];
		}
	}
	return 0;
}

int main() {
	triangle_pascale(20);
	return 0;
}
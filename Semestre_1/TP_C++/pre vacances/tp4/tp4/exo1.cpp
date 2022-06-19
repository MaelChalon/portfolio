#include<iostream>
using namespace std;

int maxi(int x, int y) {
	if (x < y) {
		return y;
	}
	else {
		return x;
	}
}

int moyenne(int x, int y) {
	float moy = 0.0;
	moy = (x + y) / 2;
	return moy;
}

int main() {
	int x, y;
	float moy = 0.0;
	cout << "entrer 2 entiers\n";
	cin >> x >> y;
	moy = moyenne(x, y);
	cout << moy;
	return 0;
}
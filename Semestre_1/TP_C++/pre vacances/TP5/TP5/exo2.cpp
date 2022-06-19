#include<iostream>
#include<iomanip>
#include "TP5_data.h"
using namespace std;

void compte(int x, int& inf, int& sup) {
	int i, plus = 0, moins = 0;
	for (i = 0; i < N; i++) {
		if (T[i] > x) {
			sup++;
		}
		if (T[i]<x){
			inf++;
		}
	}
}

float moyenne() {
	int i;
	float moy = 0.0;
	for (i = 0; i < N; i++) {
		moy = moy + T[i];
	}
	moy = moy / (float)N;
	return moy;
}


int main() {
	int a=0, b=0;
	compte(44, a, b);
	cout << "moins: " << a << " plus: " << b << endl;
	cout << "moyenne: " << moyenne();
	return 0;
}
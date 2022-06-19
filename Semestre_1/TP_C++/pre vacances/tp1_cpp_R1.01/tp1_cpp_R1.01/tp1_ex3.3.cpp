/*
premier programme
*/

#include<iostream>
using namespace std;

int main() {
	int A, B;
	float r;
	cout << "entrer les parametre A puis B de la fonction\n";
	cin >> A >> B;
	r = 0.0;
	r = float(-(B)) / float(A);
	cout << r;
	return 0;
}	

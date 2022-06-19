#include <iostream>
using namespace std;

int calculDouble(int);
int main() {
	int n = 5;
	cout << &n << endl;
	cout << "n avant l'appel de calculDouble : " << n << endl;
	n = calculDouble(n);
	cout << "n apres l'appel de calculDouble : " << n << endl;
	return 0;
}
int calculDouble(int x) {
	cout << &x << endl;
	cout << "Valeur de x a l'entree de la fonction calculDouble : " << x << endl;
	x = x * 2;
	cout << "Valeur de x a la sortie de la fonction calculDouble : " << x << endl;
	return x;
}
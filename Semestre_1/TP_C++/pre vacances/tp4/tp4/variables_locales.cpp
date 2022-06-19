#include <iostream>
using namespace std;

void fct();
int main() {
	int a = 3;
	cout << "a=" << a << endl ;
	fct();
	return 0;
}
void fct() {
	int a = 45;
	cout << "affichage de a:" << a << endl;
}
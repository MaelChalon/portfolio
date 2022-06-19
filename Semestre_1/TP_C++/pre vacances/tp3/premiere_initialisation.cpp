#include<iostream>
#include<ctime>

using namespace std;

int main() {
	int tab[20], i;
	for (i = 0; i < 20; i++) {
		tab[i] = 2 * (i *i) - i + 1;
	}
	for (i = 0; i < 20; i++) {
		cout << "f(" << i << ")=" << tab[i] << "\n";
	}
	return 0;
}
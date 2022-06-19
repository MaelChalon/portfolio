#include<iostream>

using namespace std;

int main() {
	int n, i, j;
	char c;
	cout << "entrer un caractere parmis a, b, c, d puis un entier\n";
	cin >> c >> n;
	if (c >= 'a' && c <= 'd') {
		if (c == 'a') {
			for (i = 0; i < n; i++) {
				cout << "*";
			}
		}
		if (c == 'b') {
			for (i = 0; i < n; i++) {
				for (j = 0; j < n; j++) {
						cout << "*";
				}
				cout << "\n";
			}
		}
		
		if (c == 'c') {
			for (i = 0; i < n; i++) {
				for (j = 0; j < n; j++) {
					if (i==0 || i == n - 1){
						cout << "*";
					}
					else {
						if (j > 0 && j < n - 1) {
							cout << " ";
						}
						else {
							cout << "*";
						}
					}
				}
				cout << "\n";
			}
		}
		if (c == 'd') {
			for (i = 0; i < n; i++) {
				if (i % 2 == 0) {
					cout << "*";
				}
				else {
					cout << "_";
				}
			}
		}
	}
	else {
		cout << "vous avez entre un mauvais caractere";
	}
	return 0;
}
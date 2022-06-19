#include<iostream>
using namespace std;
const int S = 14;
const int N = 4;
const int piece[N + 1] = { 0,1,2,5,10 };
int tab[N + 1][S + 1] = { 0 };

int M(int s, int v) {
	if (s == 0) {
		return 0;
	}
	if (v == 1) {
		return s;
	}

	if (s >= piece[v]) {
		return min(M(s,v-1), 1+M(s-piece[v], v));
	}
	else {
		return M(s, v - 1);
	}
}

void M_iteratif(int s, int v) {
	for (int i = 1; i < N + 1; i++) {
		for (int j = 1; j < S + 1; j++) {
			if (piece[i] == 1) {
				tab[i][j] = j;
			}
			else {
				if (j > piece[i]) {
					tab[i][j] = min(tab[i - 1][j], 1 + tab[i][j - piece[i]]);
				}
				if (j == piece[i]) {
					tab[i][j] = 1;
				}
				if (j < piece[i]) {
					tab[i][j] = tab[i - 1][j];
				}
			}
		}
	}
}

int main() {
	M_iteratif(S, N);
	for (int i = 0; i < N + 1; i++) {
		for (int j = 0; j < S + 1; j++) {
			cout << tab[i][j] << '\t';
		}
		cout << endl;
	}
	cout << tab[N][S];
	return 0;
}


/*return tab[N][S];*/
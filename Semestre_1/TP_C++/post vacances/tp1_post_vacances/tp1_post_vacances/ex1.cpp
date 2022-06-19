#include<iostream>
#include<cstring>
using namespace std;

char lettre_max(char ch[]) {

	int nbfois[26];
	int x = strlen(ch), i, max = 0, ind_max = 0;
	for (i = 0; i < 26; i++) {
		nbfois[i] = 0;
	}
	for (i = 0; i < x; i++) {
		if (ch[i] != ' ') {
			nbfois[ch[i] - 'a']++;
		}
	}
	for (i = 0; i < 26; i++) {
		if (nbfois[i] > max) {
			ind_max = i;
			max = nbfois[i];
		}
	}
	return 'a' + ind_max;
}



int main() {
	char ch[] = "bonjour tout le monde";
	cout << lettre_max(ch);
	return 0;
}
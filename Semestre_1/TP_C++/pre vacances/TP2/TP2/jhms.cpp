#include<iostream>

using namespace std;

int main(){
	
	int j = 0, h = 0, m = 0, s = 0, x;
	cout << "entrer un nombre de secondes\n";
	cin >> x;
	j = x / (3600 * 24);
	x = x % (3600 * 24);

	h = x / 3600;
	x = x % 3600;

	m = x / 60;
	s = x % 60;

	cout << "cette duree correspond a\n" << j << " jour(s)\n"
		<< h << " heure(s)\n" << m << " minute(s)\n" 
		<< s << " seconde(s)";
	return 0;
}
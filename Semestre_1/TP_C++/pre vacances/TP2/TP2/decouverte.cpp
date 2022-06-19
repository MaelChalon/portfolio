#include<iostream>
#include<ctime>

using namespace std;

int main() {
	int x, i;
	srand( time ( NULL ) );
	for (i = 1; i <= 5; i++) {
		x = rand()%51;
		cout << "x=" << x << endl;
	}
	return 0;
}
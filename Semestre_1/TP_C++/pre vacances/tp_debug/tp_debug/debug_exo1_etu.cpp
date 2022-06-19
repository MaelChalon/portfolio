#include <iostream>
using namespace std;

int main() {
    const int N = 8;
    int i;
    int somme = 0;
    float moyenne;
    cout << "Nous allons calculer la moyenne des nombres de 1 a " << N << endl;
    for (i = 1; i <= N; i++) {
        somme = somme + i;
        cout << i << " , " << somme << endl;
    }
    moyenne = somme / (float)N;
    cout << "Moyenne= " << moyenne << endl;
    return 0;
}
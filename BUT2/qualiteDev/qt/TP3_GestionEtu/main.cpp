#include "TP3_GestionEtu.h"
#include "promotion.h"
#include <QApplication>
#include "viewList.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);

    // Create Promotion
    Promotion* promo = new Promotion();
    promo->chargeList_Students("../data/promoDUT.csv");
    promo->affiche();

    // Create Interface
    TP3_GestionEtu w(promo);
    w.show();

    return a.exec();
}

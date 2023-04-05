#pragma once
#include <qstring.h>

/**
 * @brief cette classe permet de stocker les informations essentielle d'un étudiant
*/
class Student
{
private:

    QString bac, nom, prenom, num, dep;

public: 
    Student();
    Student(QString, QString, QString, QString, QString);

    void setNum(QString);
    QString getNum();
    void setDep(QString);
    QString getDep();
    void setBac(QString);
    QString getBac();
    void setNom(QString);
    QString getNom();
    void setPrenom(QString);
    QString getPrenom();
    QString affiche();

};


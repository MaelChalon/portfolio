#pragma once
#include "student.h"
#include <qvector.h>
#include "observer.h"
#include <qstring.h>
#include <qstringlist.h>



/**
 * @brief cette classe permet de créer une liste de d'étudiants
*/
class Promotion : public Observable
{
private:

	QVector<Student> list_students;

public:
	Promotion();
	void chargeList_Students(QString);
	void affiche();
	Student getEtudiant(int);
	int sizePromo();

	void addObserver(Observer* observer) override;
	void removeObserver(Observer* observer)override;
	void notifyObserver() const override;

	void remove(Student);
	void remove(QString);
	Student find(QString);
	void add(Student);
	
};


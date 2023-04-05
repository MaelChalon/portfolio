#include "promotion.h"
#include <qfile.h>
#include <qdebug.h>

Promotion::Promotion()
{

}

/**
 * @brief cette méthode permet de charger une liste d'étudiant dans la liste détudiant de promotion depuis un fichier
 * @param path ce parametre correspond au chemin d'acces vers le fichier à lire.
*/
void Promotion::chargeList_Students(QString path)
{
	QString line;
	QFile myfile(path);

	if (myfile.open(QIODevice::ReadOnly)) {
		QTextStream content(&myfile);
		while (!content.atEnd()) {

			line = content.readLine();
			QStringList list = line.split(";");
			
			list_students.append(Student(list[0], list[1], list[2], list[3], list[4]));
		}
	}
	myfile.close();
}

/**
 * @brief cette méthode affiche la liste d'étudiant dans la console
*/
void Promotion::affiche()
{

	for (int i = 0; i < list_students.size(); i++) {
		qDebug() << list_students[i].affiche();
	}

	qDebug() << list_students.size();
}

/**
 * @brief cette methode renvoie un étudiant de la promotion
 * @param i ce parametre corespond à l'indice dans la liste
 * @return renvoie un Student
*/
Student Promotion::getEtudiant(int i)
{
	return list_students[i];
}

int Promotion::sizePromo()
{
	return this->list_students.size();
}

void Promotion::addObserver(Observer* observer)
{
}

void Promotion::removeObserver(Observer* observer)
{
}

void Promotion::notifyObserver() const
{
}

void Promotion::remove(Student Stu)
{
}

void Promotion::remove(QString Stu)
{
	for (int i = 0; i < list_students.size(); i++) {
		if (list_students[i].getNum() == Stu) {
			this->list_students.remove(i);
		}
	}
}

Student Promotion::find(QString newStudent)
{
	return Student();
}

void Promotion::add(Student newStudent)
{
}


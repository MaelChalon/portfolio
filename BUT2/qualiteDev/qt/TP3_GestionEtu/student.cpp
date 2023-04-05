#include "student.h"

Student::Student()
{
}

Student::Student(QString n, QString no, QString p, QString d, QString b)
{
	this->num = n;
	this->nom = no;
	this->prenom = p;
	this->dep = d;
	this->bac = b;
}


/**
 * @brief cette methode permet de modifier la valeur du numero d'un étudiant
 * @param n ce parametre correspond au numero d'étudiant a modifier
*/
void Student::setNum(QString n)
{
	this->num = n;
}

/**
 * @brief cette métode permet de renvoyer le numero d'un étudiant
 * @return le retour de cette méthode est un int
*/
QString Student::getNum()
{
	return this->num;
}

/**
 * @brief cette méthode permet de modifier le departement d'un étidiant
 * @param d ce parametre correspond au numero de département a modifier
*/
void Student::setDep(QString d)
{
	this->dep = d;
}

/**
 * @brief cette mthode permet de récuperer le numero de département d'un étudiant
 * @return le retour de cette méthode est un int
*/
QString Student::getDep()
{
	return this->dep;
}

/**
 * @brief cette méthode permet de modifier le bac de l'étudiant
 * @param b ce parametre correspond au bac de l'étudiant a modifier
*/
void Student::setBac(QString b)
{
	this->bac = b;
}

/**
 * @brief cette méthode permet de récuperer le bac d'un étudiant
 * @return le retour de cette méthode est un QString
*/
QString Student::getBac()
{
	return this->bac;
}

/**
 * @brief cette méthode permet de modifier le nom de l'étudiant
 * @param n ce parametre correspond au nom a modifier
*/
void Student::setNom(QString n)
{
	this->nom = n;
}

/**
 * @brief cette methode permet de récuperer le nom d'un étudiant
 * @return cette méthode retourn un QString
*/
QString Student::getNom()
{
	return this->nom;
}

/**
 * @brief cette methode permet de modifier le prenom de l'étudiant
 * @param p ce parametre correspond au prenom a modifier
*/
void Student::setPrenom(QString p)
{
	this->prenom = p;
}

/**
 * @brief cette methode permet de recuperer le prenom d'un éudiant
 * @return cette méthode retourne un QString
*/
QString Student::getPrenom()
{
	return this->prenom;
}

/**
 * @brief cette methode permet de renvoyer une chaine de caractère avec toutes les information de l'étudiant
 * @return cete méthode retourne un QString
*/
QString Student::affiche()
{
	QString retour;

	retour = this->num + ", " + this->nom + ", " + this->prenom + ", " + this->dep + ", " + this->bac;

	return retour;
}

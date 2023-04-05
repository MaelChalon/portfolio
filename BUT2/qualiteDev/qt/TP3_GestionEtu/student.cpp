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
 * @brief cette methode permet de modifier la valeur du numero d'un �tudiant
 * @param n ce parametre correspond au numero d'�tudiant a modifier
*/
void Student::setNum(QString n)
{
	this->num = n;
}

/**
 * @brief cette m�tode permet de renvoyer le numero d'un �tudiant
 * @return le retour de cette m�thode est un int
*/
QString Student::getNum()
{
	return this->num;
}

/**
 * @brief cette m�thode permet de modifier le departement d'un �tidiant
 * @param d ce parametre correspond au numero de d�partement a modifier
*/
void Student::setDep(QString d)
{
	this->dep = d;
}

/**
 * @brief cette mthode permet de r�cuperer le numero de d�partement d'un �tudiant
 * @return le retour de cette m�thode est un int
*/
QString Student::getDep()
{
	return this->dep;
}

/**
 * @brief cette m�thode permet de modifier le bac de l'�tudiant
 * @param b ce parametre correspond au bac de l'�tudiant a modifier
*/
void Student::setBac(QString b)
{
	this->bac = b;
}

/**
 * @brief cette m�thode permet de r�cuperer le bac d'un �tudiant
 * @return le retour de cette m�thode est un QString
*/
QString Student::getBac()
{
	return this->bac;
}

/**
 * @brief cette m�thode permet de modifier le nom de l'�tudiant
 * @param n ce parametre correspond au nom a modifier
*/
void Student::setNom(QString n)
{
	this->nom = n;
}

/**
 * @brief cette methode permet de r�cuperer le nom d'un �tudiant
 * @return cette m�thode retourn un QString
*/
QString Student::getNom()
{
	return this->nom;
}

/**
 * @brief cette methode permet de modifier le prenom de l'�tudiant
 * @param p ce parametre correspond au prenom a modifier
*/
void Student::setPrenom(QString p)
{
	this->prenom = p;
}

/**
 * @brief cette methode permet de recuperer le prenom d'un �udiant
 * @return cette m�thode retourne un QString
*/
QString Student::getPrenom()
{
	return this->prenom;
}

/**
 * @brief cette methode permet de renvoyer une chaine de caract�re avec toutes les information de l'�tudiant
 * @return cete m�thode retourne un QString
*/
QString Student::affiche()
{
	QString retour;

	retour = this->num + ", " + this->nom + ", " + this->prenom + ", " + this->dep + ", " + this->bac;

	return retour;
}

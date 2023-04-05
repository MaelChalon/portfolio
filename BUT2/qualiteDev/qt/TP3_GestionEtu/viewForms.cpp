#include "viewForms.h"

ViewForms::ViewForms() 
{
}

ViewForms::ViewForms(Promotion* p, QWidget* v, ViewList* l)
{
	this->listView = l;
	this->vue = v;
	this->promo = p;
}

void ViewForms::addStud()
{
	QString num, prenom, nom, bac, dep;

	vue->findChild<QLineEdit*>("lineEdit_add_number");
}

void ViewForms::delStud()
{
}

#include "listeChaine.h"

bool listeChaine::at_begining()
{
	if (this->listeDoublementChaine == nullptr) {
		return true;
	}
	else {
		if (listeDoublementChaine->precedent == nullptr) {
			return true;
		}
		return false;
	}
}

void listeChaine::begining_of()
{
	if (!this->at_begining()) {
		while (this->listeDoublementChaine->precedent != nullptr) {
			this->listeDoublementChaine = this->listeDoublementChaine->precedent;
		}
	}
}

bool listeChaine::at_end()
{
	if (this->listeDoublementChaine == nullptr) {
		return true;
	}
	else {
		if (listeDoublementChaine->suivant == nullptr) {
			return true;
		}
		return false;
	}
}

void listeChaine::end_of()
{
	if (!this->at_end()) {
		while (this->listeDoublementChaine->suivant != nullptr) {
			this->listeDoublementChaine = this->listeDoublementChaine->suivant;
		}
	}
}

bool listeChaine::liste_vide()
{
	return this->listeDoublementChaine==nullptr;
}

int listeChaine::longueur_totale()
{
	if (!this->liste_vide()) {
		int cptr = 1;
		Cell* tmp = this->listeDoublementChaine;
		if (!this->at_begining()) {
			this->begining_of();
		}
		while (!this->at_end()) {
			this->listeDoublementChaine = this->listeDoublementChaine->suivant;
			cptr++;
		}
		this->listeDoublementChaine = tmp;
		return cptr;
	}
	else {
		return 0;
	}
}

int listeChaine::Longueur_fin()
{
	if (!this->liste_vide()) {
		int cptr = 1;
		Cell* tmp = this->listeDoublementChaine;
		while (!this->at_end()) {
			this->listeDoublementChaine = this->listeDoublementChaine->suivant;
			cptr++;
		}
		this->listeDoublementChaine = tmp;
		return cptr;
	}
	else {
		return 0;
	}
}

int listeChaine::longueur_debut()
{
	if (!this->liste_vide()) {
		int cptr = 1;
		Cell* tmp = this->listeDoublementChaine;
		while (!this->at_begining()) {
			this->listeDoublementChaine = this->listeDoublementChaine->precedent;
			cptr++;
		}
		this->listeDoublementChaine = tmp;
		return cptr;
	}
	else {
		return 0;
	}
}

void listeChaine::affiche()
{
	if (!this->liste_vide()) {
		Cell* tmp = this->listeDoublementChaine;
		this->begining_of();
		while (!this->at_end()) {
			cout << this->listeDoublementChaine->element << '\t';
			cout << "precedent: ";
			if (this->listeDoublementChaine->precedent != nullptr) {
				cout << this->listeDoublementChaine->precedent->element << '\t' << '\t';
			}
			else {
				cout << "nullptr" << '\t';
			}
			cout << " suivant: ";
			if (this->listeDoublementChaine->suivant != nullptr) {
				cout << this->listeDoublementChaine->suivant->element;
			}
			else {
				cout << "nullptr";
			}
			cout << endl;
			this->listeDoublementChaine = this->listeDoublementChaine->suivant;
		}
		cout << this->listeDoublementChaine->element << '\t';
		cout << "precedent: ";
		if (this->listeDoublementChaine->precedent != nullptr) {
			cout << this->listeDoublementChaine->precedent->element << '\t' << '\t';
		}
		else {
			cout << "nullptr" << '\t';
		}
		cout << " suivant: ";
		if (this->listeDoublementChaine->suivant != nullptr) {
			cout << this->listeDoublementChaine->suivant->element;
		}
		else {
			cout << "nullptr";
		}
		cout << endl;
		this->listeDoublementChaine = tmp;
	}
}

void listeChaine::ajouterDebut(int elem)
{
	Cell* add = new Cell;
	add->element = elem;
	add->precedent = nullptr;
	add->suivant = nullptr;

	if (this->liste_vide()) {
		this->listeDoublementChaine = add;
	}
	else {
		Cell* tmp = this->listeDoublementChaine;
		this->begining_of();
		add->suivant = this->listeDoublementChaine;
		this->listeDoublementChaine->precedent = add;
		this->listeDoublementChaine = tmp;
	}
}

void listeChaine::ajouterFin(int elem)
{
	Cell* add = new Cell;
	add->element = elem;
	add->precedent = nullptr;
	add->suivant = nullptr;

	if (this->liste_vide()) {
		this->listeDoublementChaine = add;
	}
	else {
		Cell* tmp = this->listeDoublementChaine;
		this->end_of();
		add->precedent = this->listeDoublementChaine;
		this->listeDoublementChaine->suivant = add;
		this->listeDoublementChaine = tmp;
	}
}

bool listeChaine::ajouterK(int elem, int pos)
{
	if (pos == 0) {
		this->ajouterDebut(elem);
		return true;
	}
	if (pos == this->longueur_totale()) {
		this->ajouterFin(elem);
		return true;
	}
	if (this->longueur_totale() < pos) {
		return false;
	}
	else {
		int cptr = 0;
		Cell* tmp = this->listeDoublementChaine;
		this->begining_of();
		while (cptr < pos-1) {
			this->listeDoublementChaine = this->listeDoublementChaine->suivant;
			cptr++;
		}
		Cell* add = new Cell;
		add->element = elem;
		add->precedent = this->listeDoublementChaine;
		add->suivant = this->listeDoublementChaine->suivant;
		this->listeDoublementChaine->suivant->precedent = add;
		this->listeDoublementChaine->suivant = add;
		this->listeDoublementChaine = tmp;
		return true;
	}
}

int listeChaine::supprDebut()
{
	int retour = NULL;
	if (!this->liste_vide()) {
		if (this->longueur_totale() == 1) {
			retour = this->listeDoublementChaine->element;
			delete this->listeDoublementChaine;
			this->listeDoublementChaine = nullptr;
		}
		else {
			Cell* tmp = this->listeDoublementChaine;
			this->begining_of();
			if (this->listeDoublementChaine == tmp) {
				tmp = tmp->suivant;
			}
			this->listeDoublementChaine->suivant->precedent = nullptr;
			retour = this->listeDoublementChaine->element;
			delete this->listeDoublementChaine;
			this->listeDoublementChaine = tmp;
		}
	}
	return retour;
}

int listeChaine::supprFin()
{
	int retour = NULL;
	if (!this->liste_vide()) {
		if (this->longueur_totale() == 1) {
			retour = this->listeDoublementChaine->element;
			delete this->listeDoublementChaine;
			this->listeDoublementChaine = nullptr;
		}
		else {
			Cell* tmp = this->listeDoublementChaine;
			this->end_of();
			if (this->listeDoublementChaine == tmp) {
				tmp = tmp->precedent;
			}
			this->listeDoublementChaine->precedent->suivant = nullptr;
			retour = this->listeDoublementChaine->element;
			delete this->listeDoublementChaine;
			this->listeDoublementChaine = tmp;
		}
	}
	return retour;
}

int listeChaine::supprK(int pos)
{
	int retour = NULL;
	if (pos == 0) {
		retour = this->supprDebut();
	}
	else {
		if (pos == this->longueur_totale()) {
			retour = this->supprFin();
		}
		else {
			if (!this->liste_vide()) {
				if (!(this->longueur_totale() < pos)) {
					Cell* tmp = this->listeDoublementChaine;
					this->begining_of();
					int cptr = 0;
					while (cptr < pos) {
						this->listeDoublementChaine = this->listeDoublementChaine->suivant;
						cptr++;
					}
					if (this->listeDoublementChaine == tmp) {
						if (this->listeDoublementChaine->suivant == nullptr) {
							tmp = this->listeDoublementChaine->precedent;
						}
						else {
							tmp = this->listeDoublementChaine->suivant;
						}
						this->listeDoublementChaine->precedent->suivant = this->listeDoublementChaine->suivant;
						this->listeDoublementChaine->suivant->precedent = this->listeDoublementChaine->precedent;
						retour = this->listeDoublementChaine->element;
					}
				}
			}
		}
	}
	
	return retour;
}

int listeChaine::supprCourant()
{
	int retour = NULL;
	if (!this->liste_vide()) {
		if (this->longueur_totale() == 1) {
			retour = this->listeDoublementChaine->element;
			delete this->listeDoublementChaine;
			this->listeDoublementChaine = nullptr;
		}
		else {
			if (this->at_begining()) {
				retour = this->supprDebut();
			}
			else {
				if (this->at_end()) {
					retour = this->supprFin();
				}
				else {
					Cell* tmp = this->listeDoublementChaine->suivant;
					retour = this->listeDoublementChaine->element;
					this->listeDoublementChaine->precedent->suivant = this->listeDoublementChaine->suivant;
					this->listeDoublementChaine->suivant->precedent = this->listeDoublementChaine->precedent;
					delete this->listeDoublementChaine;
					this->listeDoublementChaine = tmp;
				}
			}
		}
	}
	return retour;
}

void listeChaine::supprElement(int val)
{
	if (!this->liste_vide()) {
		Cell* tmp = this->listeDoublementChaine;
		int cptr = 0;
		while (this->at_end()) {
			if (val == this->listeDoublementChaine->element) {
				this->supprK(cptr);
				cptr++;
			}
		}
	}
}


#include "viewList.h"

ViewList::ViewList()
{
}

ViewList::ViewList(Promotion* promo, QListWidget* v)
{
	this->promo = promo;

	this->vue = v;

	this->control = new Controller_DeleteList(promo);
}

void ViewList::update()
{
	this->vue->clear();
	for (int i = 0; i < this->promo->sizePromo(); i++) {
		this->vue->addItem(this->promo->getEtudiant(i).affiche());
	}
	
}

void ViewList::delete_list()
{
	for (QListWidgetItem* item : vue->selectedItems()) {
		control->control(item->text().split(","));
	}
	this->update();
}

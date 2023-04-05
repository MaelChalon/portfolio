#include "TreeList.h"

TreeList::TreeList()
{
	this->master = nullptr;
	this->courant = master;
}

TreeList::~TreeList()
{
	remove(master);
	this->courant = master;
	delete master;
}

void TreeList::addChild(string name)
{
	treeNode* tmp = new treeNode;
	tmp->nom = name;
	tmp->status = true;
	if (this->master == nullptr) {
		master = tmp;
		master->parent = nullptr;
	}
	else {
		tmp->parent = courant;
		courant->childs.push_back(tmp);
	}
}

void TreeList::resetCurrentPos()
{
	courant = master;
}

bool TreeList::moveToChild(int index)
{
	if (courant != nullptr) {
		if (courant->childs.size() > index) {
			this->courant = this->courant->childs.at(index);
			return true;
		}
		else {
			return false;
		}
	}
}

bool TreeList::moveToParent()
{
	if (courant != nullptr) {
		if (courant->parent != nullptr) {
			courant = courant->parent;
			return true;
		}
		else {
			return false;
		}
	}
}

string TreeList::printNode(treeNode* tn, int l) const
{
	
	if (tn != nullptr) {
		string retour = "";
		for (int i = 0; i < l; i++) {
			retour = retour + "\t";
		}
		if (tn->status) {
			retour = retour + "o " + tn->nom;
		}
		else {
			retour = retour + "x " + tn->nom;
		}
		
		if (tn->parent != nullptr) {
			for (int i = 1; i < tn->parent->childs.size() + 1; i++) {
				if (tn->parent->childs.at(i - 1) == tn) {
					retour = retour + " " + to_string(i);
				}
			}
		}
		if (tn->status) {
			if (tn->childs.size() != 0) {
				for (auto it : tn->childs) {
					retour = retour + '\n' + printNode(it, l + 1);
				}
			}
		}
		return retour;
	}
	else {
		return "";
	}
	
}

void TreeList::remove(treeNode* tn)
{
	if (tn != nullptr) {
		if (!tn->childs.empty()) {
			int size = tn->childs.size();
			for (int i = 0; i < size; i++) {
				remove(tn->childs.at(0));
			}
		}
		if (tn->parent != nullptr) {
			treeNode* parent = tn->parent;
			int pos = 0;
			for (int i = 0; i < parent->childs.size(); i++) {
				if (parent->childs.at(i) == tn) {
					pos = i;
				}
			}
			parent->childs.erase(parent->childs.begin() + pos);
		}
		if (tn != master) {
			delete tn;
		}
		courant = tn->parent;
	}
}

void TreeList::removeAllChildren()
{
	if (courant != nullptr) {
		for (auto it : courant->childs) {
			remove(it);
		}
	}	
}

void TreeList::removeChild(int index)
{
	if (courant != nullptr) {
		if (index < courant->childs.size()) {
			this->remove(courant->childs.at(index));
		}
	}
}

void TreeList::removeChild(string nom)
{
	if (courant != nullptr) {
		int index = -1;
		for (int i = 0; i < courant->childs.size(); i++) {
			if (courant->childs.at(i)->nom == nom) {
				index = i;
			}
		}
		if (index != -1) {
			remove(courant->childs.at(index));
		}
	}
}

void TreeList::editName(string n)
{
	if (courant != nullptr) {
		courant->nom = n;
	}
}

void TreeList::changeStatus()
{
	if (courant != nullptr) {
		courant->status = !courant->status;
	}
}

ostream& operator<<(ostream& os, const TreeList& dt)
{
	os << dt.printNode(dt.master, 0);
	return os;
}

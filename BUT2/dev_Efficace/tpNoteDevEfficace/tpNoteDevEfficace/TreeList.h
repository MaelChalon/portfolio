#pragma once
#include "treeNode.h"
#include <iostream>


class TreeList
{

public:
	treeNode* master;
	treeNode* courant;

public:
	TreeList();
	~TreeList();

	void addChild(string name);

	void resetCurrentPos();
	bool moveToChild(int);
	bool moveToParent();

	friend ostream& operator<<(ostream& os, const TreeList& dt);
	string printNode(treeNode* tn, int l) const;

	void remove(treeNode*);
	void removeAllChildren();
	void removeChild(int);
	void removeChild(string);

	void editName(string);
	void changeStatus();
};
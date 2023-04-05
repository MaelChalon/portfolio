#pragma once
#include <string>
#include <vector>

using namespace std;


struct treeNode {
	string nom;
	bool status;
	treeNode* parent;
	vector<treeNode*> childs;
};
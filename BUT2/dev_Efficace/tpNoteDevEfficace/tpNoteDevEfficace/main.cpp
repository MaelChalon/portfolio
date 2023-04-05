#include "TreeList.h"


int main(void) {

	TreeList tl;
	tl.addChild("root");
	tl.resetCurrentPos();
	tl.addChild("test");
	tl.moveToChild(0);
	tl.addChild("no");
	tl.addChild("wowowo");
	tl.addChild("salut");
	tl.moveToParent();
	tl.addChild("yes");
	cout << tl << endl << endl;

	tl.resetCurrentPos();
	tl.moveToChild(0);
	tl.changeStatus();

	cout << tl;

	return EXIT_SUCCESS;
}
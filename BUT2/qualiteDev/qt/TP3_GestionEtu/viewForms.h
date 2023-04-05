#pragma once
#include "observer.h"
#include <qobject.h>
#include "promotion.h"
#include <qlistwidget.h>
#include "viewList.h"

class ViewForms : public QObject
{

private:
	Promotion* promo;
	QWidget* vue;
	ViewList* listView;


public:
	ViewForms();
	ViewForms(Promotion*, QWidget*, ViewList*);

	void addStud();
	void delStud();
};


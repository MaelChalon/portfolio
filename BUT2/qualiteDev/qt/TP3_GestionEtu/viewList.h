#pragma once
#include "promotion.h"
#include "promotion.h"
#include "observer.h"
#include "qlistwidget.h"
#include "controllers.h"

/**
 * @brief Class to manage the QListWidget
 * @author Adrien Peytavie
*/
class ViewList : public Observer, public QObject
{
private:
	Promotion* promo;
	QListWidget* vue;
	Controller_DeleteList* control;

public:
	ViewList();
	ViewList(Promotion*, QListWidget*);
	void update() override;
	void delete_list();
};


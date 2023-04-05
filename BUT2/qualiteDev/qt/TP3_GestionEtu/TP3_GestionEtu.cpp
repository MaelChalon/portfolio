#include "TP3_GestionEtu.h"

TP3_GestionEtu::TP3_GestionEtu(Promotion* promo,QWidget *parent)
    : QMainWindow(parent)
{
    ui.setupUi(this);

    listView = new ViewList(promo, ui.listWidget);
    listView->update();

    listForm = new ViewForms(promo, ui.groupBox_AddStudent, ui.groupBox_DeleteStudent, listView);

    connect(ui.pushButton_delete_list, &QPushButton::clicked, listView, &ViewList::delete_list);
    connect(ui.pushButton_addStudent, &QPushButton::clicked, listForm, &ViewForms::addStud);
    connect(ui.pushButton_delete_number, &QPushButton::clicked, listForm, &ViewForms::delStud);
    

}

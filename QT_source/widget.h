#ifndef WIDGET_H
#define WIDGET_H

#include <QWidget>
#include "second.h"

extern int cidao[];

namespace Ui {
class Widget;
}

class Widget : public QWidget
{
    Q_OBJECT

public:

    explicit Widget(QWidget *parent = nullptr);
    ~Widget();


private slots:
    void on_BTN_CreateRandom_clicked();

    void on_BTN_Choose_clicked();

private:
    Ui::Widget *ui;
    Second S;
};

#endif // WIDGET_H

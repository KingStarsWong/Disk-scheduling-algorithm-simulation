#ifndef SECOND_H
#define SECOND_H

#include <QWidget>


namespace Ui {
class Second;
}

class Second : public QWidget
{
    Q_OBJECT


public:
    explicit Second(QWidget *parent = nullptr);
    ~Second();

    QString m;

private slots:

    void on_BTN_FCFS_clicked();

    void on_BTN_SSTF_clicked();

    void on_BTN_SCAN_clicked();

    void on_BTN_CSCAN_clicked();

    void on_BTN_Compare_clicked();

private:
    Ui::Second *ui;
};

#endif // SECOND_H

#include "widget.h"
#include "ui_widget.h"
#include <QTime>
#include<QList>
#include<QVector>
#include<QString>
#include<QDebug>
#include<QRandomGenerator>
#include<QMessageBox>
#include <QIntValidator>

int cidao[1000];

Widget::Widget(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Widget)
{
    ui->setupUi(this);
    setWindowTitle("模拟磁盘调度算法");
    this->ui->TE_RandomTrack->setReadOnly(true);  //产生的随机数只读不可编辑

    QIntValidator *validator = new QIntValidator(0, 1000, this);
    ui->LE_TrackNum->setValidator(validator);     //只能输入0-1,000的整数


}

Widget::~Widget()
{
    delete ui;
}

void Widget::on_BTN_CreateRandom_clicked()
{
    QString a="";//用于输出显示随机的磁道

    int h = this->ui->LE_ExitTrackNum->text().toInt();//获得磁盘所有的磁道数目
    int k = this->ui->LE_TrackNum->text().toInt();      //获得需要的磁道数量

    if(this->ui->LE_TrackNum->text().isEmpty())
    {
        QMessageBox::critical(this,"错误","需输入磁道数","确认");
    }
    else
    {
        if((this->ui->LE_TrackNum->text()).toInt()> h )
        {
            QMessageBox::critical(this,"错误","需要的磁道数目大于已有的磁道数目","确认");
            this->ui->LE_TrackNum->clear();
        }

        //随机种子设置
        QTime t;
        t= QTime::currentTime();
        qsrand(t.msec()+t.second()*1000);

        for(int i=0;i<k;i++)
        {
            int x;
            x= qrand()% h ;
            if(x==0){
                x=x+qrand()% h;
            }
            cidao[i]=x;

            //用于主界面显示
            QString s=QString::number(x,10);
            a+=s+" ";
         }

         this->ui->TE_RandomTrack->setText(a);
    }

}


void Widget::on_BTN_Choose_clicked()
{
    if(cidao[0]!=0){
        this->S.show();
    }
    else {
        QMessageBox::warning(this, "注意", "需要输入相关信息才可进行下一步");
    }
}

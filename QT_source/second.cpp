#include "second.h"
#include "ui_second.h"
#include "algorithm.h"
#include "widget.h"
#include "qcustomplot.h"
#include<QDebug>

static Algorithm al;

static QString m;
static int now;

Second::Second(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Second)
{
    ui->setupUi(this);
    setWindowTitle("模拟磁盘调度算法");
    this->ui->LE_now->setReadOnly(true);

    /*
     *
     * 以下为设置初始的空图表
     *
     */

    // set dark background 设置黑暗背景:
    QLinearGradient gradient(0, 0, 0, 400);
    gradient.setColorAt(0, QColor(90, 90, 90));
    gradient.setColorAt(0.38, QColor(105, 105, 105));
    gradient.setColorAt(1, QColor(70, 70, 70));
    this->ui->Chart->setBackground(QBrush(gradient));
    // create empty bar chart objects:创建空的条形图对象
    QCPBars *fcfs = new QCPBars(this->ui->Chart->xAxis, this->ui->Chart->yAxis);
    QCPBars *sstf = new QCPBars(this->ui->Chart->xAxis, this->ui->Chart->yAxis);
    QCPBars *scan = new QCPBars(this->ui->Chart->xAxis, this->ui->Chart->yAxis);
    QCPBars *cscan = new QCPBars(this->ui->Chart->xAxis, this->ui->Chart->yAxis);
    fcfs->setAntialiased(false); // gives more crisp, pixel aligned bar borders 提供更清晰，像素对齐的条形边框
    sstf->setAntialiased(false);
    scan->setAntialiased(false);
    cscan->setAntialiased(false);

    /*
     *
    如果此条形绘图表堆叠在另一个条形绘图表（moveAbove）的顶部，则此方法允许指定一个以像素为单位的距离，通过该距离，绘制的条形矩形将由其下方的条形分隔。
    regen->setStackingGap(1);
    nuclear->setStackingGap(1);
    fossil->setStackingGap(1);
    */

    // set names and colors:
    fcfs->setName("FCFS");
    fcfs->setPen(QPen(QColor(111, 9, 176).lighter(170)));
    fcfs->setBrush(QColor(111, 9, 176));
    //
    sstf->setName("SSTF");
    sstf->setPen(QPen(QColor(250, 170, 20).lighter(150)));
    sstf->setBrush(QColor(250, 170, 20));
    //
    scan->setName("SCAN");
    scan->setPen(QPen(QColor(0, 168, 140).lighter(130)));
    scan->setBrush(QColor(72, 209, 204));
    //
    cscan->setName("CSCAN");
    cscan->setPen(QPen(QColor(0, 168, 140).lighter(160)));
    cscan->setBrush(QColor(255, 48, 48));
    // prepare x axis with country labels: 设置x轴
    QVector<double> ticks;
    QVector<QString> labels;
    ticks << 1 << 2 << 3 << 4 ;
    labels << "FCFS" << "SSTF" << "SCAN" << "CSCAN";
    QSharedPointer<QCPAxisTickerText> textTicker(new QCPAxisTickerText);
    textTicker->addTicks(ticks, labels);
    this->ui->Chart->xAxis->setTicker(textTicker);
    //this->ui->Chart->xAxis->setTickLabelRotation(60); //设置标签旋转
    this->ui->Chart->xAxis->setSubTicks(false);  //设置是否显示子标签
    this->ui->Chart->xAxis->setTickLength(0, 4);
    this->ui->Chart->xAxis->setRange(0, 8);    //设置x轴区间
    this->ui->Chart->xAxis->setBasePen(QPen(Qt::white));
    this->ui->Chart->xAxis->setTickPen(QPen(Qt::white));
    this->ui->Chart->xAxis->grid()->setVisible(true);   //设置网格是否显示
    this->ui->Chart->xAxis->grid()->setPen(QPen(QColor(130, 130, 130), 0, Qt::DotLine));
    this->ui->Chart->xAxis->setTickLabelColor(Qt::white);  //设置标记标签颜色
    this->ui->Chart->xAxis->setLabelColor(Qt::white);

    // prepare y axis:  设置y轴
    this->ui->Chart->yAxis->setRange(0, 150.0);
    this->ui->Chart->yAxis->setPadding(5); // a bit more space to the left border 左边框空间更多
    this->ui->Chart->yAxis->setLabel("平均寻道长度");
    this->ui->Chart->yAxis->setBasePen(QPen(Qt::white));
    this->ui->Chart->yAxis->setTickPen(QPen(Qt::white));
    this->ui->Chart->yAxis->setSubTickPen(QPen(Qt::white));  //设置SubTick颜色，SubTick指的是轴上的刻度线
    this->ui->Chart->yAxis->grid()->setSubGridVisible(true);
    this->ui->Chart->yAxis->setTickLabelColor(Qt::white);
    this->ui->Chart->yAxis->setLabelColor(Qt::white);
    this->ui->Chart->yAxis->grid()->setPen(QPen(QColor(130, 130, 130), 0, Qt::SolidLine));
    this->ui->Chart->yAxis->grid()->setSubGridPen(QPen(QColor(130, 130, 130), 0, Qt::DotLine));

    // setup legend: 设置图例
    this->ui->Chart->legend->setVisible(false);  //设置图例是否可用
    this->ui->Chart->axisRect()->insetLayout()->setInsetAlignment(0, Qt::AlignTop|Qt::AlignRight);//图例矩形位置
    this->ui->Chart->legend->setBrush(QColor(255, 255, 255, 100));  //设置背景色
    this->ui->Chart->legend->setBorderPen(Qt::NoPen);
    QFont legendFont = font();
    legendFont.setPointSize(10);
    this->ui->Chart->legend->setFont(legendFont);
    this->ui->Chart->setInteractions(QCP::iRangeDrag | QCP::iRangeZoom);//设置 可拖动，可放大缩小
}

Second::~Second()
{
    delete ui;
}

void Second::on_BTN_FCFS_clicked()
{
    if(this->ui->LE_start->text().isEmpty()){
        QMessageBox::warning(this, "错误", "需设置磁头初始位置");
        this->ui->BTN_FCFS->setChecked(false);
    }
    else{
        m = this->ui->LE_start->text();
        now=m.toInt();
        al.FCFS(cidao,now);
    }
}

void Second::on_BTN_SSTF_clicked()
{
    if(this->ui->LE_start->text().isEmpty()){
        QMessageBox::warning(this, "错误", "需设置磁头初始位置");
        this->ui->BTN_SSTF->setChecked(false);
    }
    else{
        al.SSTF(cidao,now);
    }


}

void Second::on_BTN_SCAN_clicked()
{
    if(this->ui->LE_start->text().isEmpty()){
        QMessageBox::warning(this, "错误", "需设置磁头初始位置");
        this->ui->BTN_SCAN->setChecked(false);
    }
    else{
        al.SCAN(cidao,now);
    }
}

void Second::on_BTN_CSCAN_clicked()
{
    if(this->ui->LE_start->text().isEmpty()){
        QMessageBox::warning(this, "错误", "需设置磁头初始位置");
        this->ui->BTN_CSCAN->setChecked(false);
    }
    else{
        al.CSCAN(cidao,now);
    }

}

void Second::on_BTN_Compare_clicked()
{
    this->ui->Chart->clearPlottables();//点击一次清除上一次的图像

    this->ui->LE_now->setText(this->ui->LE_start->text());

    /*一个简单的判断 找出平均寻道长度中的最大值 方便设置图表Y轴便于观察*/
    double max=al.ave_a;
    if(max<al.ave_b){
        max=al.ave_b;
    }
    else{
        if(max<al.ave_c){
            max=al.ave_c;
            }
        else{
            if(max<al.ave_d){
                max=al.ave_d;
                }
            else {
                max=al.ave_a;
            }
        }
    }


    // set dark background 设置黑暗背景:
    QLinearGradient gradient(0, 0, 0, 400);
    gradient.setColorAt(0, QColor(90, 90, 90));
    gradient.setColorAt(0.38, QColor(105, 105, 105));
    gradient.setColorAt(1, QColor(70, 70, 70));
    this->ui->Chart->setBackground(QBrush(gradient));

    // create empty bar chart objects:创建空的条形图对象
    QCPBars *fcfs = new QCPBars(this->ui->Chart->xAxis, this->ui->Chart->yAxis);
    QCPBars *sstf = new QCPBars(this->ui->Chart->xAxis, this->ui->Chart->yAxis);
    QCPBars *scan = new QCPBars(this->ui->Chart->xAxis, this->ui->Chart->yAxis);
    QCPBars *cscan = new QCPBars(this->ui->Chart->xAxis, this->ui->Chart->yAxis);
    fcfs->setAntialiased(false); // gives more crisp, pixel aligned bar borders 提供更清晰，像素对齐的条形边框
    sstf->setAntialiased(false);
    scan->setAntialiased(false);
    cscan->setAntialiased(false);

    /*
     *
    如果此条形绘图表堆叠在另一个条形绘图表（moveAbove）的顶部，则此方法允许指定一个以像素为单位的距离，通过该距离，绘制的条形矩形将由其下方的条形分隔。
    regen->setStackingGap(1);
    nuclear->setStackingGap(1);
    fossil->setStackingGap(1);
    */

    // set names and colors:  设置名字和颜色
    fcfs->setName("FCFS");
    fcfs->setPen(QPen(QColor(111, 9, 176).lighter(170)));  //RGB 和亮度
    fcfs->setBrush(QColor(111, 9, 176));
    //
    sstf->setName("SSTF");
    sstf->setPen(QPen(QColor(250, 170, 20).lighter(150)));
    sstf->setBrush(QColor(250, 170, 20));
    //
    scan->setName("SCAN");
    scan->setPen(QPen(QColor(0, 168, 140).lighter(130)));
    scan->setBrush(QColor(72, 209, 204));
    //
    cscan->setName("CSCAN");
    cscan->setPen(QPen(QColor(0, 168, 140).lighter(160)));
    cscan->setBrush(QColor(255, 48, 48));

    /*
    // stack bars on top of each other: 将图形条堆叠一起
    nuclear->moveAbove(fossil);
    regen->moveAbove(nuclear);
    */

    // prepare x axis with country labels: 设置x轴
    QVector<double> ticks;
    QVector<QString> labels;
    ticks << 1 << 2 << 3 << 4 ;
    labels << "FCFS" << "SSTF" << "SCAN" << "CSCAN";
    QSharedPointer<QCPAxisTickerText> textTicker(new QCPAxisTickerText);
    textTicker->addTicks(ticks, labels);
    this->ui->Chart->xAxis->setTicker(textTicker);
    //this->ui->Chart->xAxis->setTickLabelRotation(60); //设置标签旋转
    this->ui->Chart->xAxis->setSubTicks(false);  //设置是否显示子标签
    this->ui->Chart->xAxis->setTickLength(0, 4);
    this->ui->Chart->xAxis->setRange(0, 8);    //设置x轴区间
    this->ui->Chart->xAxis->setBasePen(QPen(Qt::white));
    this->ui->Chart->xAxis->setTickPen(QPen(Qt::white));
    this->ui->Chart->xAxis->grid()->setVisible(true);   //设置网格是否显示
    this->ui->Chart->xAxis->grid()->setPen(QPen(QColor(130, 130, 130), 0, Qt::DotLine));
    this->ui->Chart->xAxis->setTickLabelColor(Qt::white);  //设置标记标签颜色
    this->ui->Chart->xAxis->setLabelColor(Qt::white);

    // prepare y axis:  设置y轴
    this->ui->Chart->yAxis->setRange(0, (max+10)); //点击比较按钮后能够根据每一次的最大值设置图表Y轴 方便观察
    this->ui->Chart->yAxis->setPadding(5); // a bit more space to the left border 左边框空间更多
    this->ui->Chart->yAxis->setLabel("平均寻道长度");
    this->ui->Chart->yAxis->setBasePen(QPen(Qt::white));
    this->ui->Chart->yAxis->setTickPen(QPen(Qt::white));
    this->ui->Chart->yAxis->setSubTickPen(QPen(Qt::white));  //设置SubTick颜色，SubTick指的是轴上的刻度线
    this->ui->Chart->yAxis->grid()->setSubGridVisible(true);
    this->ui->Chart->yAxis->setTickLabelColor(Qt::white);
    this->ui->Chart->yAxis->setLabelColor(Qt::white);
    this->ui->Chart->yAxis->grid()->setPen(QPen(QColor(130, 130, 130), 0, Qt::SolidLine));
    this->ui->Chart->yAxis->grid()->setSubGridPen(QPen(QColor(130, 130, 130), 0, Qt::DotLine));

    // Add data:   添加数据
    QVector<double> FCFSData, SSTFData, SCANData,CSCANData;
    FCFSData << 0 << 0 << 0;
    FCFSData.prepend(al.ave_a);
    SSTFData << 0 << 0 << 0;
    SSTFData.insert(1,al.ave_b);
    SCANData << 0<< 0<<0;
    SCANData.insert(2,al.ave_c);
    CSCANData<<0<<0<<0;
    CSCANData.push_back(al.ave_d);

    fcfs->setData(ticks, FCFSData);
    sstf->setData(ticks, SSTFData);
    scan->setData(ticks, SCANData);
    cscan->setData(ticks,CSCANData);

    // setup legend: 设置图例
    if(this->ui->LE_start->text().isEmpty()){
        this->ui->Chart->legend->setVisible(false);
    }
    else {
        this->ui->Chart->legend->setVisible(true);  //设置图例是否可用
    }

    this->ui->Chart->axisRect()->insetLayout()->setInsetAlignment(0, Qt::AlignTop|Qt::AlignRight);//图例矩形位置
    this->ui->Chart->legend->setBrush(QColor(255, 255, 255, 100));  //设置背景色
    this->ui->Chart->legend->setBorderPen(Qt::NoPen);
    QFont legendFont = font();
    legendFont.setPointSize(10);
    this->ui->Chart->legend->setFont(legendFont);
    this->ui->Chart->setInteractions(QCP::iRangeDrag | QCP::iRangeZoom);//设置 可拖动，可放大缩小

    this->ui->Chart->replot();//重绘

    /*重置*/
    //重置求得的平均寻道长度
    al.ave_a=0;
    al.ave_b=0;
    al.ave_c=0;
    al.ave_d=0;

    //取消按钮高亮显示
    this->ui->BTN_FCFS->setChecked(false);
    this->ui->BTN_SSTF->setChecked(false);
    this->ui->BTN_SCAN->setChecked(false);
    this->ui->BTN_CSCAN->setChecked(false);

    this->ui->LE_start->clear();
}

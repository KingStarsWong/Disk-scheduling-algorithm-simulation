#include "algorithm.h"
#include "widget.h"
#include<cmath>
#include<QString>
#include<QMessageBox>
#include<widget.h>
#include<QDebug>


Algorithm::Algorithm()
{

}

/*****冒泡排序算法*****/
int *bubble(int cidao[],int m){
    int i,j;
    int temp;
    for(i=0;i<m;i++){//使用冒泡法从小到大顺序排列
        for(j=i+1;j<m;j++){
            if(cidao[i]>cidao[j]){
                temp=cidao[i];
                cidao[i]=cidao[j];
                cidao[j]=temp;
            }
        }
}
    return cidao;
}

/*****先来先服务算法*****/
void Algorithm::FCFS(int cidao[],int now){
    /*
    for (int t=0;t<1000;t++) {
        if(cidao[t]!=0){qDebug()<<t<<" "<<cidao[t];}
    }*/
    int sum=0;//总寻道长度
    int i,j;
    int len=0;
    float ave = 0;//平均寻道长度

    sum+=abs(cidao[0]-now);
    //qDebug()<<"a"<<sum;

    qDebug()<<(sizeof(cidao));

    for(i=0;i<1000;i++){//输出磁盘扫描序列
        if(cidao[i]!=0){//***
            len++;
            //qDebug()<<i<<" "<<len;
        }
    }
    //qDebug()<<"len:"<<len;

    for(i=0,j=1;j<len;i++,j++){
         sum+=abs(cidao[j]-cidao[i]);
         //qDebug()<<"x"<<sum;
    }

    ave= (float)sum/len;//**
    ave_a = ave;
    qDebug()<<"FCFS:ave:"<<ave_a<<" sum:"<<sum<<" len:"<<len;
}

/*****最短寻道时间优先调度算法*****/
void Algorithm::SSTF(int cidao[],int now){
    int k=1;
    int l,r,len=0;
    int i,sum=0;
    float ave;

    for(i=0;i<1000;i++)  {
        if(cidao[i]!=0){
            len++;
        }
    }

    //调用冒泡排序算法排序
    cidao=bubble(cidao,len);

    //若当前磁道号大于请求序列中最大者,则直接由外向内依次给予各请求服务
    if(cidao[len-1]<=now) {
        sum=now-cidao[0];
    }

     //若当前磁道号小于请求序列中最小者,则直接由内向外依次给予各请求服务
    if(cidao[0]>=now){
        sum=cidao[len-1]-now;
    }

    //若当前磁道号大于当前请求序列中最小者并且小于最大者
    if(now>cidao[0]&&now<cidao[len-1]){

        //确定当前磁道在已排的序列中的位置
        while(cidao[k]<now){
             k++;
        }
        l=k-1;
        r=k;

        //当前磁道在请求序列范围内
        while((l>=0)&&(r<len)){
            if(now-cidao[l]<=(cidao[r]-now)) {//选择与当前磁道最近的请求给予服务
                sum+=now-cidao[l];
                now=cidao[l];
                l=l-1;
            }
            else{
                sum+=cidao[r]-now;
                now=cidao[r];
                r=r+1;
            }
        }
        if(l==-1) {//磁头移动到序列的最小号，返回外侧扫描仍未扫描的磁道
            sum+=cidao[len-1]-cidao[0];
        }
        else{ //磁头移动到序列的最大号，返回内侧扫描仍未扫描的磁道
            sum+=cidao[len-1]-cidao[0];
        }
    }
    ave=(float)sum/len;
    ave_b = ave;
    qDebug()<<"SSTF:ave:"<<ave_b<<" sum:"<<sum<<" len:"<<len;
}

/******扫描调度算法*****/
void Algorithm::SCAN(int cidao[],int now) { //先要给出当前磁道号和移动臂的移动方向
    int i,sum=0;
    int len=0;
    float ave;
    for(i=0;i<1000;i++){
        if(cidao[i]!=0){
            len++;
        }
    }

    //调用冒泡排序算法排序
    cidao=bubble(cidao,len);
    //若当前磁道号大于请求序列中最大者，则直接由外向内依次给予各请求服务，此情况同最短寻道优先
    if(cidao[len-1]<=now){
        sum=now-cidao[0];
    }

    //若当前磁道号小于请求序列中最小者，则直接由内向外依次给予各请求服务，此情况同最短寻道优先
    if(cidao[0]>=now){
        sum=cidao[len-1]-now;
    }
    //若当前磁道号大于请求序列中最小者且小于最大者
    if(now>cidao[0]&&now<cidao[len-1]){

        QMessageBox msgBox;
          msgBox.setWindowTitle("注意");
          msgBox.setText("需要选择磁头向内还是向外");
          msgBox.setInformativeText("请点击按钮以选择");
          msgBox.setStandardButtons(QMessageBox::Save | QMessageBox::Discard);
          msgBox.setButtonText( QMessageBox::Save, "向内" );
          msgBox.setButtonText( QMessageBox::Discard, "向外" );

          msgBox.setWindowFlags(Qt::WindowCloseButtonHint | Qt::MSWindowsFixedSizeDialogHint);//防止窗口拉伸导致报错

          int ret = msgBox.exec();
        switch (ret) {
            case QMessageBox::Save:
                sum=now-2*cidao[0]+cidao[len-1];
                break;
            case QMessageBox::Discard:
                sum=now-cidao[0]+2*cidao[len-1];
                break;
            default:
                // should never be reached
                break;
          }
        }
        ave= (float)sum/len;
        ave_c = ave;
        qDebug()<<"SCAN:ave:"<<ave_c<<" sum:"<<sum<<" len:"<<len;
        }

/*****循环扫描算法*****/
void Algorithm::CSCAN(int cidao[],int now) { //先要给出当前磁道号和移动臂的移动方向
      int i,sum=0;
      int len=0;
      float ave;
      for(i=0;i<1000;i++){
          if(cidao[i]!=0){
              len++;
          }
      }

      //调用冒泡排序算法排
      cidao=bubble(cidao,len);

      //若当前磁道号大于请求序列中最大者，则直接由外向内依次给予各请求服务，此情况同最短寻道优先
      if(cidao[len-1]<=now){
        sum=now-cidao[0];
      }

      //若当前磁道号小于请求序列中最小者，则直接由内向外依次给予各请求服务，此情况同最短寻道优先
      if(cidao[0]>=now){
       sum=cidao[len-1]-now;
      }

      //若当前磁道号大于请求序列中最小者且小于最大者
      if(now>cidao[0]&&now<cidao[len-1]) {

      QMessageBox msgBox;
        msgBox.setText("需要选择磁头向内还是向外");
        msgBox.setInformativeText("点击按钮选择");
        msgBox.setStandardButtons(QMessageBox::Save | QMessageBox::Discard );
        msgBox.setButtonText( QMessageBox::Save, "向内" );
        msgBox.setButtonText( QMessageBox::Discard, "向外" );

        msgBox.setWindowFlags(Qt::WindowCloseButtonHint | Qt::MSWindowsFixedSizeDialogHint);

        int ret = msgBox.exec();
      switch (ret) {
          case QMessageBox::Save:
              sum=now-2*cidao[0]+cidao[len-1];
              break;
          case QMessageBox::Discard:
              sum=now-cidao[0]+2*cidao[len-1];
              break;
          default:
              // should never be reached
              break;
        }
      }
      ave= (float)sum/len;
      ave_d = ave;
      qDebug()<<"CSCAN:ave:"<<ave_d<<" sum:"<<sum<<" len:"<<len;
}

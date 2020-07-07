# Disk-scheduling-algorithm-simulation/磁盘调度算法模拟

主要模拟磁盘调度的4种算法：FCFS（先来先服务算法）、SSTF（最短寻道优先算法）、SCAN（扫描算法）、C-SCAN（循环扫描算法）

手动输入设定磁盘磁道数量、需要服务的磁道序列数量，随机产生对应数量的磁道序列号；选择需要的算法，计算出平均寻道长度并绘制出柱状图。

基于 QT 5.12 && Qt Creator 4.9.1 

整体数据结构为一个全局数组。

图表绘制采用了Qcustomplot来自[官网](https://www.qcustomplot.com/)

qt中配置可参考[QT5入门之32-QCustomPlot](https://blog.csdn.net/a379039233/article/details/49666307)

重要部分简短整理摘录其中部分如下：

将下载的`qcustomplot.cp` & `qcustomplot.h`导入源文件文件夹中

在工程的pro文件的第9行末尾加入：`printsupport`，如下：
`greaterThan(QT_MAJOR_VERSION, 4): QT += widgets printsupport`
（可参考QT_source下pro文件查看）

寻道算法部分主要参考了来自csdn下载的JAVA版本（忘了保存网址链接）：一份中南大学的课程设计。

图表绘制部分来自Qcustomplot官网[柱状图demo](https://www.qcustomplot.com/index.php/demos/barchartdemo)

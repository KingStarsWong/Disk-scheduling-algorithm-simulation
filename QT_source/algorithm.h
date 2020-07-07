#ifndef ALGORITHM_H
#define ALGORITHM_H

class Algorithm
{


public:
    Algorithm();

    void FCFS(int cidao[],int now);
    void SSTF(int cidao[],int now);
    void SCAN(int cidao[],int now);
    void CSCAN(int cidao[],int now);

    double ave_a;
    double ave_b;
    double ave_c;
    double ave_d;


};

#endif // ALGORITHM_H

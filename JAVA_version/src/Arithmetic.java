package caozuoxitongkeshe;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class Arithmetic extends JPanel{
LogPane log;
public Arithmetic(){
    log=LogPane.getLog();
}   

/*�ж����������Ƿ���Ч*/
public int decide(char str[])
{
int i=0;
while(str[i]!=0){
    if(str[i]<'0'||str[i]>'9'){
    return 0;
    //break;
    }

i++;
}
return i;
}

/*���ַ���ת��Ϊ����*/

int trans(char str[],int a){
int i;
int sum=0;
for(i=0;i<a;i++){
    sum=sum+(int)((str[i]-'0')*Math.pow(10,a-i-1));
}
return sum;
}

/*ð�������㷨*/
int []bubble(int cidao[],int m){
int i,j;
int temp;
for(i=0;i<m;i++)//ʹ��ð�ݷ���С����˳������
for(j=i+1;j<m;j++){
    if(cidao[i]>cidao[j]){

    temp=cidao[i];
    cidao[i]=cidao[j];
    cidao[j]=temp;

    }

}
return cidao;
}
/*�����ȷ����㷨*/
public void FCFS(int cidao[],int now)  //�ŵ������飬����ΪM
{
    //int now;//��ǰ�ŵ���
    int sum=0;//��Ѱ������
    int i,j;
    int count=0;
    int len=0;

    float ave = 0;//ƽ��Ѱ������
            sum+=Math.abs(cidao[0]-now);
            count=count+1;
            System.out.println(sum);
            //cout<<"����ɨ������Ϊ: ";
            String buffer="";
            for(i=0;i<cidao.length;i++)  {//�������ɨ������
            //  cout<<cidao[i]<<" ";
            if(cidao[i]>0){
                len++;
                buffer+=cidao[i]+" ";
            }
            }
            log.addLog("����ɨ������Ϊ: "+buffer.toString());
            for(i=0,j=1;j<len;i++,j++){
                sum+=Math.abs(cidao[j]-cidao[i]);
                count++;
            }
            ave= sum/len;
            System.out.println("sum="+sum+"  count="+count);
            //cout<<endl;
            //cout<<"ƽ��Ѱ������: "<<ave<<endl;
            log.addLog("ƽ��Ѱ������:"+ave);

    }

/*���Ѱ��ʱ�����ȵ����㷨*/
  public void SSTF(int cidao[],int now){
        int k=1;
        int  l,r,len=0;
        int i,j,sum=0;
        int a,count=0;
        float ave;

        for(i=0;i<cidao.length;i++)  { 
            //  cout<<cidao[i]<<" ";
            if(cidao[i]>0){

                len++;
            }
            }

           cidao=bubble(cidao,len); //����ð�������㷨����
           String s="";
           for(int z=0;z<len;z++){
              // System.out.println(cidao[z]);
               s+=cidao[z]+" ";
           }
           log.addLog("�ŵ����д�С��������Ϊ:"+s);
            if(cidao[len-1]<=now) //����ǰ�ŵ��Ŵ������������������,��ֱ�������������θ�����������
            {       String buffer="";
            //      cout<<"����ɨ������Ϊ: ";
                    for(i=len-1;i>=0;i--){
                        buffer+=cidao[i]+" ";
                    }
                    log.addLog("����ɨ������Ϊ: "+buffer.toString());
                    sum=now-cidao[0];
                    count=len;
            }

            if(cidao[0]>=now){ //����ǰ�ŵ���С��������������С��,��ֱ�������������θ�����������;
                        String buffer="";
                        for(i=0;i<len;i++){
                            buffer+=cidao[i]+" ";
                        }
                    log.addLog("����ɨ������Ϊ: "+buffer.toString());
                    sum=cidao[len-1]-now;
                    count=len;
            }

            if(now>cidao[0]&&now<cidao[len-1]) {//����ǰ�ŵ��Ŵ��ڵ�ǰ������������С�߲���С�������
            //  cout<<"����ɨ������Ϊ: ";
                StringBuffer buffer=new StringBuffer("");
                while(cidao[k]<now){   //ȷ����ǰ�ŵ������ŵ������е�λ��
                    k++;    
                }
                l=k-1;
                r=k;
                while((l>=0)&&(r<len)){ //��ǰ�ŵ����������з�Χ��
                    if(now-cidao[l]<=(cidao[r]-now)) {//ѡ���뵱ǰ�ŵ����������������
            //          cout<<cidao[l]<<" ";
                        buffer.append(cidao[l]+" ");
                        sum+=now-cidao[l];
                        now=cidao[l];
                        l=l-1;
                    }
                    else{
                //      cout<<cidao[r]<<" ";
                        buffer.append(cidao[r]+" ");
                        sum+=cidao[r]-now;
                        now=cidao[r];
                        r=r+1;

                    }
                }
                if(l==-1) {//��ͷ�ƶ������е���С�ţ��������ɨ����δɨ��Ĵŵ�
                    for(j=r;j<len;j++){
            //      cout<<cidao[j]<<" ";
                        buffer.append(cidao[j]+" ");
                    }
                    sum+=cidao[len-1]-cidao[0];
                } 
                else{ //��ͷ�ƶ������е����ţ������ڲ�ɨ����δɨ��Ĵŵ�
                    for(j=l;j>=0;j--){
                //  cout<<cidao[j]<<" ";
                        buffer.append(cidao[j]+" ");
                    }
                    sum+=cidao[len-1]-cidao[0];
                }
                log.addLog("����ɨ������Ϊ: "+buffer.toString());
            }

            ave=sum/len;
            log.addLog("ƽ��Ѱ������:"+ave);
        //  cout<<endl;
        //  cout<<"ƽ��Ѱ������: "<<ave<<endl;

}

/*ɨ������㷨*/

  public void SCAN(int cidao[],int now) { //��Ҫ������ǰ�ŵ��ź��ƶ��۵��ƶ�����
    int k=1;
    int l,r,d=0;
    int i,j,sum=0;
    int a,len=0;
    char str[ ]=new char[100];
    float ave;
    for(i=0;i<cidao.length;i++)  { 
        //  cout<<cidao[i]<<" ";
        if(cidao[i]>0){
            len++;
        }
        }

     cidao=bubble(cidao,len);//����ð�������㷨����
    //cout<<"�����뵱ǰ�Ĵŵ���:";
       String s="";
       for(int z=0;z<len;z++){
          // System.out.println(cidao[z]);
           s+=cidao[z]+" ";
       }
       log.addLog("�ŵ����д�С��������Ϊ:"+s);

    if(cidao[len-1]<=now) //����ǰ�ŵ��Ŵ�����������������ߣ���ֱ����������
                        //���θ����������񣬴����ͬ���Ѱ������
    {
//   cout<<"����ɨ������Ϊ��";//���뵱ǰ�ŵ���
    StringBuffer buffer=new StringBuffer("");
     for(i=len-1;i>=0;i--){
    //  cout<<cidao[i]<<" ";
         buffer.append(cidao[i]+" ");
     }
      log.addLog("����ɨ������Ϊ: "+buffer.toString());
      sum=now-cidao[0];  
    }

    if(cidao[0]>=now)   //����ǰ�ŵ���С��������������С�ߣ���ֱ����������                
                                    //���θ����������񣬴����ͬ���Ѱ������
    {
        StringBuffer buffer=new StringBuffer("");
    // cout<<"����ɨ������Ϊ: ";
     for(i=0;i<len;i++){
    // cout<<cidao[i]<<" ";
         buffer.append(cidao[i]+" ");
     }
     log.addLog("����ɨ������Ϊ: "+buffer.toString());
     sum=cidao[len-1]-now;
    }

    if(now>cidao[0]&&now<cidao[len-1]) //����ǰ�ŵ��Ŵ���������������С����
                                     //С�������
    {
     StringBuffer buffer=new StringBuffer("");
      while(cidao[k]<now){

        k++;
    }
    l=k-1;
    r=k;
    try{
    String string=JOptionPane.showInputDialog(this, "�����뵱ǰ�ƶ��۵��ƶ��ķ���(1 ��ʾ����,0��ʾ����):", 
            "��ʾ", JOptionPane.INFORMATION_MESSAGE);
     d=Integer.parseInt(string);
        //cout<<"�����뵱ǰ�ƶ��۵��ƶ��ķ���(1 ��ʾ����,0��ʾ����):";
        //cin>>d;
        if(d==0)  //ѡ���ƶ��۷�������,��������ɨ��
        {
        // cout<<"����ɨ������Ϊ:";
         for(j=1;j>=0;j--){
        // cout<<cidao[j]<<" "; //�������ɨ�������
             buffer.append(cidao[j]+" ");
         }
         for(j=r;j<len;j++){  //��ͷ�ƶ�����С�ţ���ı䷽������ɨ��Ϊɨ��Ĵŵ�
          //cout<<cidao[j]<<" "; //�������ɨ�������
             buffer.append(cidao[j]+" ");
         }
         sum=now-2*cidao[0]+cidao[len-1];
        }
        else{ //ѡ���ƶ��۷������⣬��������ɨ��

        // cout<<"����ɨ������Ϊ:";
         for(j=r;j<len;j++){
         // cout<<cidao[j]<<" ";//�������ɨ�������
             buffer.append(cidao[j]+" ");
         }
         for(j=l;j>=0;j--){
        //  cout<<cidao[j]<<" ";
             buffer.append(cidao[j]+" ");
         }
         sum=now-cidao[0]+2*cidao[len-1];
        }
        log.addLog("����ɨ������Ϊ: "+buffer.toString());
        //cout<<endl;
        //cout<<"ƽ��Ѱ������: "<<ave<<endl;
    }catch(Exception e){
        log.addLog(e.toString());
        e.printStackTrace();
    }
    }
    ave= sum/len;
    log.addLog("ƽ��Ѱ������:"+ave);
    }

  public void CSCAN(int cidao[],int now) { //��Ҫ������ǰ�ŵ��ź��ƶ��۵��ƶ�����
	    int k=1;
	    int l,r,d=0;
	    int i,j,sum=0;
	    int a,len=0;
	    char str[ ]=new char[100];
	    float ave;
	    for(i=0;i<cidao.length;i++)  { 
	        //  cout<<cidao[i]<<" ";
	        if(cidao[i]>0){
	            len++;
	        }
	        }

	     cidao=bubble(cidao,len);//����ð�������㷨����
	    //cout<<"�����뵱ǰ�Ĵŵ���:";
	       String s="";
	       for(int z=0;z<len;z++){
	          // System.out.println(cidao[z]);
	           s+=cidao[z]+" ";
	       }
	       log.addLog("�ŵ����д�С��������Ϊ:"+s);

	    if(cidao[len-1]<=now) //����ǰ�ŵ��Ŵ�����������������ߣ���ֱ����������
	                        //���θ����������񣬴����ͬ���Ѱ������
	    {
	//   cout<<"����ɨ������Ϊ��";//���뵱ǰ�ŵ���
	    StringBuffer buffer=new StringBuffer("");
	     for(i=len-1;i>=0;i--){
	    //  cout<<cidao[i]<<" ";
	         buffer.append(cidao[i]+" ");
	     }
	      log.addLog("����ɨ������Ϊ: "+buffer.toString());
	      sum=now-cidao[0];  
	    }

	    if(cidao[0]>=now)   //����ǰ�ŵ���С��������������С�ߣ���ֱ����������                
	                                    //���θ����������񣬴����ͬ���Ѱ������
	    {
	        StringBuffer buffer=new StringBuffer("");
	     for(i=0;i<len;i++){
	         buffer.append(cidao[i]+" ");
	     }
	     log.addLog("����ɨ������Ϊ: "+buffer.toString());
	     sum=cidao[len-1]-now;
	    }

	    if(now>cidao[0]&&now<cidao[len-1]) //����ǰ�ŵ��Ŵ���������������С����
	                                     //С�������
	    {
	     StringBuffer buffer=new StringBuffer("");
	      while(cidao[k]<now){
	        k++;
	    }
	    l=k-1;
	    r=k;
	    try{
	
	         for(j=r;j<len;j++){
	             buffer.append(cidao[j]+" ");
	         }
	         for(j=1;j<r;j++){
	             buffer.append(cidao[j]+" ");
	         }
	         sum=now-cidao[0]+2*cidao[len-1];
	        log.addLog("����ɨ������Ϊ: "+buffer.toString());
 	    }catch(Exception e){
	        log.addLog(e.toString());
	        e.printStackTrace();
	    }
	    }
	    ave= sum/len;
	    log.addLog("ƽ��Ѱ������:"+ave);
	    }  
 
}

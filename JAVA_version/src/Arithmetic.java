package caozuoxitongkeshe;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class Arithmetic extends JPanel{
LogPane log;
public Arithmetic(){
    log=LogPane.getLog();
}   

/*判断输入数据是否有效*/
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

/*将字符串转换为数字*/

int trans(char str[],int a){
int i;
int sum=0;
for(i=0;i<a;i++){
    sum=sum+(int)((str[i]-'0')*Math.pow(10,a-i-1));
}
return sum;
}

/*冒泡排序算法*/
int []bubble(int cidao[],int m){
int i,j;
int temp;
for(i=0;i<m;i++)//使用冒泡法从小到大顺序排列
for(j=i+1;j<m;j++){
    if(cidao[i]>cidao[j]){

    temp=cidao[i];
    cidao[i]=cidao[j];
    cidao[j]=temp;

    }

}
return cidao;
}
/*先来先服务算法*/
public void FCFS(int cidao[],int now)  //磁道号数组，个数为M
{
    //int now;//当前磁道号
    int sum=0;//总寻道长度
    int i,j;
    int count=0;
    int len=0;

    float ave = 0;//平均寻道长度
            sum+=Math.abs(cidao[0]-now);
            count=count+1;
            System.out.println(sum);
            //cout<<"磁盘扫描序列为: ";
            String buffer="";
            for(i=0;i<cidao.length;i++)  {//输出磁盘扫描序列
            //  cout<<cidao[i]<<" ";
            if(cidao[i]>0){
                len++;
                buffer+=cidao[i]+" ";
            }
            }
            log.addLog("磁盘扫描序列为: "+buffer.toString());
            for(i=0,j=1;j<len;i++,j++){
                sum+=Math.abs(cidao[j]-cidao[i]);
                count++;
            }
            ave= sum/len;
            System.out.println("sum="+sum+"  count="+count);
            //cout<<endl;
            //cout<<"平均寻道长度: "<<ave<<endl;
            log.addLog("平均寻道长度:"+ave);

    }

/*最短寻道时间优先调度算法*/
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

           cidao=bubble(cidao,len); //调用冒泡排序算法排序
           String s="";
           for(int z=0;z<len;z++){
              // System.out.println(cidao[z]);
               s+=cidao[z]+" ";
           }
           log.addLog("磁道序列从小到大排序为:"+s);
            if(cidao[len-1]<=now) //若当前磁道号大于请求序列中最大者,则直接由外向内依次给予各请求服务
            {       String buffer="";
            //      cout<<"磁盘扫描序列为: ";
                    for(i=len-1;i>=0;i--){
                        buffer+=cidao[i]+" ";
                    }
                    log.addLog("磁盘扫描序列为: "+buffer.toString());
                    sum=now-cidao[0];
                    count=len;
            }

            if(cidao[0]>=now){ //若当前磁道号小于请求序列中最小者,则直接由内向外依次给予各请求服务;
                        String buffer="";
                        for(i=0;i<len;i++){
                            buffer+=cidao[i]+" ";
                        }
                    log.addLog("磁盘扫描序列为: "+buffer.toString());
                    sum=cidao[len-1]-now;
                    count=len;
            }

            if(now>cidao[0]&&now<cidao[len-1]) {//若当前磁道号大于当前请求序列中最小者并且小于最大者
            //  cout<<"磁盘扫描序列为: ";
                StringBuffer buffer=new StringBuffer("");
                while(cidao[k]<now){   //确定当前磁道在已排的序列中的位置
                    k++;    
                }
                l=k-1;
                r=k;
                while((l>=0)&&(r<len)){ //当前磁道在请求序列范围内
                    if(now-cidao[l]<=(cidao[r]-now)) {//选择与当前磁道最近的请求给予服务
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
                if(l==-1) {//磁头移动到序列的最小号，返回外侧扫描仍未扫描的磁道
                    for(j=r;j<len;j++){
            //      cout<<cidao[j]<<" ";
                        buffer.append(cidao[j]+" ");
                    }
                    sum+=cidao[len-1]-cidao[0];
                } 
                else{ //磁头移动到序列的最大号，返回内侧扫描仍未扫描的磁道
                    for(j=l;j>=0;j--){
                //  cout<<cidao[j]<<" ";
                        buffer.append(cidao[j]+" ");
                    }
                    sum+=cidao[len-1]-cidao[0];
                }
                log.addLog("磁盘扫描序列为: "+buffer.toString());
            }

            ave=sum/len;
            log.addLog("平均寻道长度:"+ave);
        //  cout<<endl;
        //  cout<<"平均寻道长度: "<<ave<<endl;

}

/*扫描调度算法*/

  public void SCAN(int cidao[],int now) { //先要给出当前磁道号和移动臂的移动方向
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

     cidao=bubble(cidao,len);//调用冒泡排序算法排序
    //cout<<"请输入当前的磁道号:";
       String s="";
       for(int z=0;z<len;z++){
          // System.out.println(cidao[z]);
           s+=cidao[z]+" ";
       }
       log.addLog("磁道序列从小到大排序为:"+s);

    if(cidao[len-1]<=now) //若当前磁道号大于请求序列中最大者，则直接由外向内
                        //依次给予各请求服务，此情况同最短寻道优先
    {
//   cout<<"磁盘扫描序列为：";//输入当前磁道号
    StringBuffer buffer=new StringBuffer("");
     for(i=len-1;i>=0;i--){
    //  cout<<cidao[i]<<" ";
         buffer.append(cidao[i]+" ");
     }
      log.addLog("磁盘扫描序列为: "+buffer.toString());
      sum=now-cidao[0];  
    }

    if(cidao[0]>=now)   //若当前磁道号小于请求序列中最小者，则直接由内向外                
                                    //依次给予各请求服务，此情况同最短寻道优先
    {
        StringBuffer buffer=new StringBuffer("");
    // cout<<"磁盘扫描序列为: ";
     for(i=0;i<len;i++){
    // cout<<cidao[i]<<" ";
         buffer.append(cidao[i]+" ");
     }
     log.addLog("磁盘扫描序列为: "+buffer.toString());
     sum=cidao[len-1]-now;
    }

    if(now>cidao[0]&&now<cidao[len-1]) //若当前磁道号大于请求序列中最小者且
                                     //小于最大者
    {
     StringBuffer buffer=new StringBuffer("");
      while(cidao[k]<now){

        k++;
    }
    l=k-1;
    r=k;
    try{
    String string=JOptionPane.showInputDialog(this, "请输入当前移动臂的移动的方向(1 表示向外,0表示向内):", 
            "提示", JOptionPane.INFORMATION_MESSAGE);
     d=Integer.parseInt(string);
        //cout<<"请输入当前移动臂的移动的方向(1 表示向外,0表示向内):";
        //cin>>d;
        if(d==0)  //选择移动臂方向向内,则先向内扫描
        {
        // cout<<"磁盘扫描序列为:";
         for(j=1;j>=0;j--){
        // cout<<cidao[j]<<" "; //输出向内扫描的序列
             buffer.append(cidao[j]+" ");
         }
         for(j=r;j<len;j++){  //磁头移动到最小号，则改变方向向内扫描为扫描的磁道
          //cout<<cidao[j]<<" "; //输出向外扫描的序列
             buffer.append(cidao[j]+" ");
         }
         sum=now-2*cidao[0]+cidao[len-1];
        }
        else{ //选择移动臂方向向外，则先向外扫描

        // cout<<"磁盘扫描序列为:";
         for(j=r;j<len;j++){
         // cout<<cidao[j]<<" ";//输出向外扫描的序列
             buffer.append(cidao[j]+" ");
         }
         for(j=l;j>=0;j--){
        //  cout<<cidao[j]<<" ";
             buffer.append(cidao[j]+" ");
         }
         sum=now-cidao[0]+2*cidao[len-1];
        }
        log.addLog("磁盘扫描序列为: "+buffer.toString());
        //cout<<endl;
        //cout<<"平均寻道长度: "<<ave<<endl;
    }catch(Exception e){
        log.addLog(e.toString());
        e.printStackTrace();
    }
    }
    ave= sum/len;
    log.addLog("平均寻道长度:"+ave);
    }

  public void CSCAN(int cidao[],int now) { //先要给出当前磁道号和移动臂的移动方向
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

	     cidao=bubble(cidao,len);//调用冒泡排序算法排序
	    //cout<<"请输入当前的磁道号:";
	       String s="";
	       for(int z=0;z<len;z++){
	          // System.out.println(cidao[z]);
	           s+=cidao[z]+" ";
	       }
	       log.addLog("磁道序列从小到大排序为:"+s);

	    if(cidao[len-1]<=now) //若当前磁道号大于请求序列中最大者，则直接由外向内
	                        //依次给予各请求服务，此情况同最短寻道优先
	    {
	//   cout<<"磁盘扫描序列为：";//输入当前磁道号
	    StringBuffer buffer=new StringBuffer("");
	     for(i=len-1;i>=0;i--){
	    //  cout<<cidao[i]<<" ";
	         buffer.append(cidao[i]+" ");
	     }
	      log.addLog("磁盘扫描序列为: "+buffer.toString());
	      sum=now-cidao[0];  
	    }

	    if(cidao[0]>=now)   //若当前磁道号小于请求序列中最小者，则直接由内向外                
	                                    //依次给予各请求服务，此情况同最短寻道优先
	    {
	        StringBuffer buffer=new StringBuffer("");
	     for(i=0;i<len;i++){
	         buffer.append(cidao[i]+" ");
	     }
	     log.addLog("磁盘扫描序列为: "+buffer.toString());
	     sum=cidao[len-1]-now;
	    }

	    if(now>cidao[0]&&now<cidao[len-1]) //若当前磁道号大于请求序列中最小者且
	                                     //小于最大者
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
	        log.addLog("磁盘扫描序列为: "+buffer.toString());
 	    }catch(Exception e){
	        log.addLog(e.toString());
	        e.printStackTrace();
	    }
	    }
	    ave= sum/len;
	    log.addLog("平均寻道长度:"+ave);
	    }  
 
}

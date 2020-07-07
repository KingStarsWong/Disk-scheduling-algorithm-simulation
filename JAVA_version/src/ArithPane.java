package caozuoxitongkeshe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
public class ArithPane extends JPanel implements ActionListener{
JButton button1,button2,button3,backButton,button4;
Font font=new Font("微软雅黑",Font.PLAIN,16);
JLabel label;
LogPane log;
Arithmetic arith;
//int tem[]=new int[1000];
int now=0;
    public ArithPane() {
        // TODO 自动生成的构造函数存根
        //tem=DiskOperation.getCidao();
        init();      
    }
    public void init(){
        try { 
               UIManager
                 .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
              } catch (Exception e) {
               e.printStackTrace();
              }
        this.setLayout(null);
      
        log=LogPane.getLog();
        arith=new Arithmetic();
        label=new JLabel("请选择以下算法来模拟磁盘调度:");
        Font font1=new Font("微软雅黑",Font.BOLD,16);
        label.setFont(font1);
        label.setBounds(20, 20, 350, 30);

        backButton=new JButton("返回");
        backButton.setFont(font);
        backButton.setBounds(530, 15, 80, 30);
        backButton.addActionListener(this);

        button1=new JButton("先来先服务算法");
        button1.setFont(font);
        button1.setBounds(170, 90, 250, 30);
        button1.addActionListener(this);

        button2=new JButton("最短寻道优先算法");
        button2.setFont(font);
        button2.setBounds(170, 150, 250, 30);
        button2.addActionListener(this);

        button3=new JButton("扫描(电梯调度)算法");
        button3.setFont(font);
        button3.setBounds(170, 210, 250, 30);
        button3.addActionListener(this);
        
        button4=new JButton("循环扫描算法");
        button4.setFont(font);
        button4.setBounds(170, 270, 250, 30);
        button4.addActionListener(this);

        this.add(backButton);
        this.add(label);
        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(button4);
        this.setVisible(true);

        }
    @Override
    public void actionPerformed(ActionEvent e) {
        int[] cidao=DiskOperation.getCidao();
        // TODO 自动生成的方法存根
        if(e.getSource()==backButton){
            DiskOperation.showCard();
            log.addLog("返回到主界面!");
        }

       if(e.getSource()==button1){
             int []tem = new int[cidao.length];
            for(int i=0;i<tem.length;i++){
                tem[i]=cidao[i];
            }
            int len=JOptionPane.showConfirmDialog(this, "你选择的是先来先服务算法", 
                    "提示", JOptionPane.YES_NO_OPTION);
            if(len==JOptionPane.OK_OPTION){
                try{
                String str=JOptionPane.showInputDialog(this, "请输入当前磁道号", 
                        "提示", JOptionPane.INFORMATION_MESSAGE);

                now=Integer.parseInt(str);
                log.addLog("正在模拟先来先服务算法...");
            //  log.addLog("请求磁道序列为:"+cidao);
                log.addLog("当前磁道号为:"+now);
                arith.FCFS(tem,now);

                }catch(Exception e1){
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(this, "输入类型错误!", "提示", 
                            JOptionPane.WARNING_MESSAGE);
                    log.addLog(e1.toString());
                }

            } 


            }
       if(e.getSource()==button2){
           int []tem = new int[cidao.length];
           for(int i=0;i<tem.length;i++){
                tem[i]=cidao[i];
            }
           int len=JOptionPane.showConfirmDialog(this, "你选择的是最短寻道优先算法", 
                    "提示", JOptionPane.YES_NO_OPTION);
            if(len==JOptionPane.OK_OPTION){
                try{
                String str=JOptionPane.showInputDialog(this, "请输入当前磁道号", 
                        "提示", JOptionPane.INFORMATION_MESSAGE);

                now=Integer.parseInt(str);
                log.addLog("正在模拟最短寻道优先算法...");
            //  log.addLog("请求磁道序列为:"+cidao);
                log.addLog("当前磁道号为:"+now);
                arith.SSTF(tem,now);

                }catch(Exception e1){
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(this, "输入类型错误!", "提示", 
                            JOptionPane.WARNING_MESSAGE);
                    log.addLog(e1.toString());
                }

            } 

       }
       if(e.getSource()==button3){
           int []tem = new int[cidao.length];
           for(int i=0;i<tem.length;i++){
                tem[i]=cidao[i];
            }
           int len=JOptionPane.showConfirmDialog(this, "你选择的是扫描(电梯调度)算法", 
                    "提示", JOptionPane.YES_NO_OPTION);
            if(len==JOptionPane.OK_OPTION){
                try{
                String str=JOptionPane.showInputDialog(this, "请输入当前磁道号", 
                        "提示", JOptionPane.INFORMATION_MESSAGE);

                now=Integer.parseInt(str);
                log.addLog("正在模拟扫描(电梯调度)算法...");
            //  log.addLog("请求磁道序列为:"+cidao);
                log.addLog("当前磁道号为:"+now);
                arith.SCAN(tem, now);

                }catch(Exception e1){
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(this, "输入类型错误!", "提示", 
                            JOptionPane.WARNING_MESSAGE);
                    log.addLog(e1.toString());
                }

            }   

       }
       if(e.getSource()==button4){
           int []tem = new int[cidao.length];
           for(int i=0;i<tem.length;i++){
                tem[i]=cidao[i];
            }
           int len=JOptionPane.showConfirmDialog(this, "你选择的是循环扫描算法", 
                    "提示", JOptionPane.YES_NO_OPTION);
            if(len==JOptionPane.OK_OPTION){
                try{
                String str=JOptionPane.showInputDialog(this, "请输入当前磁道号", 
                        "提示", JOptionPane.INFORMATION_MESSAGE);

                now=Integer.parseInt(str);
                log.addLog("正在模拟循环扫描算法...");
            //  log.addLog("请求磁道序列为:"+cidao);
                log.addLog("当前磁道号为:"+now);
                arith.CSCAN(tem, now);

                }catch(Exception e1){
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(this, "输入类型错误!", "提示", 
                            JOptionPane.WARNING_MESSAGE);
                    log.addLog(e1.toString());
                }

            }   

       }

    }

}

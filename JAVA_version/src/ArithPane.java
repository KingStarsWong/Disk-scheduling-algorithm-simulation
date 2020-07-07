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
Font font=new Font("΢���ź�",Font.PLAIN,16);
JLabel label;
LogPane log;
Arithmetic arith;
//int tem[]=new int[1000];
int now=0;
    public ArithPane() {
        // TODO �Զ����ɵĹ��캯�����
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
        label=new JLabel("��ѡ�������㷨��ģ����̵���:");
        Font font1=new Font("΢���ź�",Font.BOLD,16);
        label.setFont(font1);
        label.setBounds(20, 20, 350, 30);

        backButton=new JButton("����");
        backButton.setFont(font);
        backButton.setBounds(530, 15, 80, 30);
        backButton.addActionListener(this);

        button1=new JButton("�����ȷ����㷨");
        button1.setFont(font);
        button1.setBounds(170, 90, 250, 30);
        button1.addActionListener(this);

        button2=new JButton("���Ѱ�������㷨");
        button2.setFont(font);
        button2.setBounds(170, 150, 250, 30);
        button2.addActionListener(this);

        button3=new JButton("ɨ��(���ݵ���)�㷨");
        button3.setFont(font);
        button3.setBounds(170, 210, 250, 30);
        button3.addActionListener(this);
        
        button4=new JButton("ѭ��ɨ���㷨");
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
        // TODO �Զ����ɵķ������
        if(e.getSource()==backButton){
            DiskOperation.showCard();
            log.addLog("���ص�������!");
        }

       if(e.getSource()==button1){
             int []tem = new int[cidao.length];
            for(int i=0;i<tem.length;i++){
                tem[i]=cidao[i];
            }
            int len=JOptionPane.showConfirmDialog(this, "��ѡ����������ȷ����㷨", 
                    "��ʾ", JOptionPane.YES_NO_OPTION);
            if(len==JOptionPane.OK_OPTION){
                try{
                String str=JOptionPane.showInputDialog(this, "�����뵱ǰ�ŵ���", 
                        "��ʾ", JOptionPane.INFORMATION_MESSAGE);

                now=Integer.parseInt(str);
                log.addLog("����ģ�������ȷ����㷨...");
            //  log.addLog("����ŵ�����Ϊ:"+cidao);
                log.addLog("��ǰ�ŵ���Ϊ:"+now);
                arith.FCFS(tem,now);

                }catch(Exception e1){
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(this, "�������ʹ���!", "��ʾ", 
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
           int len=JOptionPane.showConfirmDialog(this, "��ѡ��������Ѱ�������㷨", 
                    "��ʾ", JOptionPane.YES_NO_OPTION);
            if(len==JOptionPane.OK_OPTION){
                try{
                String str=JOptionPane.showInputDialog(this, "�����뵱ǰ�ŵ���", 
                        "��ʾ", JOptionPane.INFORMATION_MESSAGE);

                now=Integer.parseInt(str);
                log.addLog("����ģ�����Ѱ�������㷨...");
            //  log.addLog("����ŵ�����Ϊ:"+cidao);
                log.addLog("��ǰ�ŵ���Ϊ:"+now);
                arith.SSTF(tem,now);

                }catch(Exception e1){
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(this, "�������ʹ���!", "��ʾ", 
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
           int len=JOptionPane.showConfirmDialog(this, "��ѡ�����ɨ��(���ݵ���)�㷨", 
                    "��ʾ", JOptionPane.YES_NO_OPTION);
            if(len==JOptionPane.OK_OPTION){
                try{
                String str=JOptionPane.showInputDialog(this, "�����뵱ǰ�ŵ���", 
                        "��ʾ", JOptionPane.INFORMATION_MESSAGE);

                now=Integer.parseInt(str);
                log.addLog("����ģ��ɨ��(���ݵ���)�㷨...");
            //  log.addLog("����ŵ�����Ϊ:"+cidao);
                log.addLog("��ǰ�ŵ���Ϊ:"+now);
                arith.SCAN(tem, now);

                }catch(Exception e1){
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(this, "�������ʹ���!", "��ʾ", 
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
           int len=JOptionPane.showConfirmDialog(this, "��ѡ�����ѭ��ɨ���㷨", 
                    "��ʾ", JOptionPane.YES_NO_OPTION);
            if(len==JOptionPane.OK_OPTION){
                try{
                String str=JOptionPane.showInputDialog(this, "�����뵱ǰ�ŵ���", 
                        "��ʾ", JOptionPane.INFORMATION_MESSAGE);

                now=Integer.parseInt(str);
                log.addLog("����ģ��ѭ��ɨ���㷨...");
            //  log.addLog("����ŵ�����Ϊ:"+cidao);
                log.addLog("��ǰ�ŵ���Ϊ:"+now);
                arith.CSCAN(tem, now);

                }catch(Exception e1){
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(this, "�������ʹ���!", "��ʾ", 
                            JOptionPane.WARNING_MESSAGE);
                    log.addLog(e1.toString());
                }

            }   

       }

    }

}

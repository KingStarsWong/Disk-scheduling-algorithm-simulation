package caozuoxitongkeshe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
public class DiskOperation extends JFrame implements ActionListener
{
ImageIcon img;
JSplitPane vsplitPane;
JSplitPane hsplitPane;
LogPane log;
JButton sureButton;
JPanel centerPane;
JPanel southPane;
JPanel pane;
static JPanel controlPane;
static CardLayout card;
Font font=new Font("΢���ź�",Font.PLAIN,16);
JLabel label;
JTextField textField;
ArithPane arithPane;
static  int cidao[];
public DiskOperation( )
{

 try { 
       UIManager
         .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      } catch (Exception e) {
       e.printStackTrace();
      } 

try {
    init();
} catch (Exception e) {
    // TODO �Զ����ɵ� catch ��
    log.addLog(e.toString());
}
}


private void setBorder(TitledBorder titledBorder) {
    // TODO Auto-generated method stub

}


public void init() throws Exception{
arithPane=new ArithPane();  
card=new CardLayout();
controlPane=new JPanel();
controlPane.setLayout(card);
controlPane.add("����", arithPane);
cidao=new int[1000];

textField=new JTextField();
textField.setFont(font);
textField.setBounds(150, 140, 300, 30);

label=new JLabel("������Ҫ�������Ĵŵ�����(�Կո����)");
label.setFont(font);
label.setBounds(140, 60, 350, 30);

log=LogPane.getLog();
log.addLog("��ӭ����ģ����̵��Ƚ���!");

pane=new JPanel();
pane.setLayout(new BorderLayout());

controlPane.add("���һ", pane);
card.show(controlPane,"���һ");

centerPane=new JPanel();
centerPane.setLayout(null);
centerPane.add(label);
centerPane.add(textField);

sureButton=new JButton("ȷ��");
sureButton.setFont(font);
sureButton.addActionListener(this);

southPane=new JPanel();
southPane.setLayout(new FlowLayout());
southPane.add(sureButton);


pane.add(southPane,BorderLayout.SOUTH);
pane.add(centerPane);

vsplitPane=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
vsplitPane.setTopComponent(new JScrollPane(controlPane));
vsplitPane.setBottomComponent( log);
vsplitPane.setDividerLocation(320);
//button.addActionListener(this);
vsplitPane.setEnabled(false);

int screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;  
int screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;  
add(vsplitPane);
setSize(630,530);
setLocation((screen_width - this.getWidth()) / 2,  
        (screen_height - this.getHeight()) / 2);  
setBackground(Color.LIGHT_GRAY);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
setBorder(new TitledBorder("ģ����̵���"));
setResizable(false);
setTitle("ģ����̵����㷨");  
}

public void actionPerformed(ActionEvent e){

  if(e.getSource()==sureButton){

      String str=textField.getText();
      if(str.trim().equals("")){ 
          JOptionPane.showMessageDialog(this, "�ŵ������в���Ϊ��!", "��ʾ", 
                  JOptionPane.WARNING_MESSAGE);
      }
      else{
          try{
              String[] buffer=str.split(" ");
              if(buffer.length>0){
                  for(int i=0;i<buffer.length;i++){
                      cidao[i]=Integer.parseInt(buffer[i]);

                  }
                  log.addLog("������Ĵŵ�����Ϊ:"+str);
                  card.show(controlPane, "����");
              }
              else{
                  JOptionPane.showMessageDialog(this, "����ĸ�ʽ����", "��ʾ", 
                          JOptionPane.WARNING_MESSAGE);
              }

          }
          catch(Exception e1){
              e1.printStackTrace();
              JOptionPane.showMessageDialog(this, "����ĸ�ʽ����", "��ʾ", 
                      JOptionPane.WARNING_MESSAGE);
          }
      }
  }
 }

public static  int[] getCidao(){
    return cidao;
}


public  static void showCard(){
    card.show(controlPane,"���һ");
}
public static void main(String[]args){

    new DiskOperation();
}

}
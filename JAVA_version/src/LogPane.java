package caozuoxitongkeshe;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Date;
public class LogPane extends JTabbedPane implements ActionListener{
private JScrollPane scroll;
private JTextArea textArea;
static final LogPane log=new LogPane();
    public LogPane() {
        // TODO 自动生成的构造函数存根
        try {  
               UIManager
                 .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
              } catch (Exception e) {
               e.printStackTrace();
              }

        textArea=new JTextArea();
        textArea.setLineWrap(true);
        textArea.setFont(new Font("TimesRoman",Font.PLAIN,18));
        scroll=new JScrollPane(textArea);
        add(scroll,BorderLayout.CENTER);
        this.setTitleAt(0, "Log");
        //this.setTitleAt(1, "sddsf");
        //this.setFont(new Font("TimesRoman",Font.BOLD,16));
        this.setFont(new Font("微软雅黑",Font.BOLD,16));
        this.setEnabled(false);
        textArea.setEditable(false);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO 自动生成的方法存根

    }

    public void addLog(String log){
        Date current = new  Date(System.currentTimeMillis());
        textArea.append(log+"    "+current+"\n");
    }

    public static LogPane getLog(){

        return log;
    }

    public void addLog(String str,Color color){
        Date date=new Date();
        textArea.setForeground(color);
        textArea.append(str+"\t"+date+"\n");
    }
}

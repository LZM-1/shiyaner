package 疫情上报系统;

import java.sql.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
public class show extends JDialog implements ActionListener{
    Hashtable 信息= null;
    JTextArea 显示=null;
    FileInputStream inOne=null;
    ObjectInputStream inTwo=null;
    File file=new File("基本信息。txt");
    public show(File file){               //构造方法
        super(new JFrame(),"显示对话框");
        this.file=file;
        显示=new JTextArea(16,30);        //显示一个文本区域
        try{
            inOne=new FileInputStream(file);
            inTwo=new ObjectInputStream(inOne);
            信息=(Hashtable)inTwo.readObject();
            inOne.close();
            inTwo.close();
        }
        catch(Exception ee){}
        if(信息.isEmpty())显示.append("目前还没有学生的信息记录！\n");
        else{
            显示.setText("姓名\t性别\t所在省\t所在学院\t是否发热\t是否接触\t联系电话\n");
            for(Enumeration enm=信息.elements();enm.hasMoreElements();)
            {
                信息 stu=(信息)enm.nextElement();
                String sex="";
                if(stu.getSex().equals("男"))sex="男";
                else sex="女";
                String str=stu.getNumber()+"\t"+stu.getName()+"\t"+sex+"\t"
                        +stu.getFever()+"\t"stu.getcontact()+"\tstu.getProvince()+\"\t+stu.getGrade()+"\t"
             		   +stu.age+"\t"+stu.getLocation()+"\t"+stu.getTelephone()+"\n";
                显示.append(str);
            }
        }
        JScrollPane scroll=new JScrollPane(显示);   //滚动面板
        Container con=getContentPane();      //获得内容面板
        con.add("Center",scroll);            //添加中心滚动
        con.validate();
        setVisible(true);
        setBounds(200,200,1000,700);        //定义区域坐标
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){setVisible(false);}
        }   //窗口被完全关闭时调用的方法
        );
    }
    public void actionPerformed(ActionEvent e){
        new show(file);      //接收监听器接口
    }
}

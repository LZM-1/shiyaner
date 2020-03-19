package 疫情上报系统;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
public class inquest extends JDialog implements ActionListener
{ 
  Hashtable 信息=null;
  JTextField 学号,姓名,是否发热,是否接触,所在省,所在学院,联系电话;                 
  JRadioButton 男,女;
  JButton 查询;
  ButtonGroup group=null;
  FileInputStream inOne=null;
  ObjectInputStream inTwo=null;
  File file=null;                                           
  public inquest(JFrame f,File file)
  {
   super(f,"查询对话框",false);                           
   this.file=file;
   学号=new JTextField(10);
   查询=new JButton("查询");
   学号.addActionListener(this);
   查询.addActionListener(this);
   姓名=new JTextField(10);
   姓名.setEditable(false);
   是否发热=new JTextField(10);
   是否发热.setEditable(false);
   是否接触=new JTextField(10);
   是否接触.setEditable(false);
   联系电话=new JTextField(10);
   联系电话.setEditable(false);
   男=new JRadioButton("男",false);
   女=new JRadioButton("女",false);
   group=new ButtonGroup();
   group.add(男);
   group.add(女);
   Box box1=Box.createHorizontalBox();              
   box1.add(new JLabel("学号:",JLabel.CENTER));
   box1.add(学号);
   box1.add(查询);
   Box box2=Box.createHorizontalBox();              
   box2.add(new JLabel("姓名:",JLabel.CENTER));
   box2.add(姓名);
   Box box3=Box.createHorizontalBox();              
   box3.add(new JLabel("性别:",JLabel.CENTER));
   box3.add(男);
   box3.add(女);
   Box box4=Box.createHorizontalBox();              
   box4.add(new JLabel("是否发热:",JLabel.CENTER));
   box4.add(是否发热);
   Box box5=Box.createHorizontalBox();              
   box5.add(new JLabel("是否接触:",JLabel.CENTER));
   box5.add(是否接触);            
   Box box6=Box.createHorizontalBox();              
   box6.add(new JLabel("联系电话:",JLabel.CENTER));
   box6.add(联系电话); 
   Box box7=Box.createHorizontalBox();              
   box6.add(new JLabel("所在省:",JLabel.CENTER));
   box6.add(所在省);
   Box box8=Box.createHorizontalBox();              
   box6.add(new JLabel("所在学院:",JLabel.CENTER));
   box6.add(所在学院);
   Box boxH=Box.createVerticalBox(); 
   box1.add(box1);
   box2.add(box2);
   box3.add(box3);
   box4.add(box4);
   box5.add(box5);
   box6.add(box6);
   box7.add(box6);
   box8.add(Box.createVerticalGlue());          
   JPanel pCenter=new JPanel();
   pCenter.add(box1);
   Container con=getContentPane();
   con.add(pCenter,BorderLayout.CENTER);
   con.validate();
   setVisible(false);
   setBounds(100,200,400,500);
   addWindowListener(new WindowAdapter()
                    { public void windowClosing(WindowEvent e)
                       {
                         setVisible(false);
      	               }
                    });
  }
 public void actionPerformed(ActionEvent e)
  {    
     姓名.setText(null);
     是否发热.setText(null);
     是否接触.setText(null);
     联系电话.setText(null);
     所在省.setText(null);
     所在学院.setText(null);
    if(e.getSource()==查询||e.getSource()==学号)
      {
         String number="";
         number=学号.getText();
         if(number.length()>0)
            {
              try {
                    inOne=new FileInputStream(file);
                    inTwo=new ObjectInputStream(inOne);
                    信息=(Hashtable)inTwo.readObject();
                    inOne.close();
                    inTwo.close();
                  }
               catch(Exception ee)
                   {
                   }
              if(信息.containsKey(number))          
                 {
                   Student stu=(Student)信息.get(number);
                   姓名.setText(stu.getName());
                   是否发热.setText(stu.getFever());
                   是否接触.setText(stu.getInfected());
                   联系电话.setText(stu.getTelephone());
                   所在省.setText(stu.getProvince());
                   所在学院.setText(stu.getAcademe());
                   if(stu.getSex().equals("男"))
                      {
                        男.setSelected(true);
                      }
                    else
                      {
                        女.setSelected(true);
                      }
   
                 }
              else
                 { 
                  String warning="该学号不存在!";
                  JOptionPane.showMessageDialog(this,warning,"警告",JOptionPane.WARNING_MESSAGE);
                 }
            }
        else
            { 
              String warning="必须要输入学号!";
              JOptionPane.showMessageDialog(this,warning,"警告",JOptionPane.WARNING_MESSAGE);
            }
      } 
  }}
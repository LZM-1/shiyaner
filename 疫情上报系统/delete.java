package 疫情上报系统;

import java.sql.*;
import java.util.*;
public class delete extends JPanel implements ActionListener
{ 
  Hashtable 信息=null;                           
  JTextField 学号,姓名,性别,所在省,所在学院;                 
  JRadioButton 男,女;
  JButton 删除;
  ButtonGroup group=null;
  FileInputStream inOne=null;
  ObjectInputStream inTwo=null;
  FileOutputStream outOne=null;
  ObjectOutputStream outTwo=null;
  File file=null;                                           
  public delete(File file)
  {
   this.file=file;
   学号=new JTextField(10);
   删除=new JButton("删除");
   学号.addActionListener(this);
   删除.addActionListener(this);
   姓名=new JTextField(10);
   姓名.setEditable(false);
   性别=new JTextField(10);
   性别.setEditable(false);
   所在省=new JTextField(10);
   所在省.setEditable(false);
   所在学院=new JTextField(10);
   所在学院.setEditable(false);
   男=new JRadioButton("男",false);
   女=new JRadioButton("女",false);
   group=new ButtonGroup();
   group.add(男);
   group.add(女);
   Box box1=Box.createHorizontalBox();              
   box1.add(new JLabel("输入要删除的学号:",JLabel.CENTER));
   box1.add(学号);
   box1.add(删除);
   Box box2=Box.createHorizontalBox();              
   box2.add(new JLabel("姓名:",JLabel.CENTER));
   box2.add(姓名);
   Box box3=Box.createHorizontalBox();              
   box3.add(new JLabel("性别:",JLabel.CENTER));
   box3.add(男);
   box3.add(女);
   Box box4=Box.createHorizontalBox();              
   box4.add(new JLabel("所在省:",JLabel.CENTER));
   box4.add(所在省);
   Box box5=Box.createHorizontalBox();              
   box5.add(new JLabel("所在学院:",JLabel.CENTER));
   box5.add(所在学院);
   Box box6=Box.createHorizontalBox();                       
   Box boxH=Box.createVerticalBox();              
   boxH.add(box1);
   boxH.add(box2);
   boxH.add(box3);
   boxH.add(box4);
   boxH.add(box5);
   boxH.add(box6);
   boxH.add(Box.createVerticalGlue());          
   信息 pCenter=new 信息();
   pCenter.add(boxH);
   setLayout(new BorderLayout());
   add(pCenter,BorderLayout.CENTER);
   validate();
  }
 public void actionPerformed(ActionEvent e)
  {
    if(e.getSource()==删除||e.getSource()==学号)
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
                   性别.setText(stu.getDisciping());
                   学号.setText(stu.getSex());
                   所在省.setText(stu.getprovince());
                                  所在学院.setText(stu.getacademe());
                   if(stu.getSex().equals("男"))
                      {
                        男.setSelected(true);
                      }
                    else
                      {
                        女.setSelected(true);
                      }
                  String m="确定要删除该学号及全部信息吗?";
                  int ok=JOptionPane.showConfirmDialog(this,m,"确认",JOptionPane.YES_NO_OPTION,
                                                 JOptionPane.QUESTION_MESSAGE);
                  if(ok==JOptionPane.YES_OPTION)
                     {
                       信息.remove(number);
                       try
                        {
                          outOne=new FileOutputStream(file);
                          outTwo=new ObjectOutputStream(outOne);
                          outTwo.writeObject(信息);
                          outTwo.close();
                          outOne.close();
                          学号.setText(null);
                          姓名.setText(null);                                
                                               性别.setText(null);
                                               所在省.setText(null);
                                               所在学院.setText(null);
                        }
                       catch(Exception ee)
                        { 
                         System.out.println(ee);
                        }
                     }
                   else if(ok==JOptionPane.NO_OPTION)
                     {
                	    学号.setText(null);
                        姓名.setText(null);
                                           性别.setText(null);
                                           所在省.setText(null);
                                           所在学院.setText(null);
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
  }
}
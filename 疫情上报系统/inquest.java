package �����ϱ�ϵͳ;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
public class inquest extends JDialog implements ActionListener
{ 
  Hashtable ��Ϣ=null;
  JTextField ѧ��,����,�Ƿ���,�Ƿ�Ӵ�,����ʡ,����ѧԺ,��ϵ�绰;                 
  JRadioButton ��,Ů;
  JButton ��ѯ;
  ButtonGroup group=null;
  FileInputStream inOne=null;
  ObjectInputStream inTwo=null;
  File file=null;                                           
  public inquest(JFrame f,File file)
  {
   super(f,"��ѯ�Ի���",false);                           
   this.file=file;
   ѧ��=new JTextField(10);
   ��ѯ=new JButton("��ѯ");
   ѧ��.addActionListener(this);
   ��ѯ.addActionListener(this);
   ����=new JTextField(10);
   ����.setEditable(false);
   �Ƿ���=new JTextField(10);
   �Ƿ���.setEditable(false);
   �Ƿ�Ӵ�=new JTextField(10);
   �Ƿ�Ӵ�.setEditable(false);
   ��ϵ�绰=new JTextField(10);
   ��ϵ�绰.setEditable(false);
   ��=new JRadioButton("��",false);
   Ů=new JRadioButton("Ů",false);
   group=new ButtonGroup();
   group.add(��);
   group.add(Ů);
   Box box1=Box.createHorizontalBox();              
   box1.add(new JLabel("ѧ��:",JLabel.CENTER));
   box1.add(ѧ��);
   box1.add(��ѯ);
   Box box2=Box.createHorizontalBox();              
   box2.add(new JLabel("����:",JLabel.CENTER));
   box2.add(����);
   Box box3=Box.createHorizontalBox();              
   box3.add(new JLabel("�Ա�:",JLabel.CENTER));
   box3.add(��);
   box3.add(Ů);
   Box box4=Box.createHorizontalBox();              
   box4.add(new JLabel("�Ƿ���:",JLabel.CENTER));
   box4.add(�Ƿ���);
   Box box5=Box.createHorizontalBox();              
   box5.add(new JLabel("�Ƿ�Ӵ�:",JLabel.CENTER));
   box5.add(�Ƿ�Ӵ�);            
   Box box6=Box.createHorizontalBox();              
   box6.add(new JLabel("��ϵ�绰:",JLabel.CENTER));
   box6.add(��ϵ�绰); 
   Box box7=Box.createHorizontalBox();              
   box6.add(new JLabel("����ʡ:",JLabel.CENTER));
   box6.add(����ʡ);
   Box box8=Box.createHorizontalBox();              
   box6.add(new JLabel("����ѧԺ:",JLabel.CENTER));
   box6.add(����ѧԺ);
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
     ����.setText(null);
     �Ƿ���.setText(null);
     �Ƿ�Ӵ�.setText(null);
     ��ϵ�绰.setText(null);
     ����ʡ.setText(null);
     ����ѧԺ.setText(null);
    if(e.getSource()==��ѯ||e.getSource()==ѧ��)
      {
         String number="";
         number=ѧ��.getText();
         if(number.length()>0)
            {
              try {
                    inOne=new FileInputStream(file);
                    inTwo=new ObjectInputStream(inOne);
                    ��Ϣ=(Hashtable)inTwo.readObject();
                    inOne.close();
                    inTwo.close();
                  }
               catch(Exception ee)
                   {
                   }
              if(��Ϣ.containsKey(number))          
                 {
                   Student stu=(Student)��Ϣ.get(number);
                   ����.setText(stu.getName());
                   �Ƿ���.setText(stu.getFever());
                   �Ƿ�Ӵ�.setText(stu.getInfected());
                   ��ϵ�绰.setText(stu.getTelephone());
                   ����ʡ.setText(stu.getProvince());
                   ����ѧԺ.setText(stu.getAcademe());
                   if(stu.getSex().equals("��"))
                      {
                        ��.setSelected(true);
                      }
                    else
                      {
                        Ů.setSelected(true);
                      }
   
                 }
              else
                 { 
                  String warning="��ѧ�Ų�����!";
                  JOptionPane.showMessageDialog(this,warning,"����",JOptionPane.WARNING_MESSAGE);
                 }
            }
        else
            { 
              String warning="����Ҫ����ѧ��!";
              JOptionPane.showMessageDialog(this,warning,"����",JOptionPane.WARNING_MESSAGE);
            }
      } 
  }}
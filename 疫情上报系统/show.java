package �����ϱ�ϵͳ;

import java.sql.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
public class show extends JDialog implements ActionListener{
    Hashtable ��Ϣ= null;
    JTextArea ��ʾ=null;
    FileInputStream inOne=null;
    ObjectInputStream inTwo=null;
    File file=new File("������Ϣ��txt");
    public show(File file){               //���췽��
        super(new JFrame(),"��ʾ�Ի���");
        this.file=file;
        ��ʾ=new JTextArea(16,30);        //��ʾһ���ı�����
        try{
            inOne=new FileInputStream(file);
            inTwo=new ObjectInputStream(inOne);
            ��Ϣ=(Hashtable)inTwo.readObject();
            inOne.close();
            inTwo.close();
        }
        catch(Exception ee){}
        if(��Ϣ.isEmpty())��ʾ.append("Ŀǰ��û��ѧ������Ϣ��¼��\n");
        else{
            ��ʾ.setText("����\t�Ա�\t����ʡ\t����ѧԺ\t�Ƿ���\t�Ƿ�Ӵ�\t��ϵ�绰\n");
            for(Enumeration enm=��Ϣ.elements();enm.hasMoreElements();)
            {
                ��Ϣ stu=(��Ϣ)enm.nextElement();
                String sex="";
                if(stu.getSex().equals("��"))sex="��";
                else sex="Ů";
                String str=stu.getNumber()+"\t"+stu.getName()+"\t"+sex+"\t"
                        +stu.getFever()+"\t"stu.getcontact()+"\tstu.getProvince()+\"\t+stu.getGrade()+"\t"
             		   +stu.age+"\t"+stu.getLocation()+"\t"+stu.getTelephone()+"\n";
                ��ʾ.append(str);
            }
        }
        JScrollPane scroll=new JScrollPane(��ʾ);   //�������
        Container con=getContentPane();      //����������
        con.add("Center",scroll);            //������Ĺ���
        con.validate();
        setVisible(true);
        setBounds(200,200,1000,700);        //������������
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){setVisible(false);}
        }   //���ڱ���ȫ�ر�ʱ���õķ���
        );
    }
    public void actionPerformed(ActionEvent e){
        new show(file);      //���ռ������ӿ�
    }
}

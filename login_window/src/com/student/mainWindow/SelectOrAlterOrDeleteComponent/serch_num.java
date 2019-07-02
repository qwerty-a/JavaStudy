package com.student.mainWindow.SelectOrAlterOrDeleteComponent;

import com.student.sqlServer_m;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class serch_num extends JPanel implements ActionListener{
    JPanel jp;
    serch_result s_result;
    alter_result a_result;
    sqlServer_m sqlserver;
    PreparedStatement PreSql;
    ResultSet rs ;
    JLabel jlb_num;
    JLabel jlb_name;
    JLabel jlb_class;
    JTextField jtf_num;
    JTextField jtf_name;
    JTextField jtf_class;
    JButton jb_result;
    String num;
    String name;
    String Class;
    public boolean IsInHere=false;

    public serch_num(){
        s_result=new serch_result();
        a_result=new alter_result();
        jp=new JPanel();
        GridBagLayout gridBagLayout=new GridBagLayout();
        jp.setLayout(gridBagLayout);
        GridBagConstraints gbc=new GridBagConstraints();

        jlb_num=new JLabel("学号");
        jlb_name=new JLabel("姓名");
        jlb_class=new JLabel("班级");
        jtf_num=new JTextField(20);
        jtf_num.setEditable(false);
        jtf_name=new JTextField(20);
        jtf_name.setEditable(false);
        jtf_class=new JTextField(20);
        jtf_class.setEditable(false);
        jb_result=new JButton("查询");
        sqlserver=new sqlServer_m();

        Font font = new Font("宋体", Font.BOLD, 18);
        jlb_name.setFont(font);
        jlb_num.setFont(font);
        jlb_class.setFont(font);
        jtf_name.setFont(font);
        jtf_num.setFont(font);
        jtf_class.setFont(font);
        jb_result.setFont(font);

        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gridBagLayout.setConstraints(jlb_num,gbc);
        gbc.gridx=1;
        gridBagLayout.setConstraints(jlb_name,gbc);
        gbc.gridx=2;
        gridBagLayout.setConstraints(jlb_class,gbc);

        gbc.gridx=0;
        gbc.gridy=1;
        gridBagLayout.setConstraints(jtf_num,gbc);
        gbc.gridx=1;
        gridBagLayout.setConstraints(jtf_name,gbc);
        gbc.gridx=2;
        gridBagLayout.setConstraints(jtf_class,gbc);
        gbc.gridx=3;
        gridBagLayout.setConstraints(jb_result,gbc);

        jp.add(jlb_num);
        jp.add(jlb_name);
        jp.add(jlb_class);
        jp.add(jtf_num);
        jp.add(jtf_name);
        jp.add(jtf_class);
        jp.add(jb_result);
        add(jp);
        jp.setVisible(false);

        jb_result.addActionListener(event->{
            System.out.println("点击查询");
            s_result.IsInHere=true;
            a_result.IsInHere=true;
            IsInHere=false;
            jp.setVisible(false);
            if(serch.ser_or_alt==1)
            { add(s_result,BorderLayout.CENTER);s_result.repaint();s_result.setVisible(true);a_result.setVisible(false);}
            else if(serch.ser_or_alt==2)
            { add(a_result,BorderLayout.CENTER);a_result.repaint();a_result.setVisible(true);s_result.setVisible(false);}
        });


        this.setBackground(Color.WHITE);
    }


    public void data_panel(String key){
        try {
            rs=sqlserver.SelectSql("SELECT A.学号,A.姓名,B.班名 FROM 学生表 A join 班级表 B on A.班级号=B.班级号 where A.学号 = "+"'"+key+"'");
            rs.next();
            if(serch.ser_or_alt==1)
            {
                s_result.data_panel(rs.getString("学号"));}
            if(serch.ser_or_alt==2)
            {
                a_result.data_panel(rs.getString("学号"));}
            jtf_num.setText(rs.getString("学号"));
            jtf_name.setText(rs.getString("姓名"));
            jtf_class.setText(rs.getString("班名"));
            jp.setVisible(true);
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if(e.getActionCommand()=="查询")
//            System.out.println("点击查询");
    }
}
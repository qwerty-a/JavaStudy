package com.student.mainWindow.SelectOrAlterOrDeleteComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class serch implements ActionListener, KeyListener ,ItemListener{
    public JPanel SerchPanel;
    JPanel jp1;
    serch_num s_num;
    serch_name s_name;
    serch_result s_result;
    alter_result a_result;
    static JComboBox<String>comboBox;
    public JTextField jtf_sousuo=null;
    JButton jb1;
    static JButton jb;
    static int ser_or_alt;
    String key;
    static String num;
    int serch_model;
    public boolean IsInHere=false;
    public serch(){
        SerchPanel=new JPanel();
        jp1=new JPanel();
         s_result=new serch_result();
        s_num=new serch_num();
        s_name=new serch_name();

        comboBox=new JComboBox<>();
        jtf_sousuo=new JTextField(10);
        System.out.println(jtf_sousuo.getText());
        jb1=new JButton("查询");

        comboBox.addItem("请选择查询方式");
        comboBox.addItem("按学号查询");
        comboBox.addItem("按姓名查询");


        jp1.setLayout(new BorderLayout());
        jp1.add(comboBox,BorderLayout.WEST);
        jp1.add(jtf_sousuo,BorderLayout.CENTER);
        jp1.add(jb1,BorderLayout.EAST);

        SerchPanel.setLayout(new BorderLayout());
        SerchPanel.add(jp1,BorderLayout.NORTH);

        jb1.addActionListener(this);
        jtf_sousuo.addKeyListener(this);
        comboBox.addItemListener(this);

    }


    public static void ser_or_alt(int i){ser_or_alt=i;}
    @Override
    public void actionPerformed(ActionEvent e) {
        if(serch_model==1)
        {
        s_num.data_panel(key);SerchPanel.add(s_num,BorderLayout.CENTER);s_name.setVisible(false);jp1.setVisible(false);
        s_num.setVisible(true);s_num.repaint();}
        else if(serch_model==2)
        {
        s_name.data_panel(key);SerchPanel.add(s_name,BorderLayout.CENTER);s_name.setVisible(true);s_name.repaint();jp1.setVisible(false);
        s_num.setVisible(false);}
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource()==jtf_sousuo)
        { JTextField admin=(JTextField)(e.getSource());
            key=admin.getText();}
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==ItemEvent.SELECTED)
        {
            if(e.getItem().equals("按学号查询"))
            {
                jtf_sousuo.setText("请输入学号");
                serch_model=1;
            }
            if(e.getItem().equals("按姓名查询"))
            {
                jtf_sousuo.setText("请输入姓名");
                serch_model=2;
            }
        }
    }
}

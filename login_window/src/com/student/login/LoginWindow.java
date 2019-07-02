package com.student.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginWindow implements KeyListener,FocusListener {
    JButton jb1;
    JButton jb2;
    JTextField jtf1;
    JTextField jtf2;
    public JFrame f;
    public LoginListener loginListener;
    private GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
    private Rectangle rect=ge.getMaximumWindowBounds();


    public LoginWindow(){
        loginListener=new LoginListener();
        f=new JFrame("Login");
        f.setLayout(new GridLayout(4,1));
        f.setSize(450,350);
        f.setLocation(rect.width/2-175,rect.height/2-125);
        f.setResizable(false);
        JPanel JP1= new JPanel();
        JPanel JP2= new JPanel();
        JPanel JP3= new JPanel();
        f.add(new JLabel());
        f.add(JP1);
        f.add(JP2);
        f.add(JP3);
        JLabel jlb1 = new JLabel("用户名：");
        JLabel jlb2 = new JLabel("密　码：");
        jb1 = new JButton("登录");
        jb1.addActionListener(loginListener);
        jb2 = new JButton("取消");
        jb2.addActionListener(loginListener);
        jtf1 = new JTextField("",10);
        jtf2 = new JTextField("",10);
        jtf1.addKeyListener(this);
        jtf1.addFocusListener(this);
        jtf2.addKeyListener(this);
        jtf2.addFocusListener(this);
        JP1.add(jlb1);
        JP1.add(jtf1);
        JP2.add(jlb2);
        JP2.add(jtf2);
        JP3.add(jb1);
        JP3.add(jb2);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource()==jtf1){
            JTextField admin=(JTextField)(e.getSource());
            loginListener.admin=admin.getText();
        }

        if(e.getSource()==jtf2){
            JTextField admin=(JTextField)(e.getSource());
            loginListener.password=admin.getText();
        }

    }

    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource()==jtf1){
            JTextField admin=(JTextField)(e.getSource());
            admin.setBackground(Color.lightGray);
        }

        if(e.getSource()==jtf2){
            JTextField admin=(JTextField)(e.getSource());
            admin.setBackground(Color.lightGray);
        }

    }

    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource()==jtf1){
            JTextField admin=(JTextField)(e.getSource());
            admin.getCaret().setVisible(false);
            admin.setBackground(Color.WHITE);
        }

        if(e.getSource()==jtf2){
            JTextField admin=(JTextField)(e.getSource());
            admin.getCaret().setVisible(false);
            admin.setBackground(Color.WHITE);
        }
    }
}


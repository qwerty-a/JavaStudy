package com.student.mainWindow;

import com.student.mainWindow.InsertComponent.InsertWindow;
import com.student.mainWindow.SelectOrAlterOrDeleteComponent.SerchPannel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainJFrame implements ActionListener {
    public JFrame f;
    public SerchPannel serch1;
    private JPanel jp_up;
    private JLabel jb_up;
    private JPanel jp_left;
    private JButton jb_left1;
    private JButton jb_left2;
//    private JButton jb_left3;
    private boolean IsAdd1=false;
    private GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
    private Rectangle rect=ge.getMaximumWindowBounds();
    InsertWindow insertWindow;
    public mainJFrame(InsertWindow insertWindow1,SerchPannel serch11){
        serch1=serch11;
        insertWindow=insertWindow1;
        f=new JFrame();
        f.setLocation(rect.width/2-600,10);
        f.setTitle("学籍管理系统");
        f.setVisible(false);
        f.setSize(1200,900);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //设置上面
        jb_up=new JLabel("欢迎使用学籍管理系统");
        jb_up.setFont(new Font("宋体",Font.BOLD,40));
        jp_up=new JPanel();
        jp_up.setPreferredSize(new Dimension(1200, 80));
        jp_up.setBackground(Color.blue);
        jp_up.add(jb_up);
        f.add(jp_up, BorderLayout.NORTH);

        //设计下面左边
        jp_left=new JPanel();
        jp_left.setPreferredSize(new Dimension(200, 820));
        jp_left.setLayout(new GridLayout(10,1,0,20));
        jp_left.setBackground(Color.LIGHT_GRAY);
        f.add(jp_left,BorderLayout.LINE_START);


        jb_left1=new JButton("查询,修改,删除");
        jb_left1.addActionListener(this);
        jb_left1.setFont(new Font("宋体",Font.BOLD,14));
        jb_left2=new JButton("学生信息插入");
        jb_left2.addActionListener(this);
        jb_left2.setFont(new Font("宋体",Font.BOLD,14));
//        jb_left3=new JButton("学生信息删除，修改");
//        jb_left3.addActionListener(this);
//        jb_left3.setFont(new Font("宋体",Font.BOLD,14));
        jp_left.add(jb_left1);
        jp_left.add(jb_left2);
//        jp_left.add(jb_left3);

        //设计下面右边
//        jp_right=new JPanel();
//        jp_right.setPreferredSize(new Dimension(1000,820));
//        jp_right.setBackground(Color.cyan);
        f.add(serch11.Searchpanel,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()=="查询,修改,删除"){
            serch1.Searchpanel.setVisible(true);
            insertWindow.InsertPanel.setVisible(false);
        }
        if(e.getActionCommand()=="学生信息插入"){
            if(!IsAdd1){
                f.add(insertWindow.InsertPanel,BorderLayout.CENTER);
                serch1.Searchpanel.setVisible(false);
                insertWindow.InsertPanel.setVisible(true);
            }else {
                insertWindow.InsertPanel.setVisible(true);
                serch1.Searchpanel.setVisible(false);
            }
        }
//        if(e.getActionCommand()=="学生信息删除，修改"){
//            System.out.println("你点击了学生信息删除，修改");
//        }
    }
}

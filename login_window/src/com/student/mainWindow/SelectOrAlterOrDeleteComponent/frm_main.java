package com.student.mainWindow.SelectOrAlterOrDeleteComponent;

import javax.swing.*;
import java.awt.*;

public class frm_main extends JFrame {
    csh_panel panel;

    public frm_main(){
        panel=new csh_panel();
        add(panel);

//        jb_serch.addActionListener(event->{new serch();this.dispose();});
//        jb_alter.addActionListener(event->{new alter_1();this.dispose();});

        GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rect=ge.getMaximumWindowBounds();

        setBounds(rect.width/2-500,rect.height/2-410,1000,700);
        setLocation(10,100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new frm_main();
    }

}

class csh_panel extends JPanel{
    JPanel jp;

    serch serch;
    serch alter;

    JButton jb_serch;
    JButton jb_alter;
    public csh_panel(){
        this.setLayout(new BorderLayout());
        jp=new JPanel();
        jp.setLayout(new BorderLayout());

        serch=new serch();
        alter=new serch();

        jb_serch=new JButton("查询");
        jb_alter=new JButton("修改");
        jp.add(jb_serch,BorderLayout.WEST);
        jp.add(jb_alter,BorderLayout.EAST);

        add(jp,BorderLayout.NORTH);

        jb_serch.addActionListener(event->{jp.setVisible(false);add(serch.SerchPanel,BorderLayout.NORTH);serch.ser_or_alt(1);});
        jb_alter.addActionListener(event->{jp.setVisible(false);add(alter.SerchPanel,BorderLayout.NORTH);serch.ser_or_alt(2);});

    }
}

package com.student.mainWindow.SelectOrAlterOrDeleteComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SerchPannel implements ActionListener {
    public JPanel Searchpanel;
    JPanel jp;

    serch serch;
    serch alter;

    JButton jb_serch;
    JButton jb_alter;
    JButton jb_back;
    private int LastTimeIn;
    private int NowIn=0;
    private boolean IsInHere=true;
    public SerchPannel(){
        Searchpanel=new JPanel();
        Searchpanel.setPreferredSize(new Dimension(1000, 820));
        Searchpanel.setLayout(new BorderLayout());
        jp=new JPanel();
        jp.setLayout(new BorderLayout());

        serch=new serch();
        alter=new serch();

        jb_serch=new JButton("查询");
        jb_alter=new JButton("修改");
        jb_back=new JButton("返回");
        jb_back.addActionListener(this);
        Searchpanel.add(jb_back,BorderLayout.SOUTH);
        jp.add(jb_serch,BorderLayout.WEST);
        jp.add(jb_alter,BorderLayout.EAST);

        Searchpanel.add(jp,BorderLayout.NORTH);

        jb_serch.addActionListener(event->{
            serch.s_name.IsInHere=true;
            serch.s_num.IsInHere=true;
            IsInHere=false;
            GetWhere();
            LastTimeIn=NowIn;
            jp.setVisible(false);
            serch.SerchPanel.setVisible(true);
            serch.jp1.setVisible(true);
            Searchpanel.repaint();
            Searchpanel.updateUI();
            Searchpanel.add(serch.SerchPanel,BorderLayout.NORTH);
            serch.ser_or_alt(1);
        });
        jb_alter.addActionListener(event->{
            alter.s_name.IsInHere=true;
            alter.s_num.IsInHere=true;
            IsInHere=false;
            jp.setVisible(false);
            alter.SerchPanel.setVisible(true);
            alter.jp1.setVisible(true);
            Searchpanel.repaint();
            Searchpanel.updateUI();
            Searchpanel.add(alter.SerchPanel,BorderLayout.NORTH);
            serch.ser_or_alt(2);
        });

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jb_back){
            GetWhere();
            if (NowIn==1||NowIn==3){
                Searchpanel.add(jb_back,BorderLayout.SOUTH);
                jp.setVisible(true);
                serch.SerchPanel.setVisible(false);
                alter.SerchPanel.setVisible(false);
                if(serch.s_name!=null)
                    serch.s_name.IsInHere=false;
                if(serch.s_num!=null)
                    serch.s_num.IsInHere=false;
                if (alter.s_num!=null)
                    alter.s_num.IsInHere=false;
                if (alter.s_name!=null)
                    alter.s_name.IsInHere=false;
                Searchpanel.repaint();
                Searchpanel.updateUI();
            }
            if (NowIn==2){
                serch.SerchPanel.setVisible(true);
                serch.jp1.setVisible(true);
                Searchpanel.repaint();
                if(serch.s_name!=null)
                serch.s_name.IsInHere=true;
                if(serch.s_num!=null)
                serch.s_num.IsInHere=true;
                serch.s_num.s_result.IsInHere=false;
                serch.s_name.s_result.IsInHere=false;
                Searchpanel.repaint();
                Searchpanel.updateUI();
            }
            if (NowIn==4){
                alter.SerchPanel.setVisible(true);
                alter.jp1.setVisible(true);
                if (alter.s_num!=null)
                alter.s_num.IsInHere=true;
                if (alter.s_name!=null)
                alter.s_name.IsInHere=true;
                alter.s_num.a_result.IsInHere=false;
                alter.s_name.a_result.IsInHere=false;
                Searchpanel.repaint();
                Searchpanel.updateUI();
            }
        }
    }
    private void GetWhere(){
        //0第一层，1查询，2查询的查询信息界面，3修改的查询界面，4，修改界面
        if(IsInHere){
            NowIn=0;
        }
        if (serch.s_name.IsInHere||serch.s_name.IsInHere){
            NowIn=1;
        }
        if (serch.s_name.s_result.IsInHere||serch.s_num.s_result.IsInHere){
            NowIn=2;
        }
        if (alter.s_name.IsInHere||alter.s_name.IsInHere){
            NowIn=3;
        }
        if (alter.s_name.a_result.IsInHere||alter.s_num.a_result.IsInHere){
            NowIn=4;
        }
    }
}

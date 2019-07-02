package com.student.login;

import com.student.mainWindow.InsertComponent.InsertWindow;
import com.student.mainWindow.mainJFrame;
import com.student.sqlServer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginListener implements ActionListener {
    public boolean IsLogin=false;
    public boolean IsOut=false;
    public String admin=null;
    public String password=null;
    private String sql;
    private PreparedStatement PreSql;
    public sqlServer sqlserver;
    public LoginListener(){
        sqlserver=new sqlServer();
        sql="select 密码 from 账号密码表 where 账号=?";
        try{
            PreSql = sqlserver.conn.prepareStatement(sql);
        }catch (Exception e){
            System.out.println("login pre语句错了");
        }
    }
    @Override
    public  void actionPerformed(ActionEvent e){
        if(e.getActionCommand()=="登录"){
            System.out.println("你点击了登录按钮");
            ResultSet result=null;
            try{
                PreSql.setString(1,admin);
                result = sqlserver.SelectSql(PreSql);
                if(result.next()){
                    if(password.equals(result.getString("密码"))){
                        System.out.println("登录成功");
                        IsLogin=true;
                        sqlserver.CloseCnn();
                        PreSql.close();
                        result.close();
                    }else {
                        System.out.println("密码错误");
                        JOptionPane.showMessageDialog(null,"密码错误","提示",JOptionPane.PLAIN_MESSAGE);
                    }
                }else{
                    System.out.println("此账号不存在");
                    JOptionPane.showMessageDialog(null,"此账号不存在","提示",JOptionPane.PLAIN_MESSAGE);
                }
            }catch (Exception e2){
                System.out.println("LoginListener出错啦=>");
                e2.printStackTrace();
            }

        }
        if(e.getActionCommand()=="取消"){
            System.out.println("你点击了取消按钮");
            IsOut=true;
        }
    }
}

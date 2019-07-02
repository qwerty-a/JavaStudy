package com.student.login;

import com.student.login.LoginWindow;
import com.student.mainWindow.InsertComponent.InsertWindow;
import com.student.mainWindow.SelectOrAlterOrDeleteComponent.SerchPannel;
import com.student.mainWindow.SelectOrAlterOrDeleteComponent.serch;
import com.student.mainWindow.mainJFrame;

public class LoginThread implements Runnable{
    public LoginWindow loginWindow;
    public boolean IsDone=false;
    boolean IsNew=false;
    private InsertWindow insertWindow;
    private mainJFrame mf;
    private SerchPannel serch1;
    public LoginThread(){
        loginWindow=new LoginWindow();
    }

    @Override
    public void run() {
        while(!IsDone){
            try {
                Thread.sleep(100);
                if(!IsNew){
                    insertWindow=new InsertWindow();
                    serch1=new SerchPannel();
                    mf=new mainJFrame(insertWindow,serch1);
                    IsNew=true;
                }
                if(loginWindow.loginListener.IsLogin){
                    mf.f.setVisible(true);
                    System.out.println("关闭登录窗口和线程");
                    IsDone=true;
                    loginWindow.f.dispose();
                }
                if(loginWindow.loginListener.IsOut){
                    System.exit(0);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

package com.student.mainWindow.SelectOrAlterOrDeleteComponent;

import javax.swing.*;
import java.sql.*;
import java.io.*;

public class S_T_picture extends JFrame {
    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;

    JPanel jPanel;
    JLabel jLabel;
    public S_T_picture(){
//        ImageIcon icon=new ImageIcon("src/Images/6.jpg");
//        icon.setImage(icon.getImage().getScaledInstance(150,200,0));
//
//        jPanel=new JPanel();
//        jLabel=new JLabel(icon);
//
//        jPanel.add(jLabel);
//        add(jPanel);
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
//        setSize(600,500);
    }

    //建立数据库连接
    public  void CreateCon(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=学生信息管理","mr","123456abc");
            System.out.println("成功");
        }catch (Exception e){System.out.println("异常");}
    }

    //1.将大二进制文件插入到数据库
    public void  WriteContextIntoDB(){

        try {
            String updatesql="update 学生表 set " +
                    "照片=? " +
                    "where 学号=5120172060 ";
            stmt = conn.prepareStatement(updatesql);
            File file = new File("src/Images/64.jpg");
            FileInputStream fileInputStream = new FileInputStream(file);
            stmt.setBinaryStream(1, fileInputStream,
                    (int)fileInputStream.available());
            stmt.executeUpdate();
            System.out.println("成功向数据库插入图片  ");
        } catch (SQLException e) {
            System.out.println("SQLException");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException ");
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    //2.从数据读出二进制图片并写到磁盘
    public void  ReadContextFromDB() {

        try {
            String sql = "select 照片 from 学生表" +
                    " where 学号=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "5120172003");
            stmt.executeQuery();
            rs = stmt.getResultSet();

            if (rs.next()) {
                InputStream inputStream = rs.getBinaryStream("照片");

                FileOutputStream fileOutputStream = new FileOutputStream(new File(
                        "src/Image/3.jpeg"));
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, len);
                }
                System.out.println("成功读出数据库图片  ");
                inputStream.close();
                fileOutputStream.close();
            }

        } catch (SQLException e) {
            System.out.println("SQLException");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
//        new S_T_picture();
        S_T_picture l=new S_T_picture();
        l.CreateCon();
//        l.WriteContextIntoDB();
        l.ReadContextFromDB();
    }
}

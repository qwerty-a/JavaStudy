package com.student;

import java.sql.*;

public class sqlServer {

    private String DBDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //加载驱动
    private String ConnStr = "jdbc:sqlserver://localhost:1433;DatabaseName=学生信息管理"; //连接数据库"jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=Test";
    public Connection conn;

    public sqlServer() {
        try {
            Class.forName(DBDriver);// 加载驱动程序
        } catch (Exception e) {
            System.out.println("加载驱动出错 => " + e.getMessage());
        }
        try {
            conn = DriverManager.getConnection(ConnStr, "sa", "123456");
            System.out.println("数据库连接成功");
        } catch (SQLException e) {
            System.out.println("连接数据库失败 => " + e.getMessage());
        } catch (Exception e) {
            System.out.println("其它错误 => " + e.getMessage());
        }
    }//构造函数，连接数据库

    public ResultSet SelectSql(PreparedStatement PreSql) {
        try {
            return PreSql.executeQuery();
        } catch (Exception e) {
            System.out.println("执行sql查询语句出错=>"+ e.getMessage());
            return null;
        }
    }//执行查询语句，传入一个PreparedStatement，并返回一个ResultSet数据

    public boolean UpdataOrDeleteOrInsertSql(PreparedStatement PreSql) {
        Statement stm = null;
        try {
            int i=0;
            if ((i=PreSql.executeUpdate()) > 0) {
                System.out.println(i);
                return true;
            }
        } catch (Exception e) {
            System.out.println("执行sql语句出错=>"+ e.getMessage());
            return false;
        }
        return false;
    }//执行增，改，删语句，传入一个PreparedStatement，并返回一个boolean数据

    public void CloseCnn() {
        try {
            conn.close();
            System.out.println("连接关闭成功");
        } catch (Exception e) {
            System.out.println("关闭出错=>"+ e.getMessage());
        }
    }//关闭连接，但执行完组件任务后，记得关闭 UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值

}

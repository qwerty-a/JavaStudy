package com.student.mainWindow.SelectOrAlterOrDeleteComponent;

import com.student.sqlServer_m;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

public class Course extends JFrame {

    JPanel jp;
    JScrollPane jsp;
    JTable table;
    sqlServer_m sqlserver;
    PreparedStatement PreSql;
    ResultSet rs ;

    public Course(String num){
        jp=new JPanel();
        jp.setLayout(new BorderLayout());
        jsp=new JScrollPane();

        sqlserver=new sqlServer_m();
        rs=sqlserver.SelectSql("SELECT A.课程名,B.成绩,A.学分 FROM 课程表 A join 选课表 B on A.课程号=B.课程号  where B.学号="+num);

        //表头或列名
        Vector<String> Header = new Vector<String>();
        try {
            ResultSetMetaData rsMeta = rs.getMetaData();
            System.out.println("列数=" +rsMeta.getColumnCount());
            for (int i = 1; i <= rsMeta.getColumnCount(); i++) {
                Header.add(rsMeta.getColumnName(i));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //取数据
        Vector<Vector> DataObj = new Vector<Vector>();
        try {
            ResultSetMetaData rsMeta = rs.getMetaData();
            int ColumnCount = rsMeta.getColumnCount();
            while (rs.next()) {
                //取一行数据
                Vector<String> data = new Vector<String>();
                for (int i = 1; i <= ColumnCount; i++) {
                    data.add(rs.getString(i));
                }
                //将一行数据 加入到
                DataObj.add(data);
            }
//初始化表格
            table = new JTable(DataObj, Header);
            table.setEnabled(false);
            jsp.setViewportView(table);
        } catch (Exception e) {
            e.printStackTrace();
        }

        jp.add(jsp);
        add(jp);

        this.setSize(480,270);
        this.setVisible(true);
    }
}

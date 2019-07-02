package com.student.mainWindow.SelectOrAlterOrDeleteComponent;

import com.student.sqlServer;
import com.student.sqlServer_m;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class alter_result extends JPanel implements ActionListener {
    private sqlServer alterSql;
    private Map StudentInformation;
    JComboBox<String>HeightCombox;
    JButton j_delet;



    sqlServer_m sqlserver;
    PreparedStatement PreSql;
    ResultSet rs ;
    public boolean IsInHere=false;
    String num;
    String name;
    String sex;
    String ID;
    String birthdate;
    String minzu;
    String jiguan;
    String zzmm;
    String addres;
    String youbian;
    String Tel;
    String shengao;
    String xuexing;
    String Class;
    String bzr;
    String ximing;

    JLabel jlb_name;
    JLabel jlb_num;
    JLabel jlb_sex;
    JLabel jlb_class;
    JLabel jlb_bzr;
    JLabel jlb_ximing;
    JLabel jlb_ID;
    JLabel jlb_birthdate;
    JLabel jlb_minzu;
    JLabel jlb_jiguan;
    JLabel jlb_zzmm;
    JLabel jlb_addres;
    JLabel jlb_youbian;
    JLabel jlb_Tel;
    JLabel jlb_shengao;
    JLabel jlb_xuexing;
    JLabel jlb_photo;

    JButton jb_Cancel;

    JTextField jtf_name;
    JTextField jtf_num;
    JTextField jtf_sex;
    JTextField jtf_class;
    JTextField jtf_bzr;
    JTextField jtf_ximing;
    JTextField jtf_ID;
    JTextField jtf_birthdate;
    JTextField jtf_minzu;
    JTextField jtf_jiguan;
    JTextField jtf_zzmm;
    JTextField jtf_addres;
    JTextField jtf_youbian;
    JTextField jtf_Tel;
    JTextField jtf_shengao;
    JTextField jtf_xuexing;

    public alter_result() {
        j_delet=new JButton("删除");
        j_delet.addActionListener(this);
        alterSql=new sqlServer();
        jb_Cancel=new JButton("保存修改");
        jb_Cancel.addActionListener(this);
        StudentInformation=new HashMap();
        HeightCombox=new JComboBox<>();
//        StudentInformation.put("系名",null);
        StudentInformation.put("家庭电话",null);
//        StudentInformation.put("班级号",null);
        StudentInformation.put("政治面貌",null);
        StudentInformation.put("身高",null);
//        StudentInformation.put("班主任",null);



        jlb_name = new JLabel("姓名");
        jlb_num = new JLabel("学号");
        jlb_sex = new JLabel("性别");
        jlb_class = new JLabel("班级");
        jlb_bzr = new JLabel("班主任");
        jlb_ximing = new JLabel("系名");
        jlb_ID = new JLabel("身份证号");
        jlb_birthdate = new JLabel("出生年月日");
        jlb_minzu = new JLabel("民族");
        jlb_jiguan = new JLabel("籍贯");
        jlb_zzmm = new JLabel("政治面貌");
        jlb_addres = new JLabel("家庭通讯地址");
        jlb_youbian = new JLabel("邮编");
        jlb_Tel = new JLabel("家庭电话");
        jlb_shengao = new JLabel("身高");
        jlb_xuexing = new JLabel("血型");
        ImageIcon icon = new ImageIcon("src/Images/4.jpeg");
        icon.setImage(icon.getImage().getScaledInstance(150, 200, 0));
        jlb_photo = new JLabel(icon);



        sqlserver=new sqlServer_m();
        int columns=16;
        jtf_name = new JTextField(columns);
        jtf_name.setBackground(Color.WHITE);
        jtf_name.setEditable(false);
        jtf_num = new JTextField(columns);
        jtf_num.setEditable(false);
        jtf_num.setBackground(Color.WHITE);
        jtf_sex = new JTextField(columns);
        jtf_sex.setEditable(false);
        jtf_sex.setBackground(Color.WHITE);
        jtf_class = new JTextField(columns);
        jtf_class.setBackground(Color.WHITE);
        jtf_bzr = new JTextField(columns);
        jtf_bzr.setBackground(Color.WHITE);
        jtf_ximing = new JTextField(columns);
        jtf_ximing.setEditable(false);
        jtf_ximing.setBackground(Color.WHITE);
        jtf_ID = new JTextField(columns);
        jtf_ID.setEditable(false);
        jtf_ID.setBackground(Color.WHITE);
        jtf_birthdate = new JTextField(columns);
        jtf_birthdate.setEditable(false);
        jtf_birthdate.setBackground(Color.WHITE);
        jtf_minzu = new JTextField(columns);
        jtf_minzu.setEditable(false);
        jtf_minzu.setBackground(Color.WHITE);
        jtf_jiguan = new JTextField(columns);
        jtf_jiguan.setEditable(false);
        jtf_jiguan.setBackground(Color.WHITE);
        jtf_zzmm = new JTextField(columns);
        jtf_zzmm.setBackground(Color.WHITE);
        jtf_addres = new JTextField(columns);
        jtf_addres.setEditable(false);
        jtf_addres.setBackground(Color.WHITE);
        jtf_youbian = new JTextField(columns);
        jtf_youbian.setEditable(false);
        jtf_youbian.setBackground(Color.WHITE);
        jtf_Tel = new JTextField(columns);
        jtf_Tel.setBackground(Color.WHITE);
//        jtf_shengao = new JTextField(columns);
//        jtf_shengao.setBackground(Color.WHITE);
        for(int i=0;i<=300;i++){
            HeightCombox.addItem(i+"CM");
        }
        jtf_xuexing = new JTextField(columns);
        jtf_xuexing.setEditable(false);
        jtf_xuexing.setBackground(Color.WHITE);

        Font font = new Font("宋体", Font.BOLD, 18);
        jlb_name.setFont(font);
        jlb_num.setFont(font);
        jlb_sex.setFont(font);
        jlb_class.setFont(font);
        jlb_bzr.setFont(font);
        jlb_ximing.setFont(font);
        jlb_ID.setFont(font);
        jlb_birthdate.setFont(font);
        jlb_minzu.setFont(font);
        jlb_jiguan.setFont(font);
        jlb_zzmm.setFont(font);
        jlb_addres.setFont(font);
        jlb_youbian.setFont(font);
        jlb_Tel.setFont(font);
        jlb_shengao.setFont(font);
        jlb_xuexing.setFont(font);
        jlb_photo.setFont(font);
        jtf_name.setFont(font);
        jtf_num.setFont(font);
        jtf_sex.setFont(font);
        jtf_class.setFont(font);
        jtf_bzr.setFont(font);
        jtf_bzr.setEditable(false);
        jtf_ximing.setFont(font);
        jtf_ID.setFont(font);
        jtf_birthdate.setFont(font);
        jtf_minzu.setFont(font);
        jtf_jiguan.setFont(font);
        jtf_zzmm.setFont(font);
        jtf_addres.setFont(font);
        jtf_youbian.setFont(font);
        jtf_Tel.setFont(font);
//        jtf_shengao.setFont(font);
        jtf_xuexing.setFont(font);


        GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets=new Insets(1,1,1,1);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gridBagLayout.setConstraints(jlb_name, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gridBagLayout.setConstraints(jtf_name, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 2;
        gridBagLayout.setConstraints(jlb_num, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 3;
        gridBagLayout.setConstraints(jtf_num, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 4;
        gridBagLayout.setConstraints(jlb_sex, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 5;
        gridBagLayout.setConstraints(jtf_sex, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gridBagLayout.setConstraints(jlb_ximing, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gridBagLayout.setConstraints(jtf_ximing, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 2;
        gridBagLayout.setConstraints(jlb_class, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 3;
        gridBagLayout.setConstraints(jtf_class, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 4;
        gridBagLayout.setConstraints(jlb_bzr, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 5;
        gridBagLayout.setConstraints(jtf_bzr, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gridBagLayout.setConstraints(jlb_ID, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gridBagLayout.setConstraints(jtf_ID, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 2;
        gridBagLayout.setConstraints(jlb_birthdate, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 3;
        gridBagLayout.setConstraints(jtf_birthdate, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 4;
        gridBagLayout.setConstraints(jlb_minzu, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 5;
        gridBagLayout.setConstraints(jtf_minzu, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gridBagLayout.setConstraints(jlb_jiguan, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gridBagLayout.setConstraints(jtf_jiguan, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 2;
        gridBagLayout.setConstraints(jlb_zzmm, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 3;
        gridBagLayout.setConstraints(jtf_zzmm, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 4;
        gridBagLayout.setConstraints(jlb_addres, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 5;
        gridBagLayout.setConstraints(jtf_addres, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gridBagLayout.setConstraints(jlb_Tel, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gridBagLayout.setConstraints(jtf_Tel, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 2;
        gridBagLayout.setConstraints(jlb_shengao, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 3;
        gridBagLayout.setConstraints(HeightCombox, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 4;
        gridBagLayout.setConstraints(jlb_xuexing, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 5;
        gridBagLayout.setConstraints(jtf_xuexing, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 5;
        gridBagLayout.setConstraints(jlb_youbian, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gridBagLayout.setConstraints(jtf_youbian, gbc);

        gbc.gridx=2;
        gbc.gridy=6;
        JPanel tempPanel =new JPanel();
        tempPanel.add(jb_Cancel);
        tempPanel.add(j_delet);
        gridBagLayout.setConstraints(tempPanel,gbc);


        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;//设置约束的fill参数,该参数表示当组件的大小小于网格单元的大小时在水平和垂直方向都填充
        gbc.anchor = GridBagConstraints.CENTER;
        gridBagLayout.setConstraints(jlb_photo, gbc);

        add(jlb_name);
        add(jlb_num);
        add(jlb_sex);
        add(jlb_class);
        add(jlb_bzr);
        add(jlb_ximing);
        add(jlb_ID);
        add(jlb_birthdate);
        add(jlb_minzu);
        add(jlb_jiguan);
        add(jlb_zzmm);
        add(jlb_addres);
        add(jlb_youbian);
        add(jlb_Tel);
        add(jlb_shengao);
        add(jlb_xuexing);
        add(jlb_photo);
        add(jtf_name);
        add(jtf_num);
        add(jtf_sex);
        add(jtf_class);
        add(jtf_bzr);
        add(jtf_ximing);
        add(jtf_ID);
        add(jtf_birthdate);
        add(jtf_minzu);
        add(jtf_jiguan);
        add(jtf_zzmm);
        add(jtf_addres);
        add(jtf_youbian);
        add(jtf_Tel);
        add(HeightCombox);
//        add(jtf_shengao);
        add(jtf_xuexing);
        add(tempPanel);


    }

    public void data_panel(String num){
        try{
            rs=sqlserver.SelectSql("SELECT A.姓名,A.性别,A.身份证号码,A.出生年月日,A.民族,A.籍贯," +
                    "A.政治面貌,A.家庭通讯地址,A.邮编,A.家庭电话,A.身高,A.血型,A.照片,B.班名,B.班主任,C.系名" +
                    " FROM 学生表 A join 班级表 B on A.班级号=B.班级号 join 系表 C on B.系号=C.系号"+
                    " where A.学号="+"'"+num+"'");
            rs.next();
            this.num=num;
            name=rs.getString("姓名");
            sex=rs.getString("性别");
            ID=rs.getString("身份证号码");
            birthdate=rs.getString("出生年月日");
            minzu=rs.getString("民族");
            jiguan=rs.getString("籍贯");
            zzmm=rs.getString("政治面貌");
            addres=rs.getString("家庭通讯地址");
            youbian=rs.getString("邮编");
            Tel=rs.getString("家庭电话");
            shengao=rs.getString("身高");
            xuexing=rs.getString("血型");
            Class=rs.getString("班名");
            bzr=rs.getString("班主任");
            ximing=rs.getString("系名");
//以下为从数据库获取图片
            InputStream inputStream = rs.getBinaryStream("照片");
            FileOutputStream fileOutputStream = new FileOutputStream(new File(
                    "src/Image/3.jpeg"));
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }
            inputStream.close();
            fileOutputStream.close();
            rs.close();


            jtf_name.setText(name);
            jtf_num.setText(num);
            jtf_sex.setText(sex);
            jtf_class.setText(Class);
            jtf_class.setEditable(false);
            jtf_bzr.setText(bzr);
            jtf_ximing.setText(ximing);
            jtf_ID.setText(ID);
            jtf_birthdate.setText(birthdate);
            jtf_minzu.setText(minzu);
            jtf_jiguan.setText(jiguan);
            jtf_zzmm.setText(zzmm);
            jtf_addres.setText(addres);
            jtf_youbian.setText(youbian);
            jtf_Tel.setText(Tel);

            int c=shengao.indexOf("C");
            shengao=shengao.substring(0,c);
            int Height=Integer.parseInt(shengao);
            HeightCombox.setSelectedIndex(Height);

            jtf_xuexing.setText(xuexing);
        }catch (Exception e){e.printStackTrace();}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==j_delet){
            try{
                String sql="delete from 学生表 where 学号=?";
                PreparedStatement pre=alterSql.conn.prepareStatement(sql);
                pre.setString(1,num);
                if(alterSql.UpdataOrDeleteOrInsertSql(pre)){
                    JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.PLAIN_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null, "删除失败", "提示", JOptionPane.PLAIN_MESSAGE);
                }
                pre.close();
            }catch (Exception e2){
                System.out.println("删除失败=》"+e2.getMessage());
                JOptionPane.showMessageDialog(null, "删除失败", "提示", JOptionPane.PLAIN_MESSAGE);
            }
        }
        if(e.getActionCommand()=="保存修改"){
            if(jtf_Tel.getText().equals("")|| jtf_zzmm.getText().equals("")){
                JOptionPane.showMessageDialog(null, "请将信息填写完整", "提示", JOptionPane.PLAIN_MESSAGE);
            }else {
                StudentInformation.replace("家庭电话",jtf_Tel.getText());
                StudentInformation.replace("政治面貌",jtf_zzmm.getText());
                StudentInformation.replace("身高",HeightCombox.getSelectedItem());
                try{
                    int b=0;
                    Set keySet=StudentInformation.keySet();
                    Iterator it=keySet.iterator();
                    alterSql.conn.commit();
                    while (it.hasNext()){
                        Object key=it.next();
                        String sql="UPDATE 学生表 SET "+key+"="+ "'"+StudentInformation.get(key)+"'"+" WHERE 学号 ="+"'"+num+"'";
//                        System.out.println(sql);
                        Statement statement=alterSql.conn.createStatement();
                        statement.execute(sql);
                        b++;
                    if (b == 3) {
                        JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.PLAIN_MESSAGE);
                    }
                    }
                }catch (Exception ee){
                    System.out.println("修改数据时出错啦=》"+ee.getMessage());
                }
            }
        }
    }
}

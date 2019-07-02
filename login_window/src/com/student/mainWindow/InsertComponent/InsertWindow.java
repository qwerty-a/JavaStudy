package com.student.mainWindow.InsertComponent;

import com.student.sqlServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class InsertWindow implements  ActionListener, ItemListener,FocusListener {
    public JPanel InsertPanel;
    private Map StudentInformation;
    private String[] StudentInformaionColumnName=new String[16];
    private JPanel jp1;
    private JPanel jp2;
    private JPanel jp3;
    private JPanel jp4;
    private JLabel[] jb=new JLabel[20];
    private JTextField[] jt=new JTextField[20];
    private PreparedStatement preSql;
    private String sql;
    private ResultSetMetaData rsmd;
    private ResultSet rs;
    private JButton jb1;
    private JButton jb2;
    private int month=1;
    private int year=1980;
    private int days=31;
    private JRadioButton jr1;
    private JComboBox<String>comboox3;
    private JComboBox<String>comboox2;
    private JComboBox<String>comboox1;
    private JComboBox<String>Localcombox1;
    private JComboBox<String>Localcombox2;
    private JComboBox<String>Localcombox3;
    private JComboBox<String>Localcombox4;
    private JComboBox<String>Localcombox5;
    private JComboBox<String>HeightCombox;
    private JComboBox<String>WeightCombox;
    private JComboBox<String>BloodCombox;
    private ButtonGroup bg;
    private sqlServer InserSql;
    private FileInputStream fileInputStream;
    public InsertWindow(){
        InserSql=new sqlServer();
        //获取到学生表列名
        getStudentInformaionColumnName();

        //初始化StudentInformaionColumnName
        StudentInformation=new HashMap();
        for(int b=0;b<16;b++){
            StudentInformation.put(StudentInformaionColumnName[b],"Null");
        }

        InsertPanel=new JPanel();
        jp1=new JPanel();
        jp1.setPreferredSize(new Dimension(80, 800));
        jp1.setLayout(new GridLayout(12,1,10,40));
        jp2=new JPanel();
        jp2.setPreferredSize(new Dimension(380, 800));
        jp2.setLayout(new GridLayout(12,1,10,40));
        jp3=new JPanel();
        jp3.setPreferredSize(new Dimension(80, 800));
        jp3.setLayout(new GridLayout(12,1,10,40));
        jp4=new JPanel();
        jp4.setPreferredSize(new Dimension(380, 800));
        jp4.setLayout(new GridLayout(12,1,10,40));
        jb1=new JButton("重填");
        jb1.addActionListener(this);
        jb2=new JButton("提交");
        jb2.addActionListener(this);

        //初始化左边
        for(int i=0;i<9;i++){
            boolean IsAdd=true;
            jb[i]=new JLabel(StudentInformaionColumnName[i]);
            jp1.add(jb[i]);
            jt[i]=new JTextField(25);
            switch (i){
                //性别
                case 2:{
                    jr1=new JRadioButton("男");
                    JRadioButton jr2=new JRadioButton("女");
                    bg=new ButtonGroup();
                    bg.add(jr1);
                    bg.add(jr2);
                    jr1.setSelected(true);
                    JPanel jsex=new JPanel();
                    jsex.add(jr1);
                    jsex.add(jr2);
                    jp2.add(jsex);
                    IsAdd=false;
                    jt[i].setText("占位");
                    break;
                }
                //出生年月日
                case 5:{
                    JPanel jbirth=new JPanel();
                    JPanel year=new JPanel();
                    JPanel month=new JPanel();
                    JPanel day=new JPanel();
                    comboox1=new JComboBox<String>();
                    comboox2=new JComboBox<String>();
                    comboox3=new JComboBox<String>();
                    comboox1.addItemListener(this);
                    comboox2.addItemListener(this);
                    comboox3.addItemListener(this);
                    for(int a=1980;a<=2030;a++){
                        comboox1.addItem(a+"年");
                    }
                    year.add(comboox1);
                    for(int b=1;b<=12;b++){
                        comboox2.addItem(b+"月");
                    }
                    month.add(comboox2);

                    day.add(comboox3);
                    jbirth.add(year);
                    jbirth.add(month);
                    jbirth.add(day);
                    jp2.add(jbirth);
                    IsAdd=false;
                    jt[i].setText("占位");
                    break;
                }
                //籍贯，精确到市
                case 7:{
                    JPanel jp_local=new JPanel();
                    JPanel jp_loacl1=new JPanel();
                    JPanel jp_local2=new JPanel();
                    jp_local.add(jp_loacl1);
                    jp_local.add(jp_local2);
                    Localcombox1=new JComboBox<>();
                    SelectProvince(Localcombox1);
                    jp_loacl1.add(Localcombox1);
                    Localcombox2=new JComboBox<>();
                    SelectCity(Localcombox2,"北京市");
                    jp_local2.add(Localcombox2);
                    jp2.add(jp_local);
                    IsAdd=false;
                    jt[i].setText("占位");
                    break;
                }
            }
            if(IsAdd){
                jp2.add(jt[i]);
            }else {
                IsAdd=true;
            }
        }

        //初始化右边
        for(int i=9;i<17;i++){
            boolean IsAdd=true;
            if(i==10){
                jb[i]=new JLabel();
                jp3.add(jb[i]);
            }else if(i==9){
                jb[i]=new JLabel(StudentInformaionColumnName[i]);
                jp3.add(jb[i]);
            }else {
                jb[i]=new JLabel(StudentInformaionColumnName[i-1]);
                jp3.add(jb[i]);
            }

            jt[i]=new JTextField(25);

            switch (i){
                //通讯地址第一排
                case 9:{
                    JPanel jp_GSD=new JPanel();
                    jp4.add(jp_GSD);
                    JPanel jp_GSD1=new JPanel();
                    JPanel jp_GSD2=new JPanel();
                    jp_GSD.add(jp_GSD1);
                    jp_GSD.add(jp_GSD2);
                    Localcombox3=new JComboBox<>();
                    jp_GSD1.add(Localcombox3);
                    SelectProvince(Localcombox3);
                    Localcombox4=new JComboBox<>();
                    jp_GSD2.add(Localcombox4);
                    SelectCity(Localcombox4,"北京市");
                    IsAdd=false;
                    jt[i].setText("占位");
                    break;
                }
                //通讯地址第二排
                case 10:{
                    Localcombox5=new JComboBox<>();
                    SelectXian(Localcombox5,"北京市","市辖区");
                    jt[i].addFocusListener(this);
                    jt[i].setText("请输入详细地址");
                    jt[i].setColumns(15);
                    JPanel jpppp=new JPanel();
                    jpppp.add(Localcombox5);
                    jpppp.add(jt[i]);
                    jp4.add(jpppp);
                    IsAdd=false;
                    break;
                }
                //身高
                case 13:{
                    HeightCombox=new JComboBox<>();
                    HeightCombox.addItemListener(this);
                    for(int j=10;j<=300;j++){
                        HeightCombox.addItem(String.valueOf(j));
                    }
                    JLabel HeightLable=new JLabel("cm");
                    JPanel jheight=new JPanel();
                    jheight.add(HeightCombox);
                    jheight.add(HeightLable);
                    jp4.add(jheight);
                    IsAdd=false;
                    jt[i].setText("占位");
                    break;
                }
                //体重
                case 14:{
                    WeightCombox=new JComboBox<>();
                    WeightCombox.addItemListener(this);
                    for(int j=0;j<=200;j++){
                        WeightCombox.addItem(String.valueOf(j));
                    }
                    JLabel WeightLable=new JLabel("kg");
                    JPanel jweight=new JPanel();
                    jweight.add(WeightCombox);
                    jweight.add(WeightLable);
                    jp4.add(jweight);
                    IsAdd=false;
                    jt[i].setText("占位");
                    break;
                }
                //血型
                case 15:{
                    BloodCombox=new JComboBox<>();
                    BloodCombox.addItemListener(this);
                    BloodCombox.addItem("A");
                    BloodCombox.addItem("B");
                    BloodCombox.addItem("AB");
                    BloodCombox.addItem("O");
                    BloodCombox.addItem("A");
                    JLabel BloodLable=new JLabel("型");
                    JPanel jBlood=new JPanel();
                    jBlood.add(BloodCombox);
                    jBlood.add(BloodLable);
                    jp4.add(jBlood);
                    IsAdd=false;
                    jt[i].setText("占位");
                    break;
                }
                //照片
                case 16:{
                    jt[i].setText("请输入文件的地址，比例尽量为4:3");
                    jt[i].addFocusListener(this);
                    jp4.add(jt[i]);
                    IsAdd=false;
                    break;
                }
            }
            //其他为文本框
            if(IsAdd){
                jp4.add(jt[i]);
            }else {
                IsAdd=true;
            }
        }
        //提交与重填
        JPanel jp_button=new JPanel();
        JLabel JLB=new JLabel("              ");//为了占位，本身无意义
        jp_button.add(jb1);
        jp_button.add(JLB);
        jp_button.add(jb2);
        jp4.add(jp_button);

        InsertPanel.add(jp1);
        InsertPanel.add(jp2);
        InsertPanel.add(jp3);
        InsertPanel.add(jp4);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //点击重填按钮的操作
        if (e.getSource() == jb1) {
            jt[0].setText("");
            jt[1].setText("");
            jt[3].setText("");
            jt[4].setText("");
            jt[6].setText("");
            jt[8].setText("");
            jt[11].setText("");
            jt[12].setText("");
            jt[10].setText("请输入详细地址");
            jt[16].setText("请输入文件的地址，比例尽量为4:3");
            comboox1.setSelectedIndex(0);
            comboox2.setSelectedIndex(0);
            Localcombox1.setSelectedIndex(0);
            Localcombox3.setSelectedIndex(0);
            WeightCombox.setSelectedIndex(0);
            HeightCombox.setSelectedIndex(0);
            BloodCombox.setSelectedIndex(0);
        }
        //点击确定按钮的操作，
        if (e.getSource() == jb2) {
            //判断是否填写完整
            boolean IsAllDo = true;
            for (int f = 0; f < 17; f++) {
                if (jt[f].getText().equals("")) {
                    IsAllDo = false;
                }
            }
            if (jt[10].getText().equals("请输入详细地址") || jt[16].getText().equals("请输入文件的地址，比例尽量为4:3")
                    || ((String) WeightCombox.getSelectedItem()).equals(0))
            {
                IsAllDo = false;
            }
            //向StudentInformation写入数据
            if (IsAllDo) {
                for (int y = 0; y < 16; y++) {
                    boolean IsAdd = true;
                    switch (y) {
                        case 2: {
                            String sex = "女";
                            if (jr1.isSelected()) {
                                sex = "男";
                            }
                            StudentInformation.replace(StudentInformaionColumnName[y], sex);
                            IsAdd = false;
                            break;
                        }
                        //日期
                        case 5: {
                            //创建一个Calendar类对象
                            Calendar CDate = Calendar.getInstance();
                            //获取当前选择框的日期
                            String year1=((String)comboox1.getSelectedItem());
                            int c=year1.indexOf("年");
                            year1=year1.substring(0,c);
                            int birth_year=Integer.parseInt(year1);
                            String month1=((String)comboox2.getSelectedItem());
                            int a=month1.indexOf("月");
                            month1=month1.substring(0,a);
                            int birth_month=Integer.parseInt(month1);
                            String day1=((String)comboox3.getSelectedItem());
                            int b=day1.indexOf("日");
                            day1=day1.substring(0,b);
                            int birth_day=Integer.parseInt(day1);
                            //设置日期
                            CDate.set(birth_year, birth_month-1, birth_day);
                            //转换为Date类对象
                            Date date=CDate.getTime();
                            //转换为sql的Date类对象
                            java.sql.Timestamp date1=new java.sql.Timestamp(date.getTime());
                            //保存在map里面
                            StudentInformation.replace(StudentInformaionColumnName[y], date1);
                            IsAdd = false;
                            break;
                        }
                        case 7: {
                            StudentInformation.replace(StudentInformaionColumnName[y],
                                    (String) Localcombox1.getSelectedItem() + (String) Localcombox2.getSelectedItem());
                            IsAdd = false;
                            break;
                        }
                        case 9: {
                            StudentInformation.replace(StudentInformaionColumnName[y],
                                    (String) Localcombox3.getSelectedItem() + (String) Localcombox4.getSelectedItem()
                                            + (String) Localcombox5.getSelectedItem() + jt[y + 1].getText());
                            IsAdd = false;
                            break;
                        }
                        case 12: {
                            String temp=(String) HeightCombox.getSelectedItem()+"CM";
                            StudentInformation.replace(StudentInformaionColumnName[y],temp);
                            IsAdd = false;
                            break;
                        }
                        case 13: {
                            String temp=(String) WeightCombox.getSelectedItem()+"kg";
                            StudentInformation.replace(StudentInformaionColumnName[y],temp);
                            IsAdd = false;
                            break;
                        }
                        case 14: {
                            StudentInformation.replace(StudentInformaionColumnName[y],
                                    (String) BloodCombox.getSelectedItem());
                            IsAdd = false;
                            break;
                        }
                        //图片
                        case 15:{
                            File file = new File(jt[y+1].getText());
                            fileInputStream=null;
                            try {
                                //转换成二进制
                                fileInputStream = new FileInputStream(file);
                            }catch (Exception ee){
                                //转换失败的提示
                                System.out.println("图片转换出错=》"+ee.getMessage());
                                JOptionPane.showMessageDialog(null, "图片转换为2进制失败", "提示", JOptionPane.PLAIN_MESSAGE);
                                IsAllDo=false;
                            }
                            if(IsAllDo){
                                //如果成功写入map里面
                                StudentInformation.replace(StudentInformaionColumnName[y],fileInputStream);
                                //写入数据
                                InsertSqlData();
                            }
                            IsAdd=false;
                            break;
                        }
                    }
                    if(IsAdd) {
                        if(y>9){
                            StudentInformation.replace(StudentInformaionColumnName[y], jt[y+1].getText());
                        }else {
                            StudentInformation.replace(StudentInformaionColumnName[y], jt[y].getText());
                        }
                    }
                }
                StudentInformation.forEach((key,value)->System.out.println(key+":"+value));
            } else {
                JOptionPane.showMessageDialog(null, "请将信息填写完整", "提示", JOptionPane.PLAIN_MESSAGE);
                if (jr1.isSelected()) {
                    System.out.println("男");
                }
            }
        }
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==ItemEvent.SELECTED){
            //日期
            //当重新选择年时，获取选择的年
            if(e.getSource().equals(comboox1)){
                String year1=((String)e.getItem()).substring(0,4);
                year=Integer.parseInt(year1);
                comboox3.removeItemListener(this);
                comboox3.removeAllItems();
                days=countDays(year,month);//获得该年该月对应有多少天
                //重新设置日选择框
                for(int i=1;i<=days;i++){
                    comboox3.addItem(i+"日");
                }
                comboox3.addItemListener(this);
            }
            //当重新选择月份时，获取选择的月份
            if(e.getSource().equals(comboox2)){
                String month1=((String)e.getItem());
                int a=month1.indexOf("月");
                month1=month1.substring(0,a);
                month=Integer.parseInt(month1);
                comboox3.removeItemListener(this);
                comboox3.removeAllItems();
                days=countDays(year,month);//获得该年该月对应有多少天
                //重新设置日选择框
                for(int i=1;i<=days;i++){
                    comboox3.addItem(i+"日");
                }
                comboox3.addItemListener(this);
            }
//            if(!e.getSource().equals(comboox3)){
//                comboox3.removeItemListener(this);
//                comboox3.removeAllItems();
//                days=countDays(year,month);//获得该年该月对应有多少天
//                //重新设置日选择框
//                for(int i=1;i<=days;i++){
//                    comboox3.addItem(i+"日");
//                }
//                comboox3.addItemListener(this);
//            }
            //籍贯
            if(e.getSource().equals(Localcombox1)){
                //输入一个JComboBox类，和输入省名，设置这个ComboBox
                SelectCity(Localcombox2,(String) e.getItem());
            }//当籍贯重新选择省时，重新设置籍贯的市的选择框

            //归属地
            if(e.getSource().equals(Localcombox3)){
                //输入一个JComboBox类，和输入省名，设置这个ComboBox
                SelectCity(Localcombox4,(String) e.getItem());
            }
            if(e.getSource().equals(Localcombox4)){
                //输入一个JComboBox类，和输入省名市名，设置这个ComboBox
                SelectXian(Localcombox5,(String)Localcombox3.getSelectedItem(),(String) e.getItem());
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource()==jt[10]){
            if(jt[10].getText().equals("请输入详细地址")){
                jt[10].setText("");
            }
        }
        if(e.getSource()==jt[16]){
            if(jt[16].getText().equals("请输入文件的地址，比例尽量为4:3")){
                jt[16].setText("");
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource()==jt[10]){
            if(jt[10].getText().equals("")){
                jt[10].setText("请输入详细地址");
            }
        }
        if(e.getSource()==jt[16]){
            if(jt[16].getText().equals("")){
                jt[16].setText("请输入文件的地址，比例尽量为4:3");
            }
        }
    }

    //计算某年某月有好多天
    public int countDays(int year1,int month1){
        int day2=31;
        switch (month1){
            case 1: day2=31;break;
            case 2: {
                if(year1%100!=0&&year%4==0){
                    day2=28;

                }
                else if(year1%400==0){
                    day2=28;
                }
                else {
                    day2=29;
                }
                break;
            }
            case 3: day2=31;break;
            case 4: day2=30;break;
            case 5: day2=31;break;
            case 6: day2=30;break;
            case 7: day2=31;break;
            case 8: day2=31;break;
            case 9: day2=30;break;
            case 10:day2=31;break;
            case 11:day2=30;break;
            case 12:day2=31;break;
        }
        return day2;
    }

    //设置省
    public void SelectProvince(JComboBox<String>combobox){
        try{
            String Localsql="select 名称 from xz where 父代码=0";
            PreparedStatement LocalpreSql=InserSql.conn.prepareStatement(Localsql);
            ResultSet Localrs=InserSql.SelectSql(LocalpreSql);
            while (Localrs.next()){
                combobox.addItem(Localrs.getString("名称"));
            }
            combobox.addItemListener(this);
            Localrs.close();
            LocalpreSql.close();
        }catch (Exception e){
            System.out.println("查询地方 => " + e.getMessage());
        }
    }
    //设置市
    public void SelectCity(JComboBox<String>combobox,String province){
        try{
            String Localsql2="select 名称 from xz where 父代码=( select 代码 from xz where 名称=?)";
            PreparedStatement LocalpreSql2=InserSql.conn.prepareStatement(Localsql2);
            LocalpreSql2.setString(1,province);
            ResultSet Localrs2=InserSql.SelectSql(LocalpreSql2);
            combobox.removeItemListener(this);
            combobox.removeAllItems();
            while (Localrs2.next()){
                combobox.addItem(Localrs2.getString("名称"));
            }
            combobox.addItemListener(this);
            Localrs2.close();
            LocalpreSql2.close();
        }catch (Exception e){
            System.out.println("查询地方 => " + e.getMessage());
        }
    }
    //设置县
    public void SelectXian(JComboBox<String>combobox,String ffather,String father){
        try{
            String Localsql3="select 名称 from xz where 父代码=(select 代码 from xz where 名称=?" +
                    " and 父代码=(select 代码 from xz where 名称=?) )";
            PreparedStatement LocalpreSql3=InserSql.conn.prepareStatement(Localsql3);
            LocalpreSql3.setString(1,father);
            LocalpreSql3.setString(2,ffather);
            ResultSet Localrs3=InserSql.SelectSql(LocalpreSql3);
            combobox.removeItemListener(this);
            combobox.removeAllItems();
            while (Localrs3.next()){
                combobox.addItem(Localrs3.getString("名称"));
            }
            combobox.addItemListener(this);
            Localrs3.close();
            LocalpreSql3.close();
        }catch (Exception e){
            System.out.println("查询地方 => " + e.getMessage());
        }
    }

    //获取学生信息有哪些列
    private void getStudentInformaionColumnName(){
        try{
            sql="select top (1) * from 学生表 where 1=1";
            preSql=InserSql.conn.prepareStatement(sql);
            rs=InserSql.SelectSql(preSql);
            rsmd=rs.getMetaData();
            for(int i=1;i<=16;i++){
                StudentInformaionColumnName[i-1]=rsmd.getColumnName(i);
            }
            rs.close();
            preSql.close();
        }catch (Exception e){
            System.out.println("插入panelsql => " + e.getMessage());
        }
    }


    //向数据库写入信息
    private void InsertSqlData(){
        String InsertSqlData="insert into 学生表 values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement pre=InserSql.conn.prepareStatement(InsertSqlData);
            pre.setString(1,(String)StudentInformation.get(StudentInformaionColumnName[0]));
            pre.setString(2,(String)StudentInformation.get(StudentInformaionColumnName[1]));
            pre.setString(3,(String)StudentInformation.get(StudentInformaionColumnName[2]));
            pre.setString(4,(String)StudentInformation.get(StudentInformaionColumnName[3]));
            pre.setString(5,(String)StudentInformation.get(StudentInformaionColumnName[4]));
            pre.setTimestamp(6,(java.sql.Timestamp)StudentInformation.get(StudentInformaionColumnName[5]));
            pre.setString(7,(String)StudentInformation.get(StudentInformaionColumnName[6]));
            pre.setString(8,(String)StudentInformation.get(StudentInformaionColumnName[7]));
            pre.setString(9,(String)StudentInformation.get(StudentInformaionColumnName[8]));
            pre.setString(10,(String)StudentInformation.get(StudentInformaionColumnName[9]));
            pre.setString(11,(String)StudentInformation.get(StudentInformaionColumnName[10]));
            pre.setString(12,(String)StudentInformation.get(StudentInformaionColumnName[11]));
            pre.setString(13,(String)StudentInformation.get(StudentInformaionColumnName[12]));
            pre.setString(14,(String)StudentInformation.get(StudentInformaionColumnName[13]));
            pre.setString(15,(String)StudentInformation.get(StudentInformaionColumnName[14]));
            pre.setBinaryStream(16,fileInputStream,fileInputStream.available());
            if(InserSql.UpdataOrDeleteOrInsertSql(pre)){
                JOptionPane.showMessageDialog(null,"添加成功","提示",JOptionPane.PLAIN_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null,"添加失败,请注意输入长度","提示",JOptionPane.PLAIN_MESSAGE);
            }
        }catch (Exception e){
            System.out.println("插入失败数据库=>"+e.getMessage());
            JOptionPane.showMessageDialog(null,"添加失败","提示",JOptionPane.PLAIN_MESSAGE);
        }
    }

}

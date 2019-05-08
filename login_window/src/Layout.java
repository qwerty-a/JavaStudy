import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Layout implements KeyListener, MouseListener,FocusListener {
    JButton jb1;
    JButton jb2;
    JTextField jtf1;
    JTextField jtf2;

    public Layout(){
        JFrame f=new JFrame("LoginWindow");
        f.setLayout(new GridLayout(3,1));
        f.setSize(300,200);
        JPanel JP1= new JPanel();
        JPanel JP2= new JPanel();
        JPanel JP3= new JPanel();
        f.add(JP1);
        f.add(JP2);
        f.add(JP3);
        JLabel jlb1 = new JLabel("用户名");
        JLabel jlb2 = new JLabel("密　码");
        jb1 = new JButton("登录");
        jb1.addActionListener(new Mylistener());
        jb2 = new JButton("取消");
        jb2.addActionListener(new Mylistener());
        jtf1 = new JTextField("请输入5位字母",10);
        jtf1.setName("账号框");
        jtf2 = new JTextField("请输入5位数字",10);
        jtf1.addKeyListener(this);
        jtf1.addMouseListener(this);
        jtf1.addFocusListener(this);
        jtf2.addKeyListener(this);
        jtf2.addFocusListener(this);
        JP1.add(jlb1);
        JP1.add(jtf1);
        JP2.add(jlb2);
        JP2.add(jtf2);
        JP3.add(jb1);
        JP3.add(jb2);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Layout layout=new Layout();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getSource()==jtf1){
            JTextField admin=(JTextField)(e.getSource());
            if(admin.getText().equals("请输入5位字母")){
                admin.setText("");
            }
            admin.getCaret().setVisible(true);
        }

        if(e.getSource()==jtf2){
            JTextField admin=(JTextField)(e.getSource());
            if(admin.getText().equals("请输入5位数字")){
                admin.setText("");
            }
            admin.getCaret().setVisible(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource()==jtf1){
            JTextField admin=(JTextField)(e.getSource());
            if(admin.getText().length()==0){
                admin.setText("请输入5位字母");
                admin.getCaret().setVisible(false);
            }
        }

        if(e.getSource()==jtf2){
            JTextField admin=(JTextField)(e.getSource());
            if(admin.getText().length()==0){
                admin.setText("请输入5位数字");
                admin.getCaret().setVisible(false);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {


    }

    @Override
    public void mouseReleased(MouseEvent e) {


    }

    @Override
    public void mouseEntered(MouseEvent e) {


    }

    @Override
    public void mouseExited(MouseEvent e) {


    }

    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource()==jtf1){
            JTextField admin=(JTextField)(e.getSource());
            admin.setBackground(Color.BLUE);
            if(admin.getText().equals("请输入5位字母")){
                admin.getCaret().setVisible(false);
            }
        }

        if(e.getSource()==jtf2){
            JTextField admin=(JTextField)(e.getSource());
            admin.setBackground(Color.BLUE);
            if(admin.getText().equals("请输入5位数字")){
                admin.getCaret().setVisible(false);
            }
        }

    }

    @Override
    public void focusLost(FocusEvent e) {
            if(e.getSource()==jtf1){
                JTextField admin=(JTextField)(e.getSource());
                admin.getCaret().setVisible(false);
                admin.setBackground(Color.WHITE);
            }

        if(e.getSource()==jtf2){
            JTextField admin=(JTextField)(e.getSource());
            admin.getCaret().setVisible(false);
            admin.setBackground(Color.WHITE);
        }
    }

}


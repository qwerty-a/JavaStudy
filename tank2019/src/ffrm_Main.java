import javax.swing.*;
import java.awt.*;

public class ffrm_Main extends JFrame{
    public static void main(String[] args) {
        JFrame f=new JFrame();
        TankPanel tp=new TankPanel();
        tp.setBackground(Color.white);
        f.add(tp);
        f.addKeyListener(tp);
        Thread thread0   = new Thread(tp);
        thread0.start();//主线程实现刷新屏幕

//        thread.run();
        f.setVisible(true);
        if (f.isUndecorated()) {// 无边框窗口
            f.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
        }else{
            f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        }
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}



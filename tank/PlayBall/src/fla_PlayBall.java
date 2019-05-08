import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class fla_PlayBall extends JFrame implements KeyListener {
    public static void main(String[] args) {
        fla_PlayBall frm=new fla_PlayBall();
        BallPaint bp=new BallPaint();
        bp.setBackground(Color.green);
        frm.add(bp);
        frm.addKeyListener(bp);
        frm.setLocation(100,100);
        ((fla_PlayBall) frm).setSize(600,500);
        ((fla_PlayBall) frm).setVisible(true);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);
    }
}

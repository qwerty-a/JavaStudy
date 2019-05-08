import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class frm_Tank {

    public static void main(String[] args){
        System.out.println("text");
        JFrame f=new JFrame();//创建一个窗口
        TankPanel tp=new TankPanel();//创建一个画板
        tp.setBackground(Color.green);
        f.add(tp);//把画板放入窗口
        f.addKeyListener(tp);//监控键盘
        f.setVisible(true);
        f.setSize(500,400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JFrame ff=new JFrame();
    }
}





class BaseTank {
    int x,y,dir;
    int h1=50,h2=40,h3=10,w1=40,w2=34,w3=30,d=15;
    public BaseTank(int x1,int y1,int dir1){
        x=x1;
        y=y1;
        dir=dir1;
    }//dir:方向 1:left 2:right 3:top 4:down
    public void DrawTank(Graphics g) {
        if (dir == 1) {
            g.setColor(Color.red);
            g.drawRect(x + 10, y + 0, w1, h1);
            g.drawRect(x + (w1 - w2) / 2 + 10, y + (h1 - h2) / 2, w2, h2);
            g.drawOval(x + (w1 - w2) / 2 + (w2 - d) / 2 + 10, y + (h1 - h2) / 2 + (h2 - d) / 2, d, d);
            g.drawRect(x, y + h1 / 2 - h3 / 2, w3, h3);
        }
        if (dir == 2) {
            g.setColor(Color.red);
            g.drawRect(x + 0, y + 0, w1, h1);
            g.drawRect(x + (w1 - w2) / 2, y + (h1 - h2) / 2, w2, h2);
            g.drawOval(x + (w1 - w2) / 2 + (w2 - d) / 2, y + (h1 - h2) / 2 + (h2 - d) / 2, d, d);
            g.drawRect(x + w1 / 2, y + h1 / 2 - h3 / 2, w3, h3);
        }
        if (dir == 3) {
            g.setColor(Color.red);
            g.drawRect(x + 0, y + h1 - w1, h1, w1);
            g.drawRect(x + (h1 - h2) / 2, y + h1 - w1 / 2 - w2 / 2, h2, w2);
            g.drawOval(x + h1 / 2 - d / 2, y + h1 - w1 / 2 - d / 2, d, d);
            g.drawRect(x + (h1 - h3) / 2, y, h3, w3);
        }
        if (dir == 4) {
            g.setColor(Color.red);
            g.drawRect(x, y, h1, w1);
            g.drawRect(x + (h1 - h2) / 2, y + (w1 - w2) / 2, h2, w2);
            g.drawOval(x + h1 / 2 - d / 2, y + w1 / 2 - d / 2, d, d);
            g.drawRect(x + (h1 - h3) / 2, y + w1 / 2, h3, w3);
        }
    }
}//构建basetank类






class TankPanel extends JPanel implements KeyListener{
    BaseTank tank =null;
    public TankPanel() {
        tank = new BaseTank(200, 200,1);
    }//构造函数

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        tank.DrawTank(g);
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()) {
            case 37:{tank.x=tank.x-5;tank.dir=1;break;}
            case 39:{tank.x=tank.x+5;tank.dir=2;break;}
            case 38:{tank.y=tank.y-5;tank.dir=3;break;}
            case 40:{tank.y=tank.y+5;tank.dir=4;break;}
        }
        this.repaint();//监控键盘
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
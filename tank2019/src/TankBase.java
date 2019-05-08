import java.awt.*;

public class TankBase {
    int x,y,dir;
    Color SpringGreen3=new Color(0,205,102);
    int h1=50,h2=40,h3=10,w1=40,w2=34,w3=30,d=15;
    public TankBase(int x1,int y1,int dir1){
        x=x1;
        y=y1;
        dir=dir1;
    }//dir:方向 1:left 2:right 3:top 4:down
    public void DrawTank(Graphics g) {
        if (dir == 1) {

//            g.drawRect(x + 10, y + 0, w1, h1);
            g.setColor(Color.green);
            g.fill3DRect(x,y,50,15,true);
            g.fill3DRect(x,y+35,50,15,true);
            g.setColor(SpringGreen3);
            g.fill3DRect(x + (w1 - w2) / 2 + 10, y + (h1 - h2) / 2, w2, h2,true);
            g.setColor(Color.green);
            g.fillOval(x + (w1 - w2) / 2 + (w2 - d) / 2 + 10, y + (h1 - h2) / 2 + (h2 - d) / 2, d, d);
            g.drawRect(x, y + h1 / 2 - h3 / 2, w3, h3);
        }
        if (dir == 2) {
//            g.setColor(Color.red);
//            g.drawRect(x + 0, y + 0, w1, h1);
            g.setColor(Color.green);
            g.fill3DRect(x,y,50,15,true);
            g.fill3DRect(x,y+35,50,15,true);
            g.setColor(SpringGreen3);
            g.fill3DRect(x + (w1 - w2) / 2, y + (h1 - h2) / 2, w2, h2,true);
            g.setColor(Color.green);
            g.fillOval(x + (w1 - w2) / 2 + (w2 - d) / 2, y + (h1 - h2) / 2 + (h2 - d) / 2, d, d);
            g.drawRect(x + w1 / 2, y + h1 / 2 - h3 / 2, w3, h3);
        }
        if (dir == 3) {
//            g.setColor(Color.red);
//            g.drawRect(x + 0, y + h1 - w1, h1, w1);
            g.setColor(Color.green);
            g.fill3DRect(x,y,15,50,true);
            g.fill3DRect(x+35,y,15,50,true);
            g.setColor(SpringGreen3);
            g.fill3DRect(x + (h1 - h2) / 2, y + h1 - w1 / 2 - w2 / 2, h2, w2,true);
            g.setColor(Color.green);
            g.fillOval(x + h1 / 2 - d / 2, y + h1 - w1 / 2 - d / 2, d, d);
            g.drawRect(x + (h1 - h3) / 2, y, h3, w3);
        }
        if (dir == 4) {
//            g.setColor(Color.red);
//            g.drawRect(x, y, h1, w1);
            g.setColor(Color.green);
            g.fill3DRect(x,y,15,50,true);
            g.fill3DRect(x+35,y,15,50,true);
            g.setColor(SpringGreen3);
            g.fill3DRect(x + (h1 - h2) / 2, y + (w1 - w2) / 2, h2, w2,true);
            g.setColor(Color.green);
            g.fillOval(x + h1 / 2 - d / 2, y + w1 / 2 - d / 2, d, d);
            g.drawRect(x + (h1 - h3) / 2, y + w1 / 2, h3, w3);
        }
    }
}


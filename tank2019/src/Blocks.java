import java.awt.*;
import java.util.Random;
import java.awt.Toolkit;
import java.awt.Dimension;

public class Blocks {
    int x,y,num;//砖块坐标，数量
    Random rand=new Random();
    int[] bolock_loction=new int[100];//记录砖块坐标的数组
    int[] random_high=new int[100];//记录对应砖块的对应随机大小的数组
    GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
    Rectangle rect=ge.getMaximumWindowBounds();
    //获得系统屏幕分辨率: Toolkit.getDefaultToolkit() ;
    //得到当前屏幕的分辨率：Toolkit.getDafaultToolkit().getScreenSize()
//    Toolkit t = Toolkit.getDefaultToolkit();
//    Dimension size=t.getScreenSize();
    //如果系统屏幕分辨率是1024*768时，
    //size.getWidth()的值为1024
    //size.getHeight()的值为768。
    public Blocks(int num1){
        num=num1;
        bolock_loction[0]=num;
        //产生随机坐标和随机高度，并保存
        for(int i=1;i<=num;i++) {
            x = rand.nextInt( rect.width/5- 14)*5;
            y = rand.nextInt( rect.height/5- 14)*5;
            bolock_loction[2*i-1] = x;
            bolock_loction[2*i]=y;
            random_high[i]=(rand.nextInt(4)+9)*5;
        }

    }

    //画出随机砖块
    public void DrawBlocks(Graphics g){
        g.setColor(Color.lightGray);
        for(int i=1;i<=num;i++) {
            g.fill3DRect(bolock_loction[2*i-1] ,bolock_loction[2*i] ,random_high[i],random_high[i],true);
        }
    }

}


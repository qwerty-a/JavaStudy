import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class MyTank extends TankBase implements Runnable{
    int[] BlocksLocation;
    int[] BlocksRandomHigh;
    int[] EnemyTankLocation;
    int[] bullet_location;
    int[] my_bullet_location;
    int HitBulletNum;
    Color SpringGreen3=new Color(0,205,102);
    boolean[] CanShoot=new boolean[100];//存储第几个炮弹是否可以发射
    int ShotTime=-790;//设定每800ms可以发一发炮弹
    int BulletNum;
    Image[] image=new Image[3];//图片数组
    int DirStop=0;//记录不能向那个方向前进，0为不停止
    int c=2;//循环播放图片时，生成连续数字
    int BoomTime=0;//记录爆炸特效播放时间
    boolean IsHit=false;//记录我方坦克是否被击中
    public MyTank(int x1,int y1 ,int dir1,int[] BlocksLocation1,int[] BlocksRandomHigh1,int[] EnemyTankLocation1,int[] bullet_location1,int[] my_bullet_location1){
        super(x1,y1,dir1);
        BlocksLocation=BlocksLocation1;
        BlocksRandomHigh=BlocksRandomHigh1;
        EnemyTankLocation=EnemyTankLocation1;
        bullet_location=bullet_location1;
        my_bullet_location=my_bullet_location1;
        for(int i=1;i<=10;i++){
            CanShoot[i]=false;
        }
        try {
            image[0]= ImageIO.read(new File("G:\\JavaStudy\\tank2019\\boom\\1.png"));
            image[1]=ImageIO.read(new File("G:\\JavaStudy\\tank2019\\boom\\2.png"));
            image[2]=ImageIO.read(new File("G:\\JavaStudy\\tank2019\\boom\\3.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//碰到墙后，不能继续向该方向前进
    public void Block_stop(){
        for(int a=1;a<BlocksLocation[0];a++){
            if(  x==BlocksLocation[2*a-1]+BlocksRandomHigh[a]&&y+h1>=BlocksLocation[2*a]&&y<=BlocksLocation[2*a]+BlocksRandomHigh[a])
            {DirStop=1;}
            if(  x+h1==BlocksLocation[2*a-1]&&y+h1>=BlocksLocation[2*a]&&y<=BlocksLocation[2*a]+BlocksRandomHigh[a])
            {DirStop=2;}
            if(  y==BlocksLocation[2*a]+BlocksRandomHigh[a]&&x+h1>=BlocksLocation[2*a-1]&&x<=BlocksLocation[2*a-1]+BlocksRandomHigh[a])
            {DirStop=3;}
            if(  y+h1==BlocksLocation[2*a]&&x+h1>=BlocksLocation[2*a-1]&&x<=BlocksLocation[2*a-1]+BlocksRandomHigh[a])
            {DirStop=4;}
            }
    }

//判断我方坦克是否被敌方坦克击中或碰到，如果击中或碰到，我方坦克爆炸
    public void IsHitOrIsTouched(){
        for(int i=1;i<=EnemyTankLocation[0];i++){
            if(x>=EnemyTankLocation[2*i-1]&&x<=EnemyTankLocation[2*i-1]+h1
                    &&y>=EnemyTankLocation[2*i]&&y<=EnemyTankLocation[2*i])
                IsHit=true;
            if(x+h1>=EnemyTankLocation[2*i-1]&&x+h1<=EnemyTankLocation[2*i-1]+h1
                    &&y>=EnemyTankLocation[2*i]&&y<=EnemyTankLocation[2*i]+h1)
                IsHit=true;
            if(x>=EnemyTankLocation[2*i-1]&&x<=EnemyTankLocation[2*i-1]+h1
                    &&y+h1>=EnemyTankLocation[2*i]&&y+h1<=EnemyTankLocation[2*i]+h1)
                IsHit=true;
            if(x+h1>=EnemyTankLocation[2*i-1]&&x+h1<=EnemyTankLocation[2*i-1]+h1
                    &&y+h1>=EnemyTankLocation[2*i]&&y+h1<=EnemyTankLocation[2*i]+h1)
                IsHit=true;
        }
        for(int j=1;j<=2*EnemyTankLocation[0];j++){
            if(bullet_location[2*j-1]>=x&&bullet_location[2*j-1]<=x+h1
                    &&bullet_location[2*j]>=y&&bullet_location[2*j]<=y+h1)
            {IsHit=true;HitBulletNum=j;}
            if(bullet_location[2*j-1]+bullet_location[0]>=x&&bullet_location[2*j-1]+bullet_location[0]<=x+h1
                    &&bullet_location[2*j]>=y&&bullet_location[2*j]<=y+h1)
            {IsHit=true;HitBulletNum=j;}
            if(bullet_location[2*j-1]>=x&&bullet_location[2*j-1]<=x+h1
                    &&bullet_location[2*j]+bullet_location[0]>=y&&bullet_location[2*j]+bullet_location[0]<=y+h1)
            {IsHit=true;HitBulletNum=j;}
            if(bullet_location[2*j-1]+bullet_location[0]>=x&&bullet_location[2*j-1]+bullet_location[0]<=x+h1
                    &&bullet_location[2*j]+bullet_location[0]>=y&&bullet_location[2*j]+bullet_location[0]<=y+h1)
            {IsHit=true;HitBulletNum=j;}
        }

    }

//重写画出坦克函数
    public void DrawTank(Graphics g) {
        if(!IsHit) {
            if (dir == 1) {

//            g.drawRect(x + 10, y + 0, w1, h1);
                g.setColor(Color.green);
                g.fill3DRect(x,y,50,15,true);
                g.fill3DRect(x,y+35,50,15,true);
                g.setColor(SpringGreen3);
                g.fill3DRect(x + (w1 - w2) / 2 + 10, y + (h1 - h2) / 2, w2, h2,true);
                g.setColor(Color.red);
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
                g.setColor(Color.red);
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
                g.setColor(Color.red);
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
                g.setColor(Color.red);
                g.fillOval(x + h1 / 2 - d / 2, y + w1 / 2 - d / 2, d, d);
                g.drawRect(x + (h1 - h3) / 2, y + w1 / 2, h3, w3);
            }
        }else{
            g.drawImage(image[c], x, y, 40, 40, null);
        }
    }

    @Override
    public void run() {
        while (!IsHit || BoomTime != 1000) {
            try {
                Thread.sleep(10);
                IsHitOrIsTouched();
                ShotTime=ShotTime+10;

                //如果被击中，循环播放图片
                if(IsHit&&BoomTime!=1000){
                    c=(c+1)%3;
                    BoomTime=BoomTime+10;
                }

                //每800ms让一个新的子弹可以被发射
                if(ShotTime%800==0){
                    CanShoot[ShotTime/800]=true;
                    BulletNum=ShotTime/800;
                    ShotTime=ShotTime%8000;
                }

            } catch (Exception e) {
                System.out.println("wrong");
            }
        }
    }
}

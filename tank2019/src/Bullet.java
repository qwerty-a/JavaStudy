import java.awt.*;


public class Bullet implements Runnable{
    int BulletLocation_x,BulletLocation_y,BulletLocation_dir;//子弹坐标
    int BulletLocation_Speed=2;
    int Bullet_High=10;
    int[] BlocksLocation;
    int[] BlocksRandomHigh;
    boolean IsBoom=false;//是否要爆炸
    boolean IsHit=false;//是否需要继续子弹线程
    boolean IsStart=false;//是否开始画子弹
    String a="i am enemy";//测试时候用的，没有作用


    //构造函数
    public Bullet(int x1,int y1,int dir1,int[] BlocksLocation1,int[] BlocksRandomHigh1){
        BlocksLocation=BlocksLocation1;
        BlocksRandomHigh=BlocksRandomHigh1;
        BulletLocation_x=x1;
        BulletLocation_y=y1;
        BulletLocation_dir=dir1;
    }


    //画出子弹
    public void DrawBallet(Graphics g){
        if(!IsBoom&&IsStart) {
            g.fill3DRect(BulletLocation_x, BulletLocation_y, Bullet_High, Bullet_High, true);
        }//当开始运行切未爆炸时，画出子弹

    }


    //判断是否子弹碰到墙和坦克，碰到后，将IsBoom改为true
    public void Isboom1(){
        for(int a=1;a<=BlocksLocation[0];a++){
            if(  BulletLocation_x>=BlocksLocation[2*a-1]&&BulletLocation_x<=BlocksLocation[2*a-1]+BlocksRandomHigh[a]
               &&BulletLocation_y>=BlocksLocation[2*a]&&BulletLocation_y<=BlocksLocation[2*a]+BlocksRandomHigh[a])
                IsBoom=true;
            if(  BulletLocation_x+Bullet_High>=BlocksLocation[2*a-1]&&BulletLocation_x+Bullet_High<=BlocksLocation[2*a-1]+BlocksRandomHigh[a]
                    &&BulletLocation_y>=BlocksLocation[2*a]&&BulletLocation_y<=BlocksLocation[2*a]+BlocksRandomHigh[a])
                IsBoom=true;
            if(  BulletLocation_x>=BlocksLocation[2*a-1]&&BulletLocation_x<=BlocksLocation[2*a-1]+BlocksRandomHigh[a]
                    &&BulletLocation_y+Bullet_High>=BlocksLocation[2*a]&&BulletLocation_y+Bullet_High<=BlocksLocation[2*a]+BlocksRandomHigh[a])
                IsBoom=true;
            if(  BulletLocation_x+Bullet_High>=BlocksLocation[2*a-1]&&BulletLocation_x+Bullet_High<=BlocksLocation[2*a-1]+BlocksRandomHigh[a]
                    &&BulletLocation_y+Bullet_High>=BlocksLocation[2*a]&&BulletLocation_y+Bullet_High<=BlocksLocation[2*a]+BlocksRandomHigh[a])
                IsBoom=true;
        }
    }


    @Override
    public void run(){
        while (!IsHit) {
            try{
                Thread.sleep(10);
                Isboom1();
                System.out.println(a);
                //子弹没有碰到墙，且已经发出子弹
                if(!IsBoom&&IsStart){
                    if(BulletLocation_dir==1){
                        BulletLocation_x = BulletLocation_x - BulletLocation_Speed;
                    }
                    if(BulletLocation_dir==2){
                        BulletLocation_x = BulletLocation_x + BulletLocation_Speed;
                    }
                    if(BulletLocation_dir==3){
                        BulletLocation_y = BulletLocation_y - BulletLocation_Speed;
                    }
                    if(BulletLocation_dir==4) {
                        BulletLocation_y = BulletLocation_y + BulletLocation_Speed;
                    }
                }else{//当撞在墙时，将子弹放在看不到地方
                    BulletLocation_x=-1000;
                    BulletLocation_y=-1000;
                }
            }
            catch (Exception e) {
                System.out.println("wrong");
            }
        }
    }
}



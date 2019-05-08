import java.awt.*;
import java.util.Random;
import javax.imageio.*;
import java.io.*;


public class EnemyTank extends TankBase implements Runnable {
    int a,b;
    int RandomTime;//产生随机时间
    int Time=0,c=1;//时间记录
    int[] bolock_loction;//记录砖块坐标的数组
    int[] random_high;//记录对应砖块的对应随机大小的数组
    int[] EnemyTankLocation;
    int[] my_bullet_location;
    int ThisTankNum;
    Image[] image=new Image[3];//图片数组
    boolean IsHit=false;
    boolean StartShoot=false;
    int BoomTime=0;
    int i;
    int HitBulletNum=0;//0为未有炮弹击中
    GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
    Rectangle rect=ge.getMaximumWindowBounds();
    Random rand=new Random();
    public EnemyTank(int x1,int y1,int dir1,int[] bolock_loction1,int[] random_high1,int[] EnemyTankLocation1,int ThisTankNum1,int[] my_bullet_location1){
        super(x1, y1,dir1);
        RandomTime=(rand.nextInt(4)+2)*1000;
        bolock_loction=bolock_loction1;
        random_high=random_high1;
        EnemyTankLocation=EnemyTankLocation1;
        my_bullet_location=my_bullet_location1;
        ThisTankNum=ThisTankNum1;
        try {
            image[0]=ImageIO.read(new File("G:\\JavaStudy\\tank2019\\boom\\1.png"));
            image[1]=ImageIO.read(new File("G:\\JavaStudy\\tank2019\\boom\\2.png"));
            image[2]=ImageIO.read(new File("G:\\JavaStudy\\tank2019\\boom\\3.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //碰到边界返回
    public void border_back(){
        if(EnemyTank.super.x<=0){EnemyTank.super.dir=2;}
        if(EnemyTank.super.y<=0){EnemyTank.super.dir=4;}
        if(EnemyTank.super.x>=rect.width-50){EnemyTank.super.dir=1;}
        if(EnemyTank.super.y>=rect.height-50){EnemyTank.super.dir=3;}
    }

    //碰到砖块返回
    public void block_back(){
        for(int i=1;i<=bolock_loction[0];i++){
            if(dir==1||dir==2)
            {if(  EnemyTank.super.x+EnemyTank.super.h1>=bolock_loction[2*i-1]
                    &&EnemyTank.super.x+EnemyTank.super.h1<=bolock_loction[2*i-1]+random_high[i]
                    &&EnemyTank.super.y+EnemyTank.super.h1>=bolock_loction[2*i]
                    &&EnemyTank.super.y<=bolock_loction[2*i]+random_high[i])
            {EnemyTank.super.dir=1;}
            if(  EnemyTank.super.x>=bolock_loction[2*i-1]
                    &&EnemyTank.super.x<=bolock_loction[2*i-1]+random_high[i]
                    &&EnemyTank.super.y+EnemyTank.super.h1>=bolock_loction[2*i]
                    &&EnemyTank.super.y<=bolock_loction[2*i]+random_high[i])
            {EnemyTank.super.dir=2;}}
            else{
            if(  EnemyTank.super.y+EnemyTank.super.h1>=bolock_loction[2*i]
                    &&EnemyTank.super.y+EnemyTank.super.h1<=bolock_loction[2*i]+random_high[i]
                    &&EnemyTank.super.x+EnemyTank.super.h1>=bolock_loction[2*i-1]
                    &&EnemyTank.super.x<=bolock_loction[2*i-1]+random_high[i])
            {EnemyTank.super.dir=3;}
            if(  EnemyTank.super.y>=bolock_loction[2*i]
                    &&EnemyTank.super.y<=bolock_loction[2*i]+random_high[i]
                    &&EnemyTank.super.x+EnemyTank.super.h1>=bolock_loction[2*i-1]
                    &&EnemyTank.super.x<=bolock_loction[2*i-1]+random_high[i])
            {EnemyTank.super.dir=4;}}
        }
    }

    //碰到坦克返回
    public void tank_back(){
        for(int i=1;i<=EnemyTankLocation[0];i++){
            if(i==ThisTankNum) continue;;
            if(dir==1||dir==2)
            {if(  EnemyTank.super.x+EnemyTank.super.h1>=EnemyTankLocation[2*i-1]
                    &&EnemyTank.super.x+EnemyTank.super.h1<=EnemyTankLocation[2*i-1]+EnemyTank.super.h1
                    &&EnemyTank.super.y+EnemyTank.super.h1>=EnemyTankLocation[2*i]
                    &&EnemyTank.super.y<=EnemyTankLocation[2*i]+EnemyTank.super.h1)
            {EnemyTank.super.dir=1;}
                if(  EnemyTank.super.x>=EnemyTankLocation[2*i-1]
                        &&EnemyTank.super.x<=EnemyTankLocation[2*i-1]+EnemyTank.super.h1
                        &&EnemyTank.super.y+EnemyTank.super.h1>=EnemyTankLocation[2*i]
                        &&EnemyTank.super.y<=EnemyTankLocation[2*i]+EnemyTank.super.h1)
                {EnemyTank.super.dir=2;}}
            else{
                if(  EnemyTank.super.y+EnemyTank.super.h1>=EnemyTankLocation[2*i]
                        &&EnemyTank.super.y+EnemyTank.super.h1<=EnemyTankLocation[2*i]+EnemyTank.super.h1
                        &&EnemyTank.super.x+EnemyTank.super.h1>=EnemyTankLocation[2*i-1]
                        &&EnemyTank.super.x<=EnemyTankLocation[2*i-1]+EnemyTank.super.h1)
                {EnemyTank.super.dir=3;}
                if(  EnemyTank.super.y>=EnemyTankLocation[2*i]
                        &&EnemyTank.super.y<=EnemyTankLocation[2*i]+EnemyTank.super.h1
                        &&EnemyTank.super.x+EnemyTank.super.h1>=EnemyTankLocation[2*i-1]
                        &&EnemyTank.super.x<=EnemyTankLocation[2*i-1]+EnemyTank.super.h1)
                {EnemyTank.super.dir=4;}}
        }
    }


    //判断是否被击中
    public void IsHit1(){
        for(int j=1;j<=my_bullet_location[0];j++){
            if(my_bullet_location[2*j-1]>=x&&my_bullet_location[2*j-1]<=x+h1
                    &&my_bullet_location[2*j]>=y&&my_bullet_location[2*j]<=y+h1)
            {IsHit=true;HitBulletNum=j;}
            if(my_bullet_location[2*j-1]+10>=x&&my_bullet_location[2*j-1]+10<=x+h1
                    &&my_bullet_location[2*j]>=y&&my_bullet_location[2*j]<=y+h1)
            {IsHit=true;HitBulletNum=j;}
            if(my_bullet_location[2*j-1]>=x&&my_bullet_location[2*j-1]<=x+h1
                    &&my_bullet_location[2*j]+10>=y&&my_bullet_location[2*j]+10<=y+h1)
            {IsHit=true;HitBulletNum=j;}
            if(my_bullet_location[2*j-1]+10>=x&&my_bullet_location[2*j-1]+10<=x+h1
                    &&my_bullet_location[2*j]+10>=y&&my_bullet_location[2*j]+10<=y+h1)
            {IsHit=true;HitBulletNum=j;}
        }
    }

    public void Boom(Graphics g){
        try {
            if(IsHit){
                if(BoomTime!=1000)
                    g.drawImage(image[i], a, b, 40, 40, null);//通过轮播爆炸图片实现特效
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while (!IsHit||BoomTime!=1000) {
            try {
                if(c==1)
                {Thread.sleep(3000);
                c=2;
                }
                else
                Thread.sleep(100);
                IsHit1();
                if(!IsHit) {
                    border_back();//碰到边界返回
                    block_back();//碰到砖块返回
                    tank_back();

                    //实现移动
                    if (EnemyTank.super.dir == 2) {
                        EnemyTank.super.x = EnemyTank.super.x + 5;
                    }
                    if (EnemyTank.super.dir == 1) {
                        EnemyTank.super.x = EnemyTank.super.x - 5;
                    }
                    if (EnemyTank.super.dir == 3) {
                        EnemyTank.super.y = EnemyTank.super.y - 5;
                    }
                    if (EnemyTank.super.dir == 4) {
                        EnemyTank.super.y = EnemyTank.super.y + 5;
                    }

                    a=x;
                    b=y;


                    //实现随机方向和随机方向持续时间
                    Time = Time + 100;
                    if (Time == RandomTime) {
                        EnemyTank.super.dir = (rand.nextInt(4) + 1);
                        Time = 0;
                        RandomTime = (rand.nextInt(4) + 1) * 1000;
                    }
                }else{
                    BoomTime=BoomTime+100;
                    i=(i+1)%3;
                    x=-1000;
                    y=-1000;
                }
                StartShoot=true;

            } catch (Exception e) {
                System.out.println("wrong");
            }
        }
    }


//    public void RandowRun(){
//        RandomTime=(rand.nextInt(4)+3)*2000;
//        Timer timer = new Timer(true);
//        timer.schedule(new TimerTask() {
//            public void run() {
//                System.out.println("-------设定要指定任务--------");
//                EnemyTank.super.dir=(rand.nextInt(4)+1);
//            }
//        }, 0,RandomTime);
//        Timer timer2 = new Timer(true);
//        timer2.schedule(new TimerTask() {
//            public void run() {
//                if(EnemyTank.super.x==0){EnemyTank.super.dir=2;}
//                if(EnemyTank.super.y==0){EnemyTank.super.dir=4;}
//                if(EnemyTank.super.x==1000){EnemyTank.super.dir=1;}
//                if(EnemyTank.super.y==600){EnemyTank.super.dir=3;}
//                if(EnemyTank.super.dir==2)
//                {EnemyTank.super.x=EnemyTank.super.x+4;}
//                if(EnemyTank.super.dir==1)
//                {EnemyTank.super.x=EnemyTank.super.x-4;}
//                if(EnemyTank.super.dir==3)
//                {EnemyTank.super.y=EnemyTank.super.y-4;}
//                if(EnemyTank.super.dir==4)
//                {EnemyTank.super.y=EnemyTank.super.y+4;}
//                System.out.println("-------设定要指定任务2--------");
//            }
//        }, 100,100);
//    }

}


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class TankPanel extends JPanel implements KeyListener , Runnable{
    TankBase tank;
    EnemyTank[] enemyTanks=new EnemyTank[200];//一个敌方坦克数组
    Bullet[] bullets=new Bullet[200];//敌方坦克的子弹数组
    Bullet[] mybullets=new Bullet[200];//我方坦克的子弹数组
    Thread[] thread=new Thread[200];//敌方坦克线程数组
    Thread[] Bullets_Thread=new Thread[200];//敌方子弹数组
    Thread[] MyBullets_Thread=new Thread[200];//我方子弹数组
    Blocks blocks;
    Bullet bullet;
    int EnenyTank_Num=20;//敌方坦克数量
    int Blocks_Num=15;//砖块数量
    int[] tank_location=new int[200];//记录所有初始坦克的坐标，其中[0]为敌方坦克数量，最后一个是我方坦克初始坐标
    int[] bullet_location=new int[400];//敌方所有子弹坐标，其中[0]为子弹高度
    int[] now_bullet_location=new int[400];//记录所有敌方坦克的实时坐标，其中[0]为敌方坦克数量
    int[] my_bullet_location=new int[200];//记录我方坦克子弹坐标
    int MybulltNum=10;//我方最多发十发同时运行的子弹
    Random rand=new Random();
    int random_x,random_y;//用于生成对方坦克随机坐标
    int q,RunTime=0;
    MyTank mytank;
    GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
    Rectangle rect=ge.getMaximumWindowBounds();
    public TankPanel() {
        tank_location[0]=EnenyTank_Num;
        blocks=new Blocks(Blocks_Num);
        bullet=new Bullet(1,1,1,blocks.bolock_loction,blocks.random_high);
        tank=new TankBase(200, 200,1);

        //随机生成所有坦克初始坐标，要求与墙不重合，坦克坦克之间不重合
        for(int i=1;i<=EnenyTank_Num+1;i++) {
            q=0;
            random_x = (rand.nextInt(rect.width/5 - 10))*5;
            random_y = (rand.nextInt(rect.height/5 - 10))*5;
            for(int k=1;k<=Blocks_Num;k++){
                if(random_x>=blocks.bolock_loction[2*k-1]&&random_x<=blocks.bolock_loction[2*k-1]+blocks.random_high[k]
                &&random_y>=blocks.bolock_loction[2*k]&&random_y<=blocks.bolock_loction[2*k]+blocks.random_high[k]){
                    i--;
                    q=1;
                    break;
                }
                if(random_x+tank.h1>=blocks.bolock_loction[2*k-1]&&random_x+tank.h1<=blocks.bolock_loction[2*k-1]+blocks.random_high[k]
                        &&random_y>=blocks.bolock_loction[2*k]&&random_y<=blocks.bolock_loction[2*k]+blocks.random_high[k]){
                    i--;
                    q=1;
                    break;
                }
                if(random_x>=blocks.bolock_loction[2*k-1]&&random_x<=blocks.bolock_loction[2*k-1]+blocks.random_high[k]
                        &&random_y+tank.h1>=blocks.bolock_loction[2*k]&&random_y+tank.h1<=blocks.bolock_loction[2*k]+blocks.random_high[k]){
                    i--;
                    q=1;
                    break;
                }
                if(random_x+tank.h1>=blocks.bolock_loction[2*k-1]&&random_x+tank.h1<=blocks.bolock_loction[2*k-1]+blocks.random_high[k]
                        &&random_y+tank.h1>=blocks.bolock_loction[2*k]&&random_y+tank.h1<=blocks.bolock_loction[2*k]+blocks.random_high[k]){
                    i--;
                    q=1;
                    break;
                }
            }
            if(q==1) continue;
            tank_location[2*i-1]=random_x;
            tank_location[2*i]=random_y;
            for(int b=1;b<i;b++){
                if(random_x>=tank_location[2*b-1]&&random_x<=tank_location[2*b-1]+tank.h1
                        &&random_y>=tank_location[2*b]&&random_y<=tank_location[2*b]+tank.h1){
                    i--;
                    break;
                }
                if(random_x+tank.h1>=tank_location[2*b-1]&&random_x+tank.h1<=tank_location[2*b-1]+tank.h1
                        &&random_y>=tank_location[2*b]&&random_y<=tank_location[2*b]+tank.h1){
                    i--;
                    break;
                }
                if(random_x>=tank_location[2*b-1]&&random_x<=tank_location[2*b-1]+tank.h1
                        &&random_y+tank.h1>=tank_location[2*b]&&random_y+tank.h1<=tank_location[2*b]+tank.h1){
                    i--;
                    break;
                }
                if(random_x+tank.h1>=tank_location[2*b-1]&&random_x+tank.h1<=tank_location[2*b-1]+tank.h1
                        &&random_y+tank.h1>=tank_location[2*b]&&random_y+tank.h1<=tank_location[2*b]+tank.h1){
                    i--;
                    break;
                }
            }

        }

        //初始化敌方坦克
        for(int i=1;i<=EnenyTank_Num;i++){
            enemyTanks[i]=new EnemyTank(tank_location[2*i-1],tank_location[2*i],rand.nextInt(4)+1,blocks.bolock_loction,blocks.random_high,tank_location,i,my_bullet_location);
            thread[i]= new Thread(enemyTanks[i]);
            thread[i].start();
        }


        //初始化子弹位置数组
        for(int i=1;i<EnenyTank_Num;i++) {
            if (enemyTanks[i].dir == 1) {
                bullet_location[2 * i - 1] = tank_location[2 * i - 1] + tank.w3 - bullet.Bullet_High;
                bullet_location[2 * i] = tank_location[2 * i] + tank.h1 / 2 - bullet.Bullet_High / 2;
            }
            if (enemyTanks[i].dir == 2) {
                bullet_location[2 * i - 1] = tank_location[2 * i - 1] + tank.w1 / 2;
                bullet_location[2 * i] = tank_location[2 * i] + tank.h1 / 2 - bullet.Bullet_High / 2;
            }
            if (enemyTanks[i].dir == 3) {
                bullet_location[2 * i - 1] = tank_location[2 * i - 1] + tank.h1 / 2 - bullet.Bullet_High / 2;
                bullet_location[2 * i] = tank_location[2 * i] + tank.w3 - bullet.Bullet_High;
            }
            if (enemyTanks[i].dir == 4) {
                bullet_location[2 * i - 1] = tank_location[2 * i - 1] + tank.h1 / 2 - bullet.Bullet_High / 2;
                bullet_location[2 * i] = tank_location[2 * i] + tank.w1 / 2;
            }
        }
        for(int i=1;i<EnenyTank_Num;i++) {
            int j=i+EnenyTank_Num;
            if (enemyTanks[i].dir == 1) {
                bullet_location[2 * j - 1] = tank_location[2 * j - 1] + tank.w3 - bullet.Bullet_High;
                bullet_location[2 * j] = tank_location[2 * j] + tank.h1 / 2 - bullet.Bullet_High / 2;
            }
            if (enemyTanks[i].dir == 2) {
                bullet_location[2 * j - 1] = tank_location[2 * j - 1] + tank.w1 / 2;
                bullet_location[2 * j] = tank_location[2 * j] + tank.h1 / 2 - bullet.Bullet_High / 2;
            }
            if (enemyTanks[i].dir == 3) {
                bullet_location[2 * j - 1] = tank_location[2 * j - 1] + tank.h1 / 2 - bullet.Bullet_High / 2;
                bullet_location[2 * j] = tank_location[2 * j] + tank.w3 - bullet.Bullet_High;
            }
            if (enemyTanks[i].dir == 4) {
                bullet_location[2 * j - 1] = tank_location[2 * j - 1] + tank.h1 / 2 - bullet.Bullet_High / 2;
                bullet_location[2 * j] = tank_location[2 * j] + tank.w1 / 2;
            }
        }


        bullet_location[0]=bullet.Bullet_High;
        now_bullet_location[0]=bullet.Bullet_High;

        //初始化实时坦克数组
        for(int i=1;i<=4*EnenyTank_Num;i++){
            now_bullet_location[i]=-1000;
        }

        //初始化敌方子弹
        for(int i=1;i<=2*EnenyTank_Num;i++){
            bullets[i]=new Bullet(-100,-100,2,blocks.bolock_loction,blocks.random_high);
            Bullets_Thread[i]=new Thread(bullets[i]);
            Bullets_Thread[i].start();
        }

        //初始化我方坦克
        mytank =new MyTank(tank_location[(EnenyTank_Num+1)*2-1],tank_location[(EnenyTank_Num+1)*2],3,blocks.bolock_loction,blocks.random_high,tank_location,now_bullet_location,my_bullet_location);
        Thread mytank_thread=new Thread(mytank);
        mytank_thread.start();
        my_bullet_location[0]=MybulltNum;

        //初始化我方子弹
        for(int i=1;i<=MybulltNum;i++){
            my_bullet_location[2*i-1]=-1000;
            my_bullet_location[2*i]=-1000;
            mybullets[i]=new Bullet(-1000,-1000,1,blocks.bolock_loction,blocks.random_high);
            mybullets[i].a="i am mytank";
            MyBullets_Thread[i]=new Thread(mybullets[i]);
            MyBullets_Thread[i].start();
        }
    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);
        mytank.DrawTank(g);

        for(int v=1;v<=EnenyTank_Num;v++){

            //如果我方子弹击中敌方坦克后，该子弹爆炸
            if(enemyTanks[v].HitBulletNum!=0){
                mybullets[enemyTanks[v].HitBulletNum].IsBoom=true;
                enemyTanks[v].HitBulletNum=0;
            }

            //如果敌方坦克被击毁，对应子弹出边界后，停止该线程
            if(enemyTanks[v].IsHit){
                if(bullets[v].BulletLocation_x>rect.width+100)
                    bullets[v].IsHit=true;
                if(bullets[v].BulletLocation_y>rect.height+100)
                    bullets[v].IsHit=true;
                if(bullets[v].BulletLocation_x<-20)
                    bullets[v].IsHit=true;
                if(bullets[v].BulletLocation_y<-20)
                    bullets[v].IsHit=true;
                if(bullets[v+EnenyTank_Num].BulletLocation_x>rect.width+100)
                    bullets[v+EnenyTank_Num].IsHit=true;
                if(bullets[v+EnenyTank_Num].BulletLocation_y>rect.height+100)
                    bullets[v+EnenyTank_Num].IsHit=true;
                if(bullets[v+EnenyTank_Num].BulletLocation_x<-20)
                    bullets[v+EnenyTank_Num].IsHit=true;
                if(bullets[v+EnenyTank_Num].BulletLocation_y<-20)
                    bullets[v+EnenyTank_Num].IsHit=true;
            }

            //实时记录敌方坦克坐标
            tank_location[2*v-1]=enemyTanks[v].x;
            tank_location[2*v]=enemyTanks[v].y;


            enemyTanks[v].DrawTank(g);
            bullets[v].DrawBallet(g);

            //实时记录我方子弹坐标
            now_bullet_location[2*v-1]=bullets[v].BulletLocation_x;
            now_bullet_location[2*v]=bullets[v].BulletLocation_y;
            bullets[v+EnenyTank_Num].DrawBallet(g);
            now_bullet_location[2*(v+EnenyTank_Num)-1]=bullets[v+EnenyTank_Num].BulletLocation_x;
            now_bullet_location[2*(v+EnenyTank_Num)]=bullets[v+EnenyTank_Num].BulletLocation_y;

            enemyTanks[v].Boom(g);
        }
        for(int i=1;i<=MybulltNum;i++){
            mybullets[i].DrawBallet(g);
        }
        blocks.DrawBlocks(g);
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()) {

            case 37:{//先判断是否被击中和是否碰到墙
                mytank.DirStop=0;mytank.Block_stop();
                if(mytank.DirStop!=1&&!mytank.IsHit)
                {mytank.x=mytank.x-5;}
                mytank.dir=1;break;
            }
            case 39:{
                mytank.DirStop=0;mytank.Block_stop();
                if(mytank.DirStop!=2&&!mytank.IsHit)
                {mytank.x=mytank.x+5;}
                mytank.dir=2;break;
            }
            case 38:{
                mytank.DirStop=0;mytank.Block_stop();
                if(mytank.DirStop!=3&&!mytank.IsHit)
                {mytank.y=mytank.y-5;}
                mytank.dir=3;break;
            }
            case 40:{
                mytank.DirStop=0;mytank.Block_stop();
                if(mytank.DirStop!=4&&!mytank.IsHit)
                {mytank.y=mytank.y+5;}
                mytank.dir=4;break;
            }
            case 32 :{
                //如果此时子弹能发射
                if(mytank.CanShoot[mytank.BulletNum]){
                    mybullets[mytank.BulletNum].BulletLocation_dir=mytank.dir;
                    if(mytank.dir==1) {
                        mybullets[mytank.BulletNum].BulletLocation_x=mytank.x+mytank.w3-bullet.Bullet_High;
                        mybullets[mytank.BulletNum].BulletLocation_y=mytank.y+mytank.h1/2-bullet.Bullet_High/2;
                        mybullets[mytank.BulletNum].IsStart=true;
                        mybullets[mytank.BulletNum].IsBoom=false;
                        mytank.CanShoot[mytank.BulletNum]=false;
                    }
                    if(mytank.dir==2) {
                        mybullets[mytank.BulletNum].BulletLocation_x=mytank.x+mytank.w1/2;
                        mybullets[mytank.BulletNum].BulletLocation_y=mytank.y+mytank.h1/2-bullet.Bullet_High/2;
                        mybullets[mytank.BulletNum].IsStart=true;
                        mybullets[mytank.BulletNum].IsBoom=false;
                        mytank.CanShoot[mytank.BulletNum]=false;
                    }
                    if(mytank.dir==3) {
                        mybullets[mytank.BulletNum].BulletLocation_x=mytank.x+mytank.h1/2-bullet.Bullet_High/2;
                        mybullets[mytank.BulletNum].BulletLocation_y=mytank.y+mytank.w3-bullet.Bullet_High;
                        mybullets[mytank.BulletNum].IsStart=true;
                        mybullets[mytank.BulletNum].IsBoom=false;
                        mytank.CanShoot[mytank.BulletNum]=false;
                    }
                    if(mytank.dir==4) {
                        mybullets[mytank.BulletNum].BulletLocation_x=mytank.x+mytank.h1/2-bullet.Bullet_High/2;
                        mybullets[mytank.BulletNum].BulletLocation_y=mytank.y+mytank.w1/2;
                        mybullets[mytank.BulletNum].IsStart=true;
                        mybullets[mytank.BulletNum].IsBoom=false;
                        mytank.CanShoot[mytank.BulletNum]=false;
                    }
                }
            }
        }
        this.repaint();//监控键盘
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
                this.repaint();
                RunTime=RunTime+10;

                //每三秒发射一次子弹，发射子弹条件，该坦克未被击毁
                if(RunTime==3000) {
                    for (int v = 1; v <= EnenyTank_Num; v++) {
                        if(!enemyTanks[v].IsHit){
                            if (enemyTanks[v].dir == 1) {
                                bullet_location[2 * v - 1] = tank_location[2 * v - 1] + tank.w3 - bullet.Bullet_High;
                                bullet_location[2 * v] = tank_location[2 * v] + tank.h1 / 2 - bullet.Bullet_High / 2;
                            }
                            if (enemyTanks[v].dir == 2) {
                                bullet_location[2 * v - 1] = tank_location[2 * v - 1] + tank.w1 / 2;
                                bullet_location[2 * v] = tank_location[2 * v] + tank.h1 / 2 - bullet.Bullet_High / 2;
                            }
                            if (enemyTanks[v].dir == 3) {
                                bullet_location[2 * v - 1] = tank_location[2 * v - 1] + tank.h1 / 2 - bullet.Bullet_High / 2;
                                bullet_location[2 * v] = tank_location[2 * v] + tank.w3 - bullet.Bullet_High;
                            }
                            if (enemyTanks[v].dir == 4) {
                                bullet_location[2 * v - 1] = tank_location[2 * v - 1] + tank.h1 / 2 - bullet.Bullet_High / 2;
                                bullet_location[2 * v] = tank_location[2 * v] + tank.w1 / 2;
                            }
                            bullets[v].BulletLocation_x=bullet_location[2*v-1];
                            bullets[v].BulletLocation_y=bullet_location[2*v];
                            bullets[v].BulletLocation_dir=enemyTanks[v].dir;
                            bullets[v].IsStart=true;
                            bullets[v].IsBoom=false;
                        }
                    }
                }

                if(RunTime==6000) {
                    RunTime=0;
                    for (int v = 1; v <= EnenyTank_Num; v++) {
                        if(!enemyTanks[v].IsHit){
                            int j=v+EnenyTank_Num;
                            if (enemyTanks[v].dir == 1) {
                                bullet_location[2 * j - 1] = tank_location[2 * v - 1] + tank.w3 - bullet.Bullet_High;
                                bullet_location[2 * j] = tank_location[2 * v] + tank.h1 / 2 - bullet.Bullet_High / 2;
                            }
                            if (enemyTanks[v].dir == 2) {
                                bullet_location[2 * j - 1] = tank_location[2 * v - 1] + tank.w1 / 2;
                                bullet_location[2 * j] = tank_location[2 * v] + tank.h1 / 2 - bullet.Bullet_High / 2;
                            }
                            if (enemyTanks[v].dir == 3) {
                                bullet_location[2 * j - 1] = tank_location[2 * v - 1] + tank.h1 / 2 - bullet.Bullet_High / 2;
                                bullet_location[2 * j] = tank_location[2 * v] + tank.w3 - bullet.Bullet_High;
                            }
                            if (enemyTanks[v].dir == 4) {
                                bullet_location[2 * j - 1] = tank_location[2 * v - 1] + tank.h1 / 2 - bullet.Bullet_High / 2;
                                bullet_location[2 * j] = tank_location[2 * v] + tank.w1 / 2;
                            }
                            bullets[j].BulletLocation_x=bullet_location[2*j-1];
                            bullets[j].BulletLocation_y=bullet_location[2*j];
                            bullets[j].BulletLocation_dir=enemyTanks[v].dir;
                            bullets[j].IsStart=true;
                            bullets[j].IsBoom=false;
                        }
                    }
                }

                //实时记录我方子弹坐标
                for(int i=1;i<=MybulltNum;i++){
                    my_bullet_location[2*i-1]=mybullets[i].BulletLocation_x;
                    my_bullet_location[2*i]=mybullets[i].BulletLocation_y;
                }

                if(mytank.IsHit){
                    bullets[mytank.HitBulletNum].IsBoom=true;
                }

            } catch (Exception e) {
                System.out.println("wrong");
            }
        }
    }
}


import com.student.login.LoginThread;

public class ffrm_main{

    public static void main(String[] args) {
        LoginThread loginThread=new LoginThread();
        Thread t1=new Thread(loginThread);
        t1.start();
    }

}

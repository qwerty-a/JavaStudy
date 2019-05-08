import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mylistener implements ActionListener {
    @Override
    public  void actionPerformed(ActionEvent e){
        if(e.getActionCommand()=="登录"){
            System.out.println("你点击了登录按钮");
        }
        if(e.getActionCommand()=="取消"){
            System.out.println("你点击了取消按钮");
        }
    }
}

import javafx.scene.input.KeyCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BallPaint extends JPanel implements KeyListener {
    int x1=100,y1=100;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x1, y1, 50, 30);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()) {
            case 38:y1=y1-5;break;
            case 39:x1=x1+5;break;
            case 40:y1=y1+5;break;
            case 37:x1=x1-5;break;
        }
        this.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}


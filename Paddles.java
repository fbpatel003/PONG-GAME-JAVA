import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.*;

public class Paddles extends Rectangle {

    int id;
    int yVelocity;
    int speed = Constants.paddleSpeed;

    public Paddles(int x, int y, int width, int height, int id) {
        super(x,y,width, height);
        this.id = id;
    }

    public void keyPressed(KeyEvent e) {
        switch(id) {
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W) {
                    setYDirection(-speed);
                }
                if(e.getKeyCode()==KeyEvent.VK_S) {
                    setYDirection(speed);
                }
                break;

            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP) {
                    setYDirection(-speed);
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                    setYDirection(speed);
                }
                break;
        }
    }

    private void setYDirection(int i) {
        yVelocity = i;
    }

    public void keyReleased(KeyEvent e) {
        switch(id) {
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W) {
                    setYDirection(0);
                }
                if(e.getKeyCode()==KeyEvent.VK_S) {
                    setYDirection(0);
                }
                break;
            
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP) {
                    setYDirection(0);
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                    setYDirection(0);
                }
                break;
        }
    }

    public void draw(Graphics g) {
        if(id==1)
            g.setColor(Color.red);
        else
            g.setColor(Color.blue);

        g.fillRect(x, y, width, height);
    }

    public void move() {
        y = y + yVelocity;
    }


}

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.*;

public class Ball extends Rectangle{

    Random random;
    int xVelocity;
    int yVelocity;
    int BallSpeed = Constants.ballSpeedAtInitial;

    public Ball(int x, int y, int width, int height) {
        super(x,y, width, height);
        random = new Random();
        int randomXDirection = random.nextInt(2);
        if(randomXDirection==0) 
            randomXDirection--;
        
        setXDirection(randomXDirection*BallSpeed);

        int randomYDirection = random.nextInt(2);
        if(randomYDirection==0)
            randomYDirection--;

        setYDirection(randomYDirection*BallSpeed);

    }

    public void setXDirection(int i) {
        xVelocity = i;
    }

    public void setYDirection(int i) {
        yVelocity = i;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, width, height);
    }

    public void move() {
        x += xVelocity;
        y += yVelocity;
    }
}

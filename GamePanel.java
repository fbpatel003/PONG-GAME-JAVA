import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {

    static final Dimension screen = new Dimension(Constants.Window_Width, Constants.Window_Height);
    Thread T1;
    Image image;
    Graphics g;
    Random random;
    Paddles paddle1;
    Paddles paddle2;
    Ball ball;
    Score score;

    GamePanel() {
        newPaddles();
        newBall();
        score = new Score(Constants.Window_Width, Constants.Window_Height);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(screen);

        T1 = new Thread(this);
        T1.start();
    }

    public void newBall() {
        random = new Random();
        ball = new Ball((Constants.Window_Width/2), random.nextInt(Constants.Window_Height-Constants.ballDia), Constants.ballDia, Constants.ballDia);
    }

    public void newPaddles() {
        paddle1 = new Paddles(0, (Constants.Window_Height/2)-(Constants.paddleHeight/2), Constants.paddleWidth, Constants.paddleHeight, 1);
        paddle2 = new Paddles(Constants.Window_Width-Constants.paddleWidth, (Constants.Window_Height/2)-(Constants.paddleHeight/2), Constants.paddleWidth, Constants.paddleHeight, 2);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        g = image.getGraphics();
        draw(g);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
        Toolkit.getDefaultToolkit().sync();
    }

    public void move() {
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    public void collision(){

        if(ball.y <= 0) {
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y >= Constants.Window_Height-Constants.ballDia) {
            ball.setYDirection(-ball.yVelocity);
        }

        if(ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;

            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        if(ball.intersects(paddle2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;

            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        if(paddle1.y <= 0) 
            paddle1.y = 0;
        if(paddle1.y >= (Constants.Window_Height-Constants.paddleHeight)) {
            paddle1.y = Constants.Window_Height-Constants.paddleHeight;
        }
        if(paddle2.y >= 0)
            paddle2.y = 0;
        if(paddle2.y >= (Constants.Window_Height-Constants.paddleHeight)) {
            paddle2.y = Constants.Window_Height-Constants.paddleHeight;
        }

        if(ball.x <= 0) {
            score.player2++;
            newPaddles();
            newBall();
            System.out.println("player 2: "+ score.player2);
        }
        if(ball.x >= Constants.Window_Width-Constants.ballDia) {
            score.player1++;
            newPaddles();
            newBall();
            System.out.println("player 1: "+ score.player1);
        }
    }

    public void run() {
        long lasttime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now-lasttime) / ns;
            lasttime = now;
            if(delta >= 1) {
                move();
                collision();
                repaint();
                delta--;
            }
        }
    }

    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}

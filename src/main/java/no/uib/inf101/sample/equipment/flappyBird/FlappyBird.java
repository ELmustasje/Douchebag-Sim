package no.uib.inf101.sample.equipment.flappyBird;

import no.uib.inf101.sample.GamePanel;
import no.uib.inf101.sample.MouseHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class FlappyBird {
    Obsticle obs2;
    Obsticle obs1;
    Obsticle obs3;
    Obsticle obs4;
    ArrayList<Obsticle> queue;
    Bird bird;
    BufferedImage backgroundImg;
    BufferedImage STARTtxt;

    BufferedImage quitButton;
    BufferedImage quitButtonGlow;

    MouseHandler mouseHandler;
    boolean running;
    boolean done;

    int gameX;
    int gameY;
    int gameWidth;
    int gameHeigth;
    int score;
    double speed;

    public FlappyBird(MouseHandler mouseHandler){
        this.mouseHandler = mouseHandler;

        reset();
        try {
            backgroundImg = ImageIO.read(new File("res/flappyBird/flappyBirdBackground.png"));
            STARTtxt = ImageIO.read(new File("res/Other/Starttxt.png"));
            quitButton =ImageIO.read(new File("res/Other/quitbutton.png"));
            quitButtonGlow =ImageIO.read(new File("res/Other/quitbuttonglow.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getScore(){
        return score;
    }
    public boolean isRunning(){
        return running;
    }

    public void reset(){
        done = false;
        score =0;
        speed = 2;
        bird = new Bird();
        obs1 = new Obsticle(670,this);
        obs2 = new Obsticle(870,this);
        obs3 = new Obsticle(1070,this);
        obs4 = new Obsticle(1270,this);
        queue = new ArrayList<>();
        queue.add(obs1);
        queue.add(obs2);
        queue.add(obs3);
        queue.add(obs4);

    }

    private void run(){
        done = false;
        running = true;
    }
    public boolean done(){
        return done;
    }

    public void draw(Graphics2D g2){
        drawGame(g2,150,151,530,214);
        g2.drawImage(backgroundImg,0,0, GamePanel.WIDTH,GamePanel.HEIGTH,null);
    }


    private void drawGame(Graphics2D g2, int gameX, int gameY, int gameWidth, int gameHeigth){
        this.gameX = gameX;
        this.gameY = gameY;
        this.gameHeigth = gameHeigth;
        this.gameWidth = gameWidth;


        g2.setColor(Color.green);
        g2.fillRect(gameX,gameY,gameWidth,gameHeigth);
        bird.draw(g2);
        obs1.draw(g2);
        obs2.draw(g2);
        obs3.draw(g2);
        obs4.draw(g2);

        g2.setFont(new Font("_", Font.PLAIN, 30));
        g2.drawString(String.valueOf(score),gameX + 25, gameY + 35);

        if(!running){
            g2.setColor(Color.CYAN);
            g2.fillRect(gameX + gameWidth/3, gameY + gameHeigth/3, gameWidth/3, gameHeigth/3);
            g2.drawImage(STARTtxt,gameX + gameWidth/3, gameY + gameHeigth/3, gameWidth/3, gameHeigth/3,null);
            if(!hoveringExitButton()){
                g2.drawImage(quitButton,gameX+gameWidth - 42,gameY + 5,25,25,null);
            }else {
                g2.drawImage(quitButtonGlow,gameX+gameWidth - 42,gameY + 5,25,25,null);
            }
        }
    }
    private boolean mouseInsideGame(){
        return (
                mouseHandler.mouseX > gameX
                && mouseHandler.mouseX < gameX + gameWidth
                && mouseHandler.mouseY > gameY
                && mouseHandler.mouseY < gameY + gameHeigth
                );
    }

    private boolean hoveringStartButton(){
        return (
                mouseHandler.mouseX > gameX + gameWidth/3
                && mouseHandler.mouseX < gameX+((gameWidth/3)*2)
                && mouseHandler.mouseY > gameY + gameHeigth/3
                && mouseHandler.mouseY < gameY+((gameHeigth/3)*2)
                );
    }
    private boolean hoveringExitButton(){
        return (
                mouseHandler.mouseX >gameX+gameWidth - 42
                &&mouseHandler.mouseX <gameX+gameWidth - 42 + 25
                &&mouseHandler.mouseY >gameY + 5
                &&mouseHandler.mouseY < gameY + 30
                );
    }

    public void update(){
        // gameLoop Hentet fra: https://gamedev.stackexchange.com/questions/160329/java-game-loop-efficiency
        // Opphaver: Zerro97. Hentet: 24.04.24

        final int FPS = 60;
        final long OptimalTime = 1000000000 / FPS;

        if (running){
            long now;
            long updateTime;
            long wait;


            now = System.nanoTime();

            bird.update();
            obs1.update();
            obs2.update();
            obs3.update();
            obs4.update();

            if(bird.birdCrash(queue.get(0))||bird.outOfBounds(this)){
                running = false;
            }else {
                if(queue.get(0).x < bird.x){
                    score++;
                    if(score%3 == 0 && score > 0){
                        speed += 0.2;
                    }
                    Obsticle shuffle = queue.remove(0);
                    queue.add(shuffle);
                }
            }

            if(mouseInsideGame()){
                if(mouseHandler.mousePressed){
                    bird.jump();
                }
            }

            updateTime =System.nanoTime() - now;
            wait = (OptimalTime -updateTime) / 1000000;

            if(wait >= 0){
                try {
                    Thread.sleep(wait);
                } catch (Exception e) {

                }
            }
        }else {
            if(hoveringStartButton()&&mouseHandler.mousePressed){
                reset();
                run();
            }else if(hoveringExitButton()&&mouseHandler.mousePressed){
                done = true;
                mouseHandler.used();
            }

        }

    }
}

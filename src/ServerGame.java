import processing.core.PApplet;
import java.lang.Math;

//Handles all game data and calculations as well as, server user game window and interaction
public class ServerGame extends PApplet{

    //Game constants
    final static int width = 800;
    final static int height = 600;
    final static float characterSize = 50;
    final static float p1x = 20;
    final static float p2x = width-20-((int)characterSize/5);
    final float ballSize = 15;

    //Game vars subject to change
    static float p1y = 225;
    static float p2y;
    static float ballx  = (width/2);
    static float bally = (height/2);
    static float ballxVelocity  = -5;
    static float ballyVelocity = 0;
    static int p1Score = 0;
    static int p2Score = 0;

    boolean[] keys = new boolean[255];

    public static void run(){
        String[] processingArgs = {"InAndOutServerGame"};
        ServerGame game = new ServerGame();
        PApplet.runSketch(processingArgs, game);
    }

    public void settings() {
        size(width, height);
    }

    public void draw() {
        //Server handles all collisions and ball physics for both server and client
        background(0);
        move();
        fill(255);
        rect(p1x,p1y,characterSize/5,characterSize);
        rect(p2x,p2y,characterSize/5,characterSize);
        updateBall();
        rect(ballx, bally, ballSize, ballSize);
        textSize(30);
        String score = (p1Score + "|" + p2Score);
        text(score, width/2-25, 50);
    }

    public void keyPressed() { keys[keyCode] = true; }

    public void keyReleased() {
        keys[keyCode] = false;
    }

    //check if a move is valid
    public boolean checkMove(float y, float moveY){
        if(y + moveY > height-characterSize) {return false; }
        return !(y + moveY < 0);
    }

    public void updateBall(){
        //check ball collision with ceiling and floor
        if(bally <= 0 || bally >= height-ballSize){
            ballyVelocity *= -1;
        }
        //check if ball passes player 1
        if(ballx <= 0){
            p2Score++;
            ballx = (width/2);
            ballyVelocity=5;
        }
        //check if ball passes player 2
        if(ballx >= width){
            p1Score++;
            ballx = (width/2);
            ballyVelocity=-5;
        }
        //check collision with player 1
        //if(ballx == (p1x+characterSize/5+ballSize) && p1y<=bally && bally <= (p1y+characterSize))
        if(ballx == p1x && p1y<=bally && bally <= (p1y+characterSize)) {
            ballxVelocity *= -1;
            if(bally<(p1y+(characterSize/2))){
                ballyVelocity = (int) -(5*Math.cos((p1y+characterSize/2-bally)));
            }
            else{
                ballyVelocity = (int) -(5*Math.cos((p1y+characterSize/2+bally)));
            }
        }
        //check collision with player 2
        if(ballx + ballSize == p2x && p2y<=bally && bally <= (p2y+characterSize)) {
            ballxVelocity *= -1;
            if(bally<(p2y+(characterSize/2))){
                ballyVelocity = (float) -(5*Math.cos((p2y+characterSize/2-bally)));
            }
            else{
                ballyVelocity = (float) -(5*Math.cos((p2y+characterSize/2+bally)));
            }
        }
        //moves the ball according to ball velocity
        ballx += ballxVelocity;
        bally += ballyVelocity;
    }

    public void move() {
        //watch for up arrow press
        if(keys[87]) {
            if(checkMove(p1y,  -5)) {
                p1y -= 5;
            }
        }
        //watch for down arrow press
        if(keys[83]) {
            if(checkMove(p1y, 5)) {
                p1y += 5;
            }
        }
    }
}

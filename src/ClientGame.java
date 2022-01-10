import processing.core.PApplet;

//Handles all client user game window and interaction
public class ClientGame extends PApplet {

    //Game constants
    final static int width = 800;
    final static int height = 600;
    final static float characterSize = 50;
    final static float p1x = 20;
    final static float p2x = width-20-((int)characterSize/5);

    //Game vars subject to change
    static float p1y;
    static float p2y = 225;
    static float ballx  = (width/2);
    static float bally = (height/2);
    static boolean end = true;
    int p1Score = 0;
    int p2Score = 0;
    final float ballSize = 15;

    boolean[] keys = new boolean[255];

    public static void run(){
        String[] processingArgs = {"InAndOutClientGame"};
        ClientGame game = new ClientGame();
        PApplet.runSketch(processingArgs, game);
    }

    public void settings() {
        size(width, height);
    }

    public void draw() {
        background(0);
        move();
        //temp P2 AI, remove later
        p2y = bally;
        fill(255);
        rect(p1x,p1y,characterSize/5,characterSize);
        rect(p2x, p2y, characterSize/5, characterSize);
        String score = (p1Score + "|" + p2Score);
        text(score, width/2-25, 50);
        rect(ballx, bally, ballSize, ballSize);
    }

    public void keyPressed() { keys[keyCode] = true; }

    public void keyReleased() {
        keys[keyCode] = false;
    }

    public boolean checkMove(float y, float moveY){
        if(y + moveY > height-characterSize) {return false; }
        return !(y + moveY < 0);
    }

    public void move() {
        if(keys[87]) {
            if(checkMove(p2y,  -5)) {
                p2y -= 5;
            }
        }
        if(keys[83]) {
            if(checkMove(p2y,  5)) {
                p2y += 5;
            }
        }
        if(keys['E']){
            end = false;
        }
    }
}

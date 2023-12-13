import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import processing.core.PApplet;

public class Game extends PApplet {
    Player humanPlayer; Player AI;
    public static final int SLOW = 1;
    public static final int MEDIUM = 2;
    public static final int FAST = 3;
    public static final int SUPER_FAST = 4;
    public static int tick;


    public static void gameOver(Player winner) {
        System.out.println("Game Over! " + winner.getName() + " won!");
        System.exit(0);
    }

    public void settings(){
        size(600,800);
    }
    public void setup(){
        getSurface().setLocation(0,0);
        frameRate(60);
        background(0,100,0);
        humanPlayer = new Player("Human Player",this, color(255,0,0));
        AI = new Player("AI",this, color(0,0,255));
        Tower.setupMainTowers(humanPlayer, AI, this);




    }


    public void mouseReleased() {

        if(mouseY > height/2) humanPlayer.spawn(mouseX, mouseY);
    }
    public void draw(){
            background(50, 150, 50);
            humanPlayer.update(AI);
            AI.update(humanPlayer);

        if(!humanPlayer.getDeck().lastButtonPressed.equals("")) {
            fill(0,90);
            stroke(0,90);
            rect(0, 0, width, height/2);
        }

            tick++;

    }




    public static void main(String[] args) {
        PApplet.main("Game");

    }


}

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import processing.core.PApplet;

public class Game extends PApplet {
    Player humanPlayer; Player AI;
    public static final int SLOW = 1;
    public static final int MEDIUM = 2;
    public static final int FAST = 3;
    public static final int SUPER_FAST = 4;
    public static int tick;

    private Player winner;

    public boolean paused = false;


    public static void gameOver(Player winner) {
        System.out.println("Game Over! " + winner.getName() + " won!");
        System.exit(0);
    }

    public void settings() {
        size(600, 800);
    }

    public void setup() {
        winner = null;
        paused = false;
        Cannon = this.loadImage("images/Cannon.png");
        EliteBarbarians = this.loadImage("images/EliteBarbarians.png");
        HogRider = this.loadImage("images/HogRider.png");
        IceGolem = this.loadImage("images/IceGolem.png");
        KingTower = this.loadImage("images/KingTower.png");
        Knight = this.loadImage("images/Knight.png");
        Musketeer = this.loadImage("images/Musketeer.png");
        Skeletons = this.loadImage("images/Skeletons.png");
        getSurface().setLocation(0, 0);
        frameRate(60);
        humanPlayer = new Player("Human Player", this, color(137, 207, 240));
        AI = new Player("AI", this, color(226, 134, 129));
        Tower.setupMainTowers(humanPlayer, AI, this);




    }


    public void mouseReleased() {

        if(mouseY > height/2) humanPlayer.spawn(mouseX, mouseY);
    }

    public void draw() {
        if (!paused) {
            background(50, 150, 50);
            humanPlayer.update(AI);
            AI.update(humanPlayer);


            //gray out of unspawnable area
            if (!humanPlayer.getDeck().lastButtonPressed.equals("")) {
                fill(0, 90);
                stroke(0, 90);
                rect(0, 0, width, height / 2);
            }
            tick++;
        } else {
            fill(0);
            rect(0, 0, 100000, 100000);
            textAlign(CENTER, CENTER);
            textSize(50);
            fill(255);
            stroke(255);
            text(winner.getName() + " won!", width / 2, height / 2);
        }
    }




    public static void main(String[] args) {
        PApplet.main("Game");

    }


}

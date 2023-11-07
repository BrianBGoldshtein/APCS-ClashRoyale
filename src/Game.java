import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import processing.core.PApplet;

public class Game extends PApplet {
    Player player1; Player player2;
    public static final int SLOW = 1;
    public static final int MEDIUM = 2;
    public static final int FAST = 3;
    public static final int SUPER_FAST = 4;
    public static int tick;

    public static void gameOver(Player winner) {
        System.out.println("Game Over! Player " + winner + " won!");
        System.exit(0);
    }

    public void settings(){
        size(600,800);
    }
    public void setup(){
        frameRate(60);
        background(0,100,0);
        player1 = new Player(this, color(255,0,0));
        player2 = new Player(this, color(0,0,255));
        Tower.setupMainTowers(player1, player2, this);
        player1.addTroop(new HogRider(player1,width/2+50, height/2-56, this));
        player1.addTroop(new HogRider(player1,width/2+50, height/2-57, this));
        player1.addTroop(new HogRider(player1,width/2+50, height/2-54, this));
        player1.addTroop(new HogRider(player1,width/2+50, height/2-53, this));


        player2.addTroop(new Skeleton(player2,width/2-180, height/2-240, this));



    }
    public void draw(){
        background(50,150,50);
        player1.update(player2);
        player2.update(player1);
        tick++;
    }


    public static void main(String[] args) {
        PApplet.main("Game");

    }


}

import processing.core.PApplet;

public class Game extends PApplet {
    Player player1; Player player2;
    public static final int SLOW = 1;
    public static final int MEDIUM = 2;
    public static final int FAST = 3;
    public static final int SUPER_FAST = 4;
    public void settings(){
        size(600,800);
    }
    public void setup(){
        frameRate(20);
        background(0,255,0);
        player1 = new Player(true, this);
        player2 = new Player(false, this);
        Tower.setupMainTowers(player1, player2, this);

    }
    public void draw(){
        background(0,255,0);
        player1.update(player2);
        player2.update(player1);
    }


    public static void main(String[] args) {
        PApplet.main("Game");

    }


}

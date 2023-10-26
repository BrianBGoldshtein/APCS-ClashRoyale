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
        frameRate(60);
        background(0,100,0);
        player1 = new Player(true, this);
        player2 = new Player(false, this);
        Tower.setupMainTowers(player1, player2, this);
        player1.addTroop(new HogRider(true,width/2+50, height/2-100, this));
        player2.addTroop(new Skeleton(false,width/2-50, height/2-80, this));
        player2.addTroop(new Skeleton(false,width/2-200, height/2-200, this));
        player2.addTroop(new Skeleton(false,width/2-60, height/2-80, this));
        player2.addTroop(new Skeleton(false,width/2-180, height/2-200, this));
        player2.addTroop(new Skeleton(false,width/2-190, height/2-200, this));
        player2.addTroop(new Skeleton(false,width/2-60, height/2-110, this));
        player2.addTroop(new Skeleton(false,width/2-180, height/2-240, this));
        //hello there


    }
    public void draw(){
        background(50,150,50);
        player1.update(player2);
        player2.update(player1);
    }


    public static void main(String[] args) {
        PApplet.main("Game");

    }


}

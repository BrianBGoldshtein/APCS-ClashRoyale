import java.util.ArrayList;

public class Troop {
    // hog rider
    // log
    // ice golem
    // musketeer
    // fireball
    // cannon
    // ice spirit
    // skeletons
    protected int elixirPrice, health, damage, shotRange, cooldown;
    protected float x, y, speed;
    protected boolean owner, tank, alive;
    protected Game game;
    public Troop(boolean owner, int elixirPrice, int health, int damage, int shotRange, int cooldown, float speed, boolean tank, Game game, float x, float y) {
        this.owner = owner;
        this.elixirPrice = elixirPrice;
        this.health = health;
        this.damage = damage;
        this.shotRange = shotRange;
        this.cooldown = cooldown;
        this.speed = speed;
        this.tank = tank;
        this.alive = true;
        this.game = game;
        this. x = x;
        this.y = y;

    }

    public void attack(Game game) {
    }

    public void move(ArrayList<Troop> troopList) {


        //find the angle
        double angle;
        Troop nearestEntity = getNearestEntity(troopList);
        float smallestEntityDistance = Game.dist(nearestEntity.x, nearestEntity.y, this.x, this.y);


        angle = Math.acos(((nearestEntity.x-this.x)/(smallestEntityDistance)));
        if(this.y > nearestEntity.y) angle *=-1;

        // move speed units along that axis per second

        x+= Math.cos(angle)*this.speed;
        y+= Math.sin(angle)*this.speed;
    }
    public Troop getNearestEntity(ArrayList<Troop> troopList) {
        //GET BACK HERE AND REVISE PLS PLS DANKE

        //find nearest tower
        float smallestTroopDistance = Float.MAX_VALUE;
        Troop closestTroop = null;

        for(Troop troop: troopList) {
            if(!(troop instanceof Tower)) {if(tank) continue;}
            float dist = Game.dist(this.x, this.y, troop.x, troop.y);
            if (dist < smallestTroopDistance && troop.owner != this.owner && !troop.equals(this)) {
                smallestTroopDistance = dist;
                closestTroop = troop;
            }
        }
        return closestTroop;
    }
    public void draw() {
        game.fill(255);
        game.ellipse(this.x,this.y,this.health/50, this.health/50);
        game.textAlign(game.CENTER, game.CENTER);
        game.textSize(14);
        game.fill(0);
        game.text(this.health, this.x, this.y);
    }

    public void act(ArrayList<Troop> troopList) {
        move(troopList);
        draw();
    }


    public boolean collisionDetection(float x1, float y1, float r1, float x2, float y2, float r2) {
        return Game.dist(x1,y1,x2,y2) <= r1 + r2;
    }


}

import java.util.ArrayList;

public class Troop {
    protected int elixirPrice, health, damage, shotRange, cooldown;
    protected boolean attackedThisTick;
    protected float x, y, speed;
    protected boolean tank, alive;
    protected Player owner;
    protected Game game;

    public static final String[] CARDS = {"HogRider", "Skeletons", "Cannon", "Musketeer", "EliteBarbarians", "Knight", "IceGolem"};

    public Troop(Player owner, int elixirPrice, int health, int damage, int shotRange, int cooldown, float speed, boolean tank, Game game, float x, float y) {
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
        attackedThisTick = false;

    }

    public int getElixirPrice() {
        return elixirPrice;
    }
    public void attack(Troop nearest) {
        if (Game.tick % cooldown == 0 && Game.dist(x,y,nearest.x,nearest.y)-getSize()/2.0 - nearest.getSize()/2.0 <= shotRange && !attackedThisTick) {
            nearest.health -= damage;
            attackedThisTick = true;
        }
    }

    public void act(ArrayList<Troop> ourTroops, ArrayList<Troop> otherTroopList) {
        Troop nearestEntity = getNearestEntity(otherTroopList);
        move(nearestEntity);
        collisionFix(ourTroops, otherTroopList);
        attack(nearestEntity);
        draw();
        attackedThisTick=false;
    }

    public void move(Troop nearestEntity) {
        if(nearestEntity == null) return;
        float smallestEntityDistance = Game.dist(nearestEntity.x, nearestEntity.y, this.x, this.y);
        double angle = Math.acos(((nearestEntity.x-this.x)/(smallestEntityDistance)));
        if(this.y > nearestEntity.y) angle *=-1;
        // move speed units along that axis per second

        if (Math.abs(smallestEntityDistance - nearestEntity.getSize()/2.0 - this.getSize()/2.0) > this.speed) {
            x += Math.cos(angle) * this.speed;
            y += Math.sin(angle) * this.speed;
        } else if (Math.abs(smallestEntityDistance) > nearestEntity.getSize()/2 + this.getSize()/2) {
            x += Math.cos(angle) * Math.abs(smallestEntityDistance-nearestEntity.getSize()/2.0 - this.getSize()/2.0);
            y += Math.sin(angle) * Math.abs(smallestEntityDistance-nearestEntity.getSize()/2.0 - this.getSize()/2.0);
        }
    }
    public Troop getNearestEntity(ArrayList<Troop> troopList) {
        //GET BACK HERE AND REVISE PLS PLS DANKE

        //find the nearest tower
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

    public float getSize() {
        return this.health/100 + 30;
    }
    public void draw() {
            game.fill(owner.getColor());
            game.ellipse(this.x, this.y, this.getSize(), this.getSize());
            game.textAlign(game.CENTER, game.CENTER);
            game.textSize(14);
            game.fill(255);
            game.text(this.health, this.x, this.y);

    }

    public void collisionFix(ArrayList<Troop> troops1, ArrayList<Troop> troops2) {
        ArrayList<Troop> troops = new ArrayList<>();
        troops.addAll(troops1);
        troops.addAll(troops2);
        loop: for(Troop otherTroop: troops) {
            if (otherTroop == this) continue;
            if (isCollided(this, otherTroop)) {
                double distance = (Game.dist(x, y, otherTroop.x, otherTroop.y));
                double angle = Math.acos(((otherTroop.x - this.x) / distance));
                if (this.y > otherTroop.y) angle *= -1;
                if(this instanceof Tower) continue loop;
                x -= Math.cos(angle) * Math.abs(distance - otherTroop.getSize() / 2.0 - this.getSize() / 2.0);
                y -= Math.sin(angle) * Math.abs(distance - otherTroop.getSize() / 2.0 - this.getSize() / 2.0);
            }
        }
    }


    public String getName() {
        if(this instanceof Tower) return "Tower";
        if(this instanceof HogRider) return "HogRider";
        if(this instanceof Skeleton) return "Skeletons";
        if(this instanceof Cannon) return "Cannon";
        if(this instanceof Musketeer) return "Musketeer";
        if(this instanceof Knight) return "Knight";
        if(this instanceof EliteBarbarians) return "ELiteBarbarians";
        if(this instanceof IceGolem) return "IceGolem";
        return null;
    }


    public boolean isCollided(Troop t1, Troop t2) {
        return Game.dist(t1.x,t1.y,t2.x,t2.y) <= t1.getSize()/2.0 + t2.getSize()/2.0;
    }


}

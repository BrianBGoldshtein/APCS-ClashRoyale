import java.util.ArrayList;

public class Projectile {
    public static ArrayList<Projectile> projectiles = new ArrayList<>();
    Troop owner, target; float x, y; Game game;

    private static final int speed = 3;
    private static final int size = 10;

    public Projectile(Troop owner, Troop target, Game game) {
        this.owner = owner;
        this.x = owner.x; this.y = owner.y;
        this.target = target;
        this.game = game;

        projectiles.add(this);
    }

    public static void update() {
        if(projectiles.size() == 0) return;
        for(Projectile p: projectiles) {
            float distance = Game.dist(p.target.x, p.target.y, p.x, p.y);
            double angle = Math.acos(((p.target.x-p.x)/(distance)));
            if(p.y > p.target.y) angle *=-1;
            // move speed units along that axis per second

                p.x += Math.cos(angle) * p.speed;
                p.y += Math.sin(angle) * p.speed;

                if(distance <= p.target.getSize()/2.0) {
                    p.target.health -= p.owner.damage;
                    p.owner.attackedThisTick = true;

                    p.dispose();
                    return;
                }

                p.draw();



        }
    }

    private void dispose() {
        projectiles.remove(this);
    }

    public void draw() {
        game.fill(owner.owner.getColor());
        game.ellipse(this.x, this.y, this.size, this.size);
        game.textAlign(game.CENTER, game.CENTER);

    }
}

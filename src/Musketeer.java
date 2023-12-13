public class Musketeer extends Troop{
    public static final int PRICE = 4;
    private static final int STARTING_HEALTH = 792;
    private static final int DAMAGE = 239;
    private static final int SHOT_RANGE = 100;
    private static final int COOLDOWN = 60;
    private static final int SPEED = Game.MEDIUM;
    private static final boolean TANK = false;

    public Musketeer(Player owner, float x, float y, Game game) {
        super(owner, PRICE, STARTING_HEALTH, DAMAGE, SHOT_RANGE, COOLDOWN, SPEED, TANK, game, x, y);
    }

    @Override
    public void attack(Troop nearest) {
        if (Game.tick % cooldown == 0 && Game.dist(x,y,nearest.x,nearest.y)-getSize()/2.0 - nearest.getSize()/2.0 <= shotRange && !attackedThisTick) {
            new Projectile(this, nearest, game);
        }

        if(Game.dist(x,y,nearest.x,nearest.y)-getSize()/2.0 - nearest.getSize()/2.0 <= shotRange) speed = 0;
        else speed = SPEED;
    }



}

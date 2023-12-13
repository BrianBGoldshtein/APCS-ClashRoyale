public class EliteBarbarians extends Troop{
    public static final int PRICE = 6;
    private static final int STARTING_HEALTH = 1472;
    private static final int DAMAGE = 421;
    private static final int SHOT_RANGE = 1;
    private static final int COOLDOWN = 84;
    private static final int SPEED = Game.FAST;
    private static final boolean TANK = false;

    public EliteBarbarians(Player owner, float x, float y, Game game) {
        super(owner, PRICE, STARTING_HEALTH, DAMAGE, SHOT_RANGE, COOLDOWN, SPEED, TANK, game, x, y);
    }


}

public class Knight extends Troop{
    public static final int PRICE = 3;
    private static final int STARTING_HEALTH = 1766;
    private static final int DAMAGE = 202;
    private static final int SHOT_RANGE = 1;
    private static final int COOLDOWN = 72;
    private static final int SPEED = Game.MEDIUM;
    private static final boolean TANK = false;

    public Knight(Player owner, float x, float y, Game game) {
        super(owner, PRICE, STARTING_HEALTH, DAMAGE, SHOT_RANGE, COOLDOWN, SPEED, TANK, game, x, y);
    }


}

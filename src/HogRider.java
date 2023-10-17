public class HogRider extends Troop{
    private static final int PRICE = 4;
    private static final int STARTING_HEALTH = 1696;
    private static final int DAMAGE = 318;
    private static final int SHOT_RANGE = 1;
    private static final int COOLDOWN = 96;
    private static final int SPEED = Game.SUPER_FAST;

    public HogRider(boolean owner, float x, float y, Game game) {
        super(owner, PRICE, STARTING_HEALTH, DAMAGE, SHOT_RANGE, COOLDOWN, SPEED, false, game, x, y);
    }
}

public class IceGolem extends Troop{
    public static final int PRICE = 2;
    private static final int STARTING_HEALTH = 1197;
    private static final int DAMAGE = 84;
    private static final int SHOT_RANGE = 1;
    private static final int COOLDOWN = 150;
    private static final int SPEED = Game.SLOW;
    private static final boolean TANK = true;

    public IceGolem(Player owner, float x, float y, Game game) {
        super(owner, PRICE, STARTING_HEALTH, DAMAGE, SHOT_RANGE, COOLDOWN, SPEED, TANK, game, x, y);
    }


}

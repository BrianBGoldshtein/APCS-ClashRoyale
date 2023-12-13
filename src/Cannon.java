public class Cannon extends Tower {
    public static final int PRICE = 3;
    private static final int STARTING_HEALTH = 1091;
    private static final int DAMAGE = 281;
    private static final int SHOT_RANGE = 140;
    private static final int COOLDOWN = 54;

    public Cannon(Player owner, float x, float y, Game game) {
        super(owner, PRICE, STARTING_HEALTH, DAMAGE, SHOT_RANGE, COOLDOWN, game, x, y);
    }


}

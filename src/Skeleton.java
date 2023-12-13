import java.util.ArrayList;

public class Skeleton extends Troop{
    public static final int PRICE = 1;
    private static final int STARTING_HEALTH = 81;
    private static final int DAMAGE = 81;
    private static final int SHOT_RANGE = 1;
    private static final int COOLDOWN = 60;
    private static final int SPEED = Game.FAST;

    public Skeleton(Player owner, float x, float y, Game game) {
        super(owner, PRICE, STARTING_HEALTH, DAMAGE, SHOT_RANGE, COOLDOWN, SPEED, false, game, x, y);
    }

}

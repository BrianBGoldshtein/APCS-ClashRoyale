import java.util.ArrayList;

public class Player {
    public static final int MAIN_TOWER_DAMAGE = 110;
    public static final int MAIN_TOWER_SHOTRANGE = 70;
    public static final int MAIN_TOWER_COOLDOWN = 60;

    private boolean owner;
    private Game game;
    private ArrayList<Troop> troopList;
    public static final int STARTING_HEALTH = 5000;

    public Player(boolean owner, Game game) {
        troopList = new ArrayList<>();
        this.game = game; this.owner = owner;
    }

    public ArrayList<Troop> getTroopList() {return this.troopList;}

    public void addTroop(Troop t) {
        troopList.add(t);
    }

    public void update(Player other) {
        for (Troop t : troopList) {
            t.act(other.troopList);
        }
    }

}

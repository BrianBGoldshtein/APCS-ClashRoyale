import java.util.ArrayList;

public class Player {
    public static final int MAIN_TOWER_DAMAGE = 110;
    public static final int MAIN_TOWER_SHOTRANGE = 70;
    public static final int MAIN_TOWER_COOLDOWN = 60;

    private Game game;
    private ArrayList<Troop> troopList;
    public static final int STARTING_HEALTH = 5000;
    private int color;

    public Player(Game game, int color) {
        troopList = new ArrayList<>();
        this.game = game;
        this.color = color;
    }

    public ArrayList<Troop> getTroopList() {return this.troopList;}

    public void addTroop(Troop t) {
        troopList.add(t);
    }

    public void update(Player other) {
        ArrayList<Troop> deadTroops = new ArrayList<>();
        if(other.troopList.isEmpty()) Game.gameOver(this);

        for (Troop t : troopList) {
            t.act(troopList, other.troopList);
            //check for dead ones
            if(t.health<=0) deadTroops.add(t);
        }
        //remove all dead ones
        for(Troop t: deadTroops) {
            troopList.remove(t);
        }

    }

    public int getColor() {
        return this.color;
    }
}

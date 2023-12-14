import java.util.ArrayList;

public class Player {
    public static final int MAIN_TOWER_DAMAGE = 110;
    public static final int MAIN_TOWER_SHOTRANGE = 150;
    public static final int MAIN_TOWER_COOLDOWN = 60;

    public Game game;
    private ArrayList<Troop> troopList;
    public static final int STARTING_HEALTH = 5000;
    private int color;

    private Deck deck;

    private String name;

    public Troop mainTower;

    public Player(String name,Game game, int color) {
        this.name = name;
        troopList = new ArrayList<>();
        this.game = game;
        this.color = color;
        deck = new Deck(this);
    }

    public ArrayList<Troop> getTroopList() {
        return troopList;
    }

    public String getName() {
        return name;
    }


    public void spawnHogRider(float x, float y) {
        troopList.add(new HogRider(this, x, y, game));
    }
    public void spawnCannon(float x, float y) {
        troopList.add(new Cannon(this, x, y, game));
    }
    public void spawnMusketeer(float x, float y) {
        troopList.add(new Musketeer(this, x, y, game));
    }
    public void spawnSkeletons(float x, float y) {
        troopList.add(new Skeleton(this, x-1, y-1, game));
        troopList.add(new Skeleton(this, x+1, y-1, game));
        troopList.add(new Skeleton(this, x+1, y+1, game));
        troopList.add(new Skeleton(this, x-1, y+1, game));
    }
    public void spawnKnight(float x, float y) { troopList.add(new Knight(this, x, y, game)); }
    public void spawnEliteBarbarians(float x, float y) {
        troopList.add(new EliteBarbarians(this, x, y, game));
        troopList.add(new EliteBarbarians(this, x, y, game));
    }
    public void spawnIceGolem(float x, float y) { troopList.add(new IceGolem(this, x, y, game)); }


    public void addTroop(Troop t) {
        troopList.add(t);
    }

    public void update(Player other) {
        ArrayList<Troop> deadTroops = new ArrayList<>();

        deck.update();
        Projectile.update();

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


    public void spawn(int x, int y) {
        if(deck.lastButtonPressed == "") return;

        if(deck.lastButtonPressed.equals("HogRider")) {
            spawnHogRider(x,y);
        }
        else if(deck.lastButtonPressed.equals("Skeletons")) {
            spawnSkeletons(x,y);
        }
        else if(deck.lastButtonPressed.equals("Cannon")) {
            spawnCannon(x,y);
        }
        else if(deck.lastButtonPressed.equals("Musketeer")) {
            spawnMusketeer(x,y);
        }
        else if(deck.lastButtonPressed.equals("IceGolem")) {
            spawnIceGolem(x,y);
        }
        else if(deck.lastButtonPressed.equals("EliteBarbarians")) {
            spawnEliteBarbarians(x,y);
        }
        else if(deck.lastButtonPressed.equals("Knight")) {
            spawnKnight(x,y);
        }
        deck.lastButtonPressed = "";

    }

    public Deck getDeck() {
        return deck;
    }
}

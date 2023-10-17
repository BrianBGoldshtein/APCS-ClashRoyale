public class Tower extends Troop{

    public Tower(boolean owner, int elixirPrice, int health, int damage, int shotRange, int cooldown, Game game, float x, float y) {
        super(owner, elixirPrice, health, damage, shotRange, cooldown, 0, false, game, x, y);
    }

    public int getRad() {
        return health/50;
    }

    public void draw() {
        game.fill(255);
        game.ellipse(this.x,this.y,this.getRad()*2, this.getRad()*2);
        game.textAlign(game.CENTER, game.CENTER);
        game.textSize(14);
        game.fill(0);
        game.text(this.health, this.x, this.y);
    }

    public static void setupMainTowers(Player p1, Player p2, Game game) {
        p1.addTroop(new Tower(true, Integer.MAX_VALUE, Player.STARTING_HEALTH, Player.MAIN_TOWER_DAMAGE, Player.MAIN_TOWER_SHOTRANGE, Player.MAIN_TOWER_COOLDOWN,game, game.width/2, game.height-10));
        p2.addTroop(new Tower(false, Integer.MAX_VALUE, Player.STARTING_HEALTH, Player.MAIN_TOWER_DAMAGE, Player.MAIN_TOWER_SHOTRANGE, Player.MAIN_TOWER_COOLDOWN,game, game.width/2, 10));
    }
}

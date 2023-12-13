public class Tower extends Troop{

    public Tower(Player owner, int elixirPrice, int health, int damage, int shotRange, int cooldown, Game game, float x, float y) {
        super(owner, elixirPrice, health, damage, shotRange, cooldown, 0, false, game, x, y);
    }

    @Override
    public float getSize() {
        return this.health/50 + 30;
    }


    @Override
    public void attack(Troop nearest) {
        if (Game.tick % cooldown == 0 && Game.dist(x,y,nearest.x,nearest.y)-getSize()/2.0 - nearest.getSize()/2.0 <= shotRange && !attackedThisTick) {
            new Projectile(this, nearest, game);
        }
    }

    public static void setupMainTowers(Player p1, Player p2, Game game) {
        p1.addTroop(new Tower(p1, Integer.MAX_VALUE, Player.STARTING_HEALTH, Player.MAIN_TOWER_DAMAGE, Player.MAIN_TOWER_SHOTRANGE, Player.MAIN_TOWER_COOLDOWN,game, game.width/2, game.height-10));
        p2.addTroop(new Tower(p2, Integer.MAX_VALUE, Player.STARTING_HEALTH, Player.MAIN_TOWER_DAMAGE, Player.MAIN_TOWER_SHOTRANGE, Player.MAIN_TOWER_COOLDOWN,game, game.width/2, 10));
    }
}

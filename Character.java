public abstract class Character {
    protected String name;
    protected int hp;

    public Character(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    public void takeDamage(int dmg) {
        hp -= dmg;
    }

    public int getHp() {
        return hp;
    }
}
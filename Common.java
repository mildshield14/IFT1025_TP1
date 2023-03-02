package ca.udem.ift1025.tp1.corrige.guildcommands;

public class Common extends Hero{
    private int category;

    public Common(String name, double cashCost, int armorCost, double lifePoints) {
        super(name, cashCost, armorCost, lifePoints);
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getCategory() {
        return category;
    }
}

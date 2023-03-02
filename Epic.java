package ca.udem.ift1025.tp1.corrige.guildcommands;

public class Epic extends Hero{
    private int category;

    public Epic(String name, double cashCost, int armorCost, double lifePoints) {
        super(name, cashCost, armorCost, lifePoints);
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getCategory() {
        return category;
    }
}

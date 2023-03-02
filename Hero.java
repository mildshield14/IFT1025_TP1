package ca.udem.ift1025.tp1.corrige.guildcommands;

public class Hero extends Guild{

    private String name;
    private double cashCost;
    private int armorCost;

    private double lifePoints;

    int category;

    public int getCategory() {
        return category;
    }

    public Hero(String name, double cashCost, int armorCost, double lifePoints, int category ){
        super();
        this.name=name;
        this.armorCost=armorCost;
        this.cashCost=cashCost;
        this.lifePoints=lifePoints;
        this.category=category;
    }
    public String getName() {
        return name;
    }

    public double getCashCost() {
        return cashCost;
    }

    public double getLifePoints() {
        return lifePoints;
    }


    public int getArmorCost() {
        return armorCost;
    }

    public void setLifePoints(double lifePoints) {
        this.lifePoints = lifePoints;

    }

    public void setCategory(int category) {
        this.category = category;
    }

}

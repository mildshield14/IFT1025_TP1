package ca.udem.ift1025.tp1.corrige.guildcommands;

public class Hero{

    private String name;
    private double cashCost;
    private int armorCost;
    private double lifePoints;
    private double maxLifePoints;
    int category;

    public int getCategory() {
        return category;
    }

    public Hero(String name, double cashCost, int armorCost, double lifePoints, int category, double maxLifePoints) {

        this.name = name;
        this.armorCost = armorCost;
        this.cashCost = cashCost;
        this.lifePoints = lifePoints;
        this.category = category;
        this.maxLifePoints = maxLifePoints;
    }

    //getters
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
    public double getMaxLifePoints() {
        return maxLifePoints;
    }

    //setters
    public void setLifePoints(double lifePoints) {
        this.lifePoints = lifePoints;
    }
    public void setMaxLifePoints(double maxLifePoints) {
        this.maxLifePoints = maxLifePoints;
    }
    public void setCategory(int category) {
        this.category = category;
    }
}


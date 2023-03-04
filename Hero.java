package ca.udem.ift1025.tp1.corrige.guildcommands;

public class Hero{

    private String name;
    private double cashPrice;
    private int armorPrice;
    private double lifePoints;
    private double maxLifePoints;
    int category;

    public int getCategory() {
        return category;
    }

    public Hero(String name, double cashPrice, int armorPrice, double lifePoints, int category, double maxLifePoints) {

        this.name = name;
        this.armorPrice = armorPrice;
        this.cashPrice = cashPrice;
        this.lifePoints = lifePoints;
        this.category = category;
        this.maxLifePoints = maxLifePoints;
    }

    //getters
    public String getName() {
        return name;
    }
    public double getCashPrice() {
        return cashPrice;
    }
    public double getHealthPoints() {
        return lifePoints;
    }
    public int getArmorPrice() {
        return armorPrice;
    }
    public double getMaxHealthPoints() {
        return maxLifePoints;
    }

    //setters
    public void setHealthPoints(double lifePoints) {
        this.lifePoints = lifePoints;
    }
    public void setMaxHealthPoints(double maxLifePoints) {
        this.maxLifePoints = maxLifePoints;
    }
    public void setCategory(int category) {
        this.category = category;
    }
}

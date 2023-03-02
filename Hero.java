package ca.udem.ift1025.tp1.corrige.guildcommands;

public class Hero extends Guild{

    private String name;
    private double cashCost;
    private int armorCost;

    private double lifePoints;

    public Hero(String name, double cashCost,  int armorCost, double lifePoints ){
        super();
        this.name=name;
        this.armorCost=armorCost;
        this.cashCost=cashCost;
        this.lifePoints=lifePoints;
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

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;

    }
}

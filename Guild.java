package ca.udem.ift1025.tp1.corrige.guildcommands;

import ca.udem.ift1025.tp1.corrige.guildcommands.GuildCommand;
import ca.udem.ift1025.tp1.corrige.guildcommands.GuildCommandSystem;

public class Guild extends Main{

    private String name;
    private level category;
    private double armorCost;
    private int cashCost;
    private int lifePoints;

    public Hero(String name, level category, double armorCost, int cashCost, int lifePoints ){
        this.name=name;
        this.category=category;
        this.armorCost=armorCost;
        this.cashCost=cashCost;
        this.lifePoints=lifePoints;
    }
    public String getName() {
        return name;
    }

    public int getCashCost() {
        return cashCost;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public level getCategory() {
        return category;
    }

    public double getArmorCost() {
        return armorCost;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }
}

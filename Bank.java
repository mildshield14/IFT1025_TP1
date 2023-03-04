package ca.udem.ift1025.tp1.corrige.guildcommands;

import ca.udem.ift1025.tp1.corrige.guildcommands.GuildCommand;
import ca.udem.ift1025.tp1.corrige.guildcommands.GuildCommandSystem;

public class Bank extends Guild{

    protected int nbArm;
    protected double gold;


   public static void setterGold(double gold) {

        setGold((gold));
    }

    public static void setterArm(int nbArm) {

        setNbArm((nbArm));
    }


    public static void buyArms(int buyAm, double buyPr){
        if (getGold()>=buyPr*buyAm){
            setterGold(getGold()-(buyPr*buyAm));
            setterArm(getNbArm()+buyAm);
        }else{
            System.out.println("Guild is broke :(");
        }
    }


}

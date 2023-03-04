package ca.udem.ift1025.tp1.corrige.guildcommands;

import ca.udem.ift1025.tp1.corrige.guildcommands.GuildCommand;
import ca.udem.ift1025.tp1.corrige.guildcommands.GuildCommandSystem;

public class Bank extends Guild{

    protected int nbArm;
    protected double montant;


   public static void setterMont(double montant) {

        setMontant((montant));
    }

    public static void setterArm(int nbArm) {

        setNbArm((nbArm));
    }


    public static void buyArms(int buyAm, double buyPr){
        if (getMontant()>=buyPr*buyAm){
            setterMont(getMontant()-(buyPr*buyAm));
            setterArm(getNbArm()+buyAm);
        }else{
            System.out.println("Guild is broke :(");
        }
    }


}

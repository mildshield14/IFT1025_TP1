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
       if (buyAm<0) {
           System.out.println("That's not a valid number of armours...");
           if (buyPr < 0){
               System.out.println("");
               System.out.println("We are not paying you to buy armours!!");
           }
        } else if (buyPr < 0){
       System.out.println("We are not paying you to buy armours!!");
       }else if (getGold()>=buyPr*buyAm){
            setterGold(getGold()-(buyPr*buyAm));
            setterArm(getNbArm()+buyAm);
        }else{
            System.out.println("Guild is broke :(");
        }
    }


}

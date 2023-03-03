package ca.udem.ift1025.tp1.corrige.guildcommands;

import static ca.udem.ift1025.tp1.corrige.guildcommands.Main.herosList;
import static ca.udem.ift1025.tp1.corrige.guildcommands.Main.herosListRemove;

public class Quete {


public static void quest(boolean boo, Hero chosenhero,int lev, double lifeCost, Guild maGuilde, double goldReward, int armReward){
    //CALCULATIONS:

                        if (lev == chosenhero.getCategory() && ((chosenhero.getLifePoints() - lifeCost) >= 0)) {
        chosenhero.setLifePoints(chosenhero.getLifePoints() - lifeCost);

        maGuilde.setNbArm(maGuilde.getNbArm() + armReward);

        maGuilde.setMontant(maGuilde.getMontant() + goldReward);
    } else if ((chosenhero.getLifePoints() - lifeCost) >= 0) {
        // vie_enlevée - (niveau_actuelle - niveau_origine) * 1.5

        chosenhero.setLifePoints(chosenhero.getLifePoints() - (lifeCost - (chosenhero.getCategory() - lev) * 1.5));

        maGuilde.setNbArm(maGuilde.getNbArm() + armReward);

        maGuilde.setMontant(maGuilde.getMontant() + goldReward);

        if (((chosenhero.getLifePoints() - lifeCost) < 0)) {
            System.out.println("Quete echouee");

            herosListRemove.add(chosenhero.getName());
            herosList.remove(chosenhero);

            Main.setBoo1(true);


        }
    } else {

        System.out.println("Quete echouee");

    }

}  }

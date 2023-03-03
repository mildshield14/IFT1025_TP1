package ca.udem.ift1025.tp1.corrige.guildcommands;

import java.util.LinkedList;

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

}

    public static Hero searchingAlgo ( int level, LinkedList<Hero > herosList){
        int i = -1;
        boolean stop = true;
        Hero returns = null;
        while (stop == true) {

            i++;

            if (i == herosList.size() - 1) {
                stop = false;
            }

            //Case 1: Found one
            //Since linkedlist is FIFI, we take first one
            if (herosList.get(i).getCategory() == level) {

                stop = false;

                return herosList.get(i);


            }


        }

        //CAN MAKE BETTER USING ABS
        // TODO //again

        // Case 2:  NOT found exact; search for nearest greater first
        int nearestGr = herosList.get(0).getCategory();
        int diffGr = level - nearestGr;
        Hero selectedGr = null;
        i = -1;
        stop = true;
        while (stop == true) {

            i++;

            if (i == (herosList.size() - 1)) {
                stop = false;

            }


            if (level - herosList.get(i).getCategory() <= diffGr) {

                diffGr = level - herosList.get(i).getCategory();

                selectedGr = herosList.get(i);

            }


        }
        // Case 3:  NOT found exact; search for nearest less
        int nearestLe = herosList.get(0).getCategory();
        int diffLe = level - nearestGr;
        Hero selectedLe = null;
        i = -1;
        stop = true;
        while (stop == true) {

            i++;

            if (i == herosList.size() - 1) {
                stop = false;
            }


            if (level - herosList.get(i).getCategory() <= diffLe) {

                diffLe = level - herosList.get(i).getCategory();

                selectedLe = herosList.get(i);

            }

            if (diffGr < diffLe) {

                return selectedLe;
            } else {

                return selectedGr;
            }


        }


        return returns;
    }


}





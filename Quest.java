package ca.udem.ift1025.tp1.corrige.guildcommands;

import java.util.LinkedList;

import static ca.udem.ift1025.tp1.corrige.guildcommands.Main.herosList;
import static ca.udem.ift1025.tp1.corrige.guildcommands.Main.herosListRemove;
public class Quest {
    
     //procedure that will perform the quest of selected hero
     public static void quest(boolean boo, Hero chosenhero,int lev, double hpRequired, Guild myGuild, double goldReward, int armReward){
         // checks if enough HP
         if ((chosenhero.getHealthPoints() - hpRequired) >= 0){

             setDataGuild(chosenhero, myGuild, hpRequired, lev, armReward, goldReward);

         } else {
                System.out.println("Quete echouee");
                
                //storing removed name for output at the end
                herosListRemove.add(chosenhero.getName());
                herosList.remove(chosenhero);

                // no resources
                 Main.setEnoughResources(true);
                 
            }
     }

     public static void setDataGuild(Hero chosenhero, Guild myGuild, double hpRequired, int lev, int armReward, double goldReward){
            chosenhero.setHealthPoints(chosenhero.getHealthPoints() - (hpRequired - (chosenhero.getCategory() - lev) * 1.5));
    
            myGuild.setNbArm(myGuild.getNbArm() + armReward);
    
            myGuild.setGold(myGuild.getGold() + goldReward);
     }

     //Linear Search (better for Linked List since no sorting is required)
     public static Hero searchingAlgo(int level, LinkedList<Hero> herosList) {
         Hero selectedGr = herosList.get(0);
         Hero selectedLe = herosList.get(0);
         int diffGr = Math.abs(level - selectedGr.getCategory());
         int diffLe = Math.abs(level - selectedLe.getCategory());

         for (int i = 0; i < herosList.size(); i++) {
             if (herosList.get(i).getCategory() == level) {
                 return herosList.get(i);
             }

             int diff = Math.abs(level - herosList.get(i).getCategory());

             if (herosList.get(i).getCategory() > level && diff < diffGr) {
                 diffGr = diff;
                 selectedGr = herosList.get(i);
             } else if (herosList.get(i).getCategory() < level && diff < diffLe) {
                 diffLe = diff;
                 selectedLe = herosList.get(i);
             }
         }

         if (selectedLe == null || diffGr < diffLe) {
             return selectedGr;
         } else {
             return selectedLe;
         }
     }
     
}

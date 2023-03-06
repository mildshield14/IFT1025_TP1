// Vennila Sooben (20235256) and Marion Absalon (20211423)

// Main file to start the program and where every task is dealt with (switch case)

package ca.udem.ift1025.tp1.corrige.guildcommands;

import java.util.LinkedList;
import java.lang.Math;

public class Main {

    public static boolean enoughResources;
    
    //To store heroes of the Guild we use a linked list
    //We use another one to store name of Heroes that are eliminated so that we can output their names at the end
    public static  LinkedList<Hero> herosList = new LinkedList<>();
    public static LinkedList<String> herosListRemove = new LinkedList<>();
    
    //This boolean value is used in another class to check if resources are available so that appropriate 
    //actions can be taken
    public static void setEnoughResources(boolean enoughResources) {
        Main.enoughResources = enoughResources;
    }

    /**
     * Args: array with
     * <ol>
     *     <li>guild:&lt;montant initial&gt;,&lt;armures initiales&gt;</li>
     * </ol>
     *
     * @param : args
     */

    public static void main(String[] args) {

        GuildCommandSystem guildCommandSystem = new GuildCommandSystem(args);
        Guild myGuild = makeGuilde(guildCommandSystem.actualCommand());

        //To decode input data and create objects after checking for uniqueness
        boolean unique = false;

        while (guildCommandSystem.hasNextCommand()) {

            GuildCommand command = guildCommandSystem.nextCommand();

            //stores info on What to do?
            String what = command.getName();

            switch (what) {

                case "buy-hero": {

                    String name = command.nextString();
                    int level = command.nextInt();
                    double priceHero = command.nextDouble();
                    int nbArms = command.nextInt();
                    double healthPoints = command.nextDouble();
                    double maxHealthPoints = healthPoints;

                    //creation of objects based on categories using polymorphism;
                    Hero name1;

                    switch (level) {

                        case 0:
                            name1 = new Common(name, priceHero, nbArms, healthPoints, level, maxHealthPoints);
                            break;
                        case 1:
                            name1 = new Uncommon(name, priceHero, nbArms, healthPoints, level, maxHealthPoints);
                            break;
                        case 2:
                            name1 = new Rare(name, priceHero, nbArms, healthPoints, level, maxHealthPoints);
                            break;
                        case 3:
                            name1 = new Epic(name, priceHero, nbArms, healthPoints, level, maxHealthPoints);
                            break;
                        case 4:
                            name1 = new Legendary(name, priceHero, nbArms, healthPoints, level, maxHealthPoints);
                            break;

                        default:
                            throw new IllegalArgumentException("Invalid level");
                    }

                    //checking for uniqueness of hero in Guild
                    if (herosList.size() != 0 && myGuild.getGold() >= priceHero && myGuild.getNbArm() >= nbArms) {
                        checkUnique(name1, herosList, myGuild);

                    } else if (myGuild.getGold() >= priceHero && myGuild.getNbArm() >= nbArms) {
                        herosList.add(name1);

                        myGuild.setGold(myGuild.getGold() - priceHero);
                        myGuild.setNbArm(myGuild.getNbArm() - nbArms);

                    } else {
                        double newPrice = (myGuild.getGold() - priceHero);
                        int newArms = (myGuild.getNbArm() - nbArms);

                        if (newPrice < 0) {
                            System.out.println("Not enough money for " + name1.getName() + " :(" + " | you could be in debt of : " + String.format("%.1f", newPrice));
                        }
                        if (newArms < 0) {
                            System.out.println("Not enough armours for " + name1.getName() + " :(" + " | you could be in debt of : " + -1 * newArms + "");
                            System.out.println();
                        }
                    }

                }
                break;

                case "buy-armor": {

                    int arm = command.nextInt();
                    double gold = command.nextDouble();

                    Bank.buyArms(arm, gold);

                }
                break;

                case "do-quest": {

                    if (herosList.size() > 0) {

                        int level = command.nextInt();
                        double hpRequired = command.nextDouble();
                        int goldReward = command.nextInt();
                        int armReward = command.nextInt();
                        int i = herosList.get(0).getCategory();

                        Hero chosenhero = Quest.searchingAlgo(level, herosList);

                        Quest.quest(unique, chosenhero, level, hpRequired, myGuild, goldReward, armReward);

                    }
                }
                break;

                case "train-hero": {

                    String name = command.nextString();
                    Hero heroBeingTrained = null;
                    int heroCategory = 0;

                    //We check if the hero is in the Guild
                    for (int i = 0; i < herosList.size(); i++) {
                        if (herosList.get(i).getName().equals(name)) {
                            heroBeingTrained = herosList.get(i);
                            heroCategory = heroBeingTrained.getCategory();
                        }
                    }

                    if (heroBeingTrained == null) {
                        herosListRemove.add(name);
                    }
                    //If the hero exists in the guild we can check if he isn't already at the max level (4)
                    if (heroBeingTrained != null) {

                        if (heroCategory >= 0 && heroCategory < 4) {

                            //Then we have to see if the guild has enough armours and gold to train the hero

                            double trainingPriceInGold = 20 * Math.log(heroCategory + 10);

                            double armours = Math.log(heroCategory + 10);
                            double ceilArmours = Math.ceil(armours);
                            int trainingPriceInArmors = (int) ceilArmours;

                            int armourInventory = myGuild.getNbArm();
                            double goldInventory = myGuild.getGold();

                            if (goldInventory - trainingPriceInGold > 0 && armourInventory - trainingPriceInArmors > 0) {

                                //Gold and Armour quantity after paying for the training
                                myGuild.setGold(myGuild.getGold() - trainingPriceInGold);
                                myGuild.setNbArm(myGuild.getNbArm() - trainingPriceInArmors);

                                //Setting the new level, maximum health points and health points after training
                                heroBeingTrained.setMaxHealthPoints(heroBeingTrained.getMaxHealthPoints() * 1.5);
                                heroBeingTrained.setHealthPoints(heroBeingTrained.getHealthPoints() * 1.5);
                                heroBeingTrained.setCategory(heroCategory + 1);

                            } else if (trainingPriceInGold > goldInventory || trainingPriceInArmors > armourInventory) {
                                enoughResources = true;
                            }

                        } else if (heroCategory == 4) {
                            System.out.println(heroBeingTrained.getName() + " already reached the highest category.");
                        } else if (heroCategory < 0 || heroCategory > 4) {
                            System.out.println("Invalid category.");
                        }
                    }
                }
                break;
            }
        }

        //Output messages
        System.out.println("Output: ");
        System.out.println("");
        String am = String.format("%.1f", myGuild.getGold());
        System.out.println("Guild Bank account: " + am + " gold & " + myGuild.getNbArm() + "" + " armours");

        if (herosList.size() > 0) {
            System.out.println("Heroes: ");
        }

        for (int i = 0; i < herosList.size(); i++) {
            System.out.println("-"+herosList.get(i).getName() + ": level:" + herosList.get(i).getCategory() + " HP=" + String.format("%.1f", herosList.get(i).getHealthPoints()));
        }

        if (enoughResources == true){
            System.out.println("Error:");
            System.out.println("-"+"The Guild doesn't have enough resources to proceed.");
        }


        for (int i=0;i< herosListRemove.size();i++){
            System.out.println("-"+herosListRemove.get(i)+ " is not on the list");
        }
    }

    //procedure to check uniqueness
    public static void checkUnique (Hero objHero, LinkedList < Hero > herosList, Guild myGuild){

        String n = objHero.getName();
        int i = -1;
        boolean unique = true;
        boolean stop = true;

        while (stop == true) {

            i++;
            Hero n2 = herosList.get(i);
            String name2 = n2.getName();

            if (name2.equals(n)) {
                stop = false;
                unique = false;
            }

            if (i == herosList.size() - 1) {
                stop = false;
            }

        }


        if (unique) {
            herosList.add(objHero);

            myGuild.setGold(myGuild.getGold() - objHero.getCashPrice());
            myGuild.setNbArm(myGuild.getNbArm() - objHero.getArmorPrice());

        } else {
            System.out.println("Error; input another one as " + " " + n + " already exists");
        }


    }



    //Creation of Guild
    public static Guild makeGuilde(GuildCommand command) {
        double goldInitial = command.nextDouble();
        int nbArmures = command.nextInt();
        Guild myGuild = new Guild(goldInitial, nbArmures);
        myGuild.setGold(goldInitial);
        return myGuild;
    }

}

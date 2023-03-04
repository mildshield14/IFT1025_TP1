package ca.udem.ift1025.tp1.corrige.guildcommands;

import java.util.LinkedList;

public class Main {

    public static boolean enoughResources;
    //To store heroes of the Guild we use a linked list
    public static  LinkedList<Hero> herosList = new LinkedList<>();
    public static LinkedList<String> herosListRemove = new LinkedList<>();

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

        //To decode input info and create objects after checking for uniqueness
        boolean unique = false;

        while (guildCommandSystem.hasNextCommand()) {

            GuildCommand command = guildCommandSystem.nextCommand();

            //variable what-stores info on What to do?
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
                            throw new IllegalArgumentException("Invalid levelel");
                    }

                    //checking for uniqueness
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
                            System.out.println("Not enough arms for " + name1.getName() + " :(" + " | you could be in debt of : " + -1 * newArms + "");
                        }
                    }

                } break;

                case "buy-armor": {

                    int arm = command.nextInt();
                    double gold = command.nextDouble();

                    Bank.buyArms(arm, gold);

                } break;

                case "do-quest": {
                    if (herosList.size() > 0) {

                        int level = command.nextInt();
                        double hpRequired = command.nextDouble();
                        int goldReward = command.nextInt();
                        int armReward = command.nextInt();
                        int i = herosList.get(0).getCategory();

                        Hero chosenhero = Quest.searchingAlgo(level, herosList);

                        Quest.quest(unique,chosenhero,level, hpRequired, myGuild, goldReward,armReward);

                }
            } break;

                case "train-hero": {
                    String name = command.nextString();
                    Hero heroBeingTrained = null;
                    for (int i = 0; i < herosList.size(); i++) {
                        if (herosList.get(i).getName().equals(name)) {
                            heroBeingTrained = herosList.get(i);
                        }
                    }

                    if(heroBeingTrained == null){
                           herosListRemove.add(name);
                    }

                    if (heroBeingTrained != null) {

                        if (heroBeingTrained.getCategory() >= 0 && heroBeingTrained.getCategory() < 4) {

                            double trainingPriceInGold = 20 * Math.log(heroBeingTrained.getCategory() + 10);//<- prix
                            double c=Math.log(heroBeingTrained.getCategory() + 10);
                            double d = Math.ceil(c);
                            int armoursPrice=(int)d;
                            double trainingPriceInArmors = Math.ceil(trainingPriceInGold);//<- prix armures
                            int armourInventory = myGuild.getNbArm();
                            double goldInventory = myGuild.getGold();

                            if (myGuild.getGold()-trainingPriceInArmors>0 && myGuild.getNbArm()-armoursPrice>0) {

                                myGuild.setGold(myGuild.getGold()-trainingPriceInArmors);
                                myGuild.setNbArm(myGuild.getNbArm()-armoursPrice);

                                heroBeingTrained.setMaxHealthPoints(heroBeingTrained.getMaxHealthPoints() * 1.5);
                                heroBeingTrained.setHealthPoints(heroBeingTrained.getHealthPoints() * 1.5);
                                heroBeingTrained.setCategory(heroBeingTrained.getCategory() + 1);

                            } else if (trainingPriceInGold > goldInventory || trainingPriceInArmors > armourInventory) {
                                enoughResources=true;
                            }

                        }


                    }
                }
                break;
            }


        }

        //Output message
        System.out.println("");
        String am = String.format("%.1f", myGuild.getGold());
        System.out.println("Guild Bank account: " + am + " gold & " + myGuild.getNbArm() + "" + " armours");

        if (herosList.size() > 0) {
            System.out.println("Heroes: ");
        }

        for (int i = 0; i < herosList.size(); i++) {
            System.out.println("-"+herosList.get(i).getName() + ": levelel:" + herosList.get(i).getCategory() + " HP=" + String.format("%.1f", herosList.get(i).getHealthPoints()));
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

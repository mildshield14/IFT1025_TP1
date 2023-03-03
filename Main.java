package ca.udem.ift1025.tp1.corrige.guildcommands;

import java.util.LinkedList;

public class Main {

    public static boolean boo1;
    //To store heroes of the Guild we use a linked list
   public static  LinkedList<Hero> herosList = new LinkedList<>();
    public static LinkedList<String> herosListRemove = new LinkedList<>();

    public static void setBoo1(boolean boo1) {
        Main.boo1 = boo1;
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

        Guild maGuilde = makeGuilde(guildCommandSystem.actualCommand());



        //To decode input info and create objects after checking for uniqueness
        boolean boo = false;

        while (guildCommandSystem.hasNextCommand()) {

            GuildCommand command = guildCommandSystem.nextCommand();

            //variable what-stores info on What to do?
            String what = command.getName();

            switch (what) {
                case "buy-hero": {

                    String name = command.nextString();

                    int lev = command.nextInt();

                    double costHero = command.nextDouble();

                    int nbArms = command.nextInt();

                    double lifePoints = command.nextDouble();

                    double maxLifePoints = lifePoints;


                    //creation of objects based on categories using polymorphism;
                    Hero name1;

                    switch (lev) {

                        case 0:
                            name1 = new Common(name, costHero, nbArms, lifePoints, lev, maxLifePoints);

                            break;
                        case 1:
                            name1 = new Uncommon(name, costHero, nbArms, lifePoints, lev, maxLifePoints);

                            break;
                        case 2:
                            name1 = new Rare(name, costHero, nbArms, lifePoints, lev, maxLifePoints);
                            break;
                        case 3:
                            name1 = new Epic(name, costHero, nbArms, lifePoints, lev, maxLifePoints);
                            break;
                        case 4:
                            name1 = new Legendary(name, costHero, nbArms, lifePoints, lev, maxLifePoints);
                            break;

                        default:
                            throw new IllegalArgumentException("Invalid level");

                    }

                    //checking for uniqueness
                    if (herosList.size() != 0 && maGuilde.getMontant() >= costHero && maGuilde.getNbArm() >= nbArms) {
                        checkUnique(name1, herosList, maGuilde);
                    } else if (maGuilde.getMontant() >= costHero && maGuilde.getNbArm() >= nbArms) {
                        herosList.add(name1);

                        maGuilde.setMontant(maGuilde.getMontant() - costHero);
                        maGuilde.setNbArm(maGuilde.getNbArm() - nbArms);

                    } else {
                        double newCost = (maGuilde.getMontant() - costHero);
                        int newArms = (maGuilde.getNbArm() - nbArms);

                        if (newCost < 0) {
                            System.out.println("Not enough money for " + name1.getName() + " :(" + " | you could be in debt of : " + String.format("%.1f", newCost));
                        }
                        if (newArms < 0) {

                            System.out.println("Not enough arms for " + name1.getName() + " :(" + " | you could be in debt of : " + -1 * newArms + "");
                        }
                    }

                    break;
                }

                case "buy-armor": {

                    int arm = command.nextInt();
                    double gold = command.nextDouble();

                    Bank.buyArms(arm, gold);

                }
                break;

                case "do-quest": {
                    if (herosList.size() > 0) {

                        int lev = command.nextInt();
                        double lifeCost = command.nextDouble();
                        int goldReward = command.nextInt();
                        int armReward = command.nextInt();
                        int i = herosList.get(0).getCategory();


                        Hero chosenhero = Quete.searchingAlgo(lev, herosList);

                        Quete.quest(boo,chosenhero,lev, lifeCost, maGuilde, goldReward,armReward);

                }
                }
                break;

                case "train-hero": {
                    String name = command.nextString();
                    Hero obje = null;
                    for (int i = 0; i < herosList.size(); i++) {
                        if (herosList.get(i).getName().equals(name)) {
                            obje = herosList.get(i);
                        }
                    }


                    if(obje==null){

                           herosListRemove.add(name);

                    }

                    if (obje != null) {

                        if (obje.getCategory() >= 0 &&
                                obje.getCategory() < 4) {
                            double trainingCostInGold = 20 * Math.log(obje.getCategory() + 10);//<- prix
                            double c=Math.log(obje.getCategory() + 10);
                            double d = Math.ceil(c);
                            int ArmorsCost=(int)d;
                            double trainingCostInArmors = Math.ceil(trainingCostInGold);//<- prix armures
                            int ArmorInventory = maGuilde.getNbArm();
                            double GoldInventory = maGuilde.getMontant();

                            if (maGuilde.getMontant()-trainingCostInArmors>0 && maGuilde.getNbArm()-ArmorsCost>0) {
                                maGuilde.setMontant(maGuilde.getMontant()-trainingCostInArmors);
                                maGuilde.setNbArm(maGuilde.getNbArm()-ArmorsCost);
                                obje.setMaxLifePoints(
                                        obje.getMaxLifePoints() * 1.5);
                                obje.setLifePoints(
                                        obje.getLifePoints() * 1.5);
                                obje.setCategory(obje.getCategory() + 1);
                            } else if (trainingCostInGold > GoldInventory || trainingCostInArmors > ArmorInventory) {
                                boo1=true;
                            }

                        }


                    }
                }
                break;
            }


        }
        System.out.println("");
        String am = String.format("%.1f", maGuilde.getMontant());
        System.out.println("Guild Bank account: " + am + " gold & " + maGuilde.getNbArm() + "" + " armours");

        if (herosList.size() > 0) {
            System.out.println("Heroes: ");
        }

        for (int i = 0; i < herosList.size(); i++) {
            System.out.println(herosList.get(i).getName() + ": level:" + herosList.get(i).getCategory() + " HP=" + String.format("%.1f", herosList.get(i).getLifePoints()));

        }

        if( boo1==true){
            System.out.println("The Guild doesn't have enough resources to proceed.");
        }


for (int i=0;i< herosListRemove.size();i++){

    System.out.println(herosListRemove.get(i)+ " is not on the list");}
}





        //procedure to check uniqueness
        public static void checkUnique (Hero objHero, LinkedList < Hero > herosList, Guild maGuilde){

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
                ;

                if (i == herosList.size() - 1) {
                    stop = false;
                }

            }


            if (unique) {
                herosList.add(objHero);

                maGuilde.setMontant(maGuilde.getMontant() - objHero.getCashCost());
                maGuilde.setNbArm(maGuilde.getNbArm() - objHero.getArmorCost());

            } else {
                System.out.println("Error; input another one as " + " " + n + " already exists");
            }


        }



    //Creation of Guild
    public static Guild makeGuilde(GuildCommand command) {
        double montantInitial = command.nextDouble();
        int nbArmures = command.nextInt();
        Guild maGuilde = new Guild(montantInitial, nbArmures);
        maGuilde.setMontant(montantInitial);
        return maGuilde;
    }
}


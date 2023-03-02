package ca.udem.ift1025.tp1.corrige.guildcommands;

import ca.udem.ift1025.tp1.corrige.guildcommands.GuildCommand;
import ca.udem.ift1025.tp1.corrige.guildcommands.GuildCommandSystem;
import java.util.LinkedList;

public class Main {

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

        //To store heroes of the Guild we use a linked list
        LinkedList<Hero> herosList = new LinkedList<>();

        //To decode input info and create objects after checking for uniqueness
        while (guildCommandSystem.hasNextCommand()) {

            GuildCommand command = guildCommandSystem.nextCommand();

            //variable what- stores info on What to do?
            String what = command.getName();

            switch (what) {
                case "buy-hero": {

                    String name = command.nextString();

                    int lev = command.nextInt();

                    double costHero = command.nextDouble();

                    int nbArms = command.nextInt();

                    double lifePoints = command.nextDouble();



                    //creation of objects based on caetgories using polymorphism;
                    Hero name1;

                    switch (lev) {

                        case 0:
                            name1 = new Common(name, costHero, nbArms, lifePoints,lev);

                            break;
                        case 1:
                            name1 = new Uncommon(name, costHero, nbArms, lifePoints,lev);

                            break;
                        case 2:
                            name1 = new Rare(name, costHero, nbArms, lifePoints,lev);
                            break;
                        case 3:
                            name1 = new Epic(name, costHero, nbArms, lifePoints,lev);
                            break;
                        case 4:
                            name1 = new Legendary(name, costHero, nbArms, lifePoints,lev);
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
                            System.out.println("Not enough money for " + name1.getName() + " :(" + " | you could be in debt of : " + Double.toString(newCost));
                        }
                        if (newArms < 0) {

                            System.out.println("Not enough arms for " + name1.getName() + " :(" + " | you could be in debt of : " + -1 * newArms + "");
                        }
                    }
break;
                }

                case "buy-armor": {
                    break;
                }
                case "do-quest": {


                    int lev = command.nextInt();
                    double lifeCost = command.nextDouble();
                    int goldReward = command.nextInt();
                    int armReward = command.nextInt();
                    int i=herosList.get(0).getCategory();
                    Hero chosenhero = searchingAlgo(lev, herosList);

                    System.out.println(chosenhero.getName());

                    //CALCULATIONS:

                    if (lev== chosenhero.getCategory() && ((chosenhero.getLifePoints() - lifeCost)>=0) && ((maGuilde.getNbArm() + armReward)>=0) &&((maGuilde.getMontant() + goldReward)>0)) {
                        chosenhero.setLifePoints(chosenhero.getLifePoints() - lifeCost);

                        maGuilde.setNbArm(maGuilde.getNbArm() + armReward);

                        maGuilde.setMontant(maGuilde.getMontant() + goldReward);
                    }
                    else if (((chosenhero.getLifePoints() - lifeCost)>=0) && ((maGuilde.getNbArm() + armReward)>=0) && ((maGuilde.getMontant() + goldReward)>0)) {
                        // vie_enlevée - (niveau_actuelle - niveau_origine) * 1.5

                        chosenhero.setLifePoints(chosenhero.getLifePoints() - (lifeCost-(chosenhero.getCategory()-lev)*1.5));

                        maGuilde.setNbArm(maGuilde.getNbArm() - armReward);

                        maGuilde.setMontant(maGuilde.getMontant() - goldReward);

                        if (((chosenhero.getLifePoints() - lifeCost)<0) | ((maGuilde.getMontant() + goldReward)<0) | ((maGuilde.getNbArm() + armReward)>=0)){
                            System.out.println("Quete echouee");
                        }
                    }else
                    {

                        System.out.println("Quete echouee");

                    }


                }
                case "train-hero": {
                    // TODO


                }
            }
        }
      //  Guild Bank account: 107.5 gold & 7 armours
      //  Heroes:
      // -Berserker: level=2, HP=25.2
        System.out.println("Guild Bank account: " + maGuilde.getMontant() + "" + " gold & "+ maGuilde.getNbArm()+"" + " armours");

        System.out.println("Heroes: ");

        for (int i=0;i<herosList.size();i++){
            System.out.println(herosList.get(i).getName()+": level:" +herosList.get(i).getCategory() + " HP=" + herosList.get(i).getLifePoints());
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

    //procedure to check uniqueness
    public static void checkUnique(Hero objHero, LinkedList<Hero> herosList, Guild maGuilde) {

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

    public static Hero searchingAlgo(int level, LinkedList<Hero> herosList) {
        int i = -1;
        boolean stop = true;
        Hero returns = null;
        while (stop == true) {

            i++;

            if (i == herosList.size()-1) {
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

            if (i == (herosList.size()-1)) {
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

            if (i == herosList.size()-1) {
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



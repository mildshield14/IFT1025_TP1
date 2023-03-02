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
            String what=command.getName();

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
                            name1 = new Common(name, costHero, nbArms, lifePoints);
                            break;
                        case 1:
                            name1 = new Uncommon(name, costHero, nbArms, lifePoints);
                            break;
                        case 2:
                            name1 = new Rare(name, costHero, nbArms, lifePoints);
                            break;
                        case 3:
                            name1 = new Epic(name, costHero, nbArms, lifePoints);
                            break;
                        case 4:
                            name1 = new Legendary(name, costHero, nbArms, lifePoints);
                            break;

                        default:
                            throw new IllegalArgumentException("Invalid level");

                    }

                    //checking for uniqueness
                    if (herosList.size()!=0 && maGuilde.getMontant()>=costHero && maGuilde.getNbArm()>=nbArms) {
                        checkUnique(name1, herosList, maGuilde);
                    } else if (maGuilde.getMontant()>=costHero && maGuilde.getNbArm()>=nbArms) {
                        herosList.add(name1);
                        maGuilde.setMontant(maGuilde.getMontant()-costHero);
                        maGuilde.setNbArm(maGuilde.getNbArm()-nbArms);
                    }else{
                        double newCost = (maGuilde.getMontant()-costHero);
                        int newArms= (maGuilde.getNbArm()-nbArms);

                        if (newCost <0) {
                            System.out.println("Not enough money for " + name1.getName() + " :(" + " | you could be in debt of : " + Double.toString(newCost));
                        }
                        if (newArms<0) {

                            System.out.println("Not enough arms for " + name1.getName() + " :(" + " | you could be in debt of : " + -1*newArms + "");
                        }
                    }

                }

                case "buy-armor" :{
                    // TODO
                }
                case "do-quest" : {
                    // TODO
                }
                case "train-hero" : {
                    // TODO


                }
            }
        }
    }

//Creation of Guild
    public static Guild makeGuilde(GuildCommand command) {
        double montantInitial = command.nextDouble();
        int nbArmures = command.nextInt();
        Guild maGuilde=new Guild(montantInitial, nbArmures);
        maGuilde.setMontant(montantInitial);
        return maGuilde;
    }

    //procedure to check uniqueness
    public static void checkUnique(Hero objHero,LinkedList<Hero> herosList, Guild maGuilde){

        String n=objHero.getName();
        int i=-1;
        boolean unique=true;
        boolean stop=true;

        while (stop==true){
            i++;
            Hero n2= herosList.get(i);
            String name2=n2.getName();
            if (name2.equals(n)){
                stop=false;
                unique=false;
            };

            if (i==herosList.size()-1){
                stop=false;
            }

        }


            if (unique){
                herosList.add(objHero);
              
                maGuilde.setMontant(maGuilde.getMontant()- objHero.getCashCost());
                maGuilde.setNbArm(maGuilde.getNbArm()-objHero.getArmorCost());

            }else{
                System.out.println("Error; input another one as " + " " + n + " already exists");
            }


    }
}

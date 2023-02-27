package ca.udem.ift1025.tp1.corrige.guildcommands;

import ca.udem.ift1025.tp1.corrige.guildcommands.GuildCommand;
import ca.udem.ift1025.tp1.corrige.guildcommands.GuildCommandSystem;


public class Main {
    /**
     * Args: array with
     * <ol>
     *     <li>guild:&lt;montant initial&gt;,&lt;armures initiales&gt;</li>
     * </ol>
     *
     * @param args
     */

    public Guild heroList[]; //liste pour heros (a refaire mais l'idee est la)


    //use of an enumerated data type for maintability and avoid use of try-catch
    public enum level{

        0,1,2,3,4

        //0 - common
        //1 - uncommon
        //2 - rare
        //3- epic
        //4- legendary

    }
    public static void main(String[] args) {
        GuildCommandSystem guildCommandSystem = new GuildCommandSystem(args);

        Guilde maGuilde = makeGuilde(guildCommandSystem.actualCommand());

        while (guildCommandSystem.hasNextCommand()) {
            GuildCommand command = guildCommandSystem.nextCommand();
            switch (command.getName()) {
                case "buy-hero" -> {
                    // TODO
                }
                case "buy-armor" ->{
                    // TODO
                }
                case "do-quest" -> {
                    // TODO
                }
                case "train-hero" -> {
                    // TODO


                }
            }
        }
    }


    public static Guilde makeGuilde(GuildCommand command) {
        double montantInitial = command.nextDouble();
        int nbArmures = command.nextInt();
        return new Guilde(montantInitial, nbArmures);
    }
}
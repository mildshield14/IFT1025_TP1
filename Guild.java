package ca.udem.ift1025.tp1.corrige.guildcommands;

public class Guild {


    private static double gold;
    private static int nbArm;

    public Guild(double gold, int nbArm){
        this.gold=gold;
        this.nbArm=nbArm;
    }

    public Guild() {

    }

    public static int getNbArm() {
        return nbArm;
    }

    public static double getGold() {
        return gold;
    }

    public static void setNbArm(int nbArm) {
        Guild.nbArm = nbArm;
    }

    public static void setGold(double gold) {
        Guild.gold = gold;
    }

}

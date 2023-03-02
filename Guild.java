package ca.udem.ift1025.tp1.corrige.guildcommands;

public class Guild {

    public static double setMontant;
    private static double montant;
    private static int nbArm;


    public Guild(double montant, int nbArm){
        this.montant=montant;
        this.nbArm=nbArm;
    }

    public Guild() {

    }

    public static int getNbArm() {
        return nbArm;
    }

    public static double getMontant() {
        return montant;
    }

    public void setNbArm(int nbArm) {
        this.nbArm = nbArm;
    }

    public void setMontant(double montant) {

        this.montant = montant;
    }


}

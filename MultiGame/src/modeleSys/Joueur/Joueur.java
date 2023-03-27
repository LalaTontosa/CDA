package modeleSys.Joueur;

public abstract class Joueur {
    private int partieGagnee=0;

    private final String nom;

    public Joueur(String nom){
        this.nom = nom;
    }
    public String getName(){
        return nom;
    }
    public int getPartieGagnee(){
        return partieGagnee;
    }

    public void incrementePG(){
        partieGagnee++;
    }
}

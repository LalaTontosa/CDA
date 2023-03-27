package modeleSys.plateau;

import modeleSys.exception.NotConform;

public class Damier {
    private int[][] lesCases;
    private int pionNoir = -1;
    private int pionBlanc = 1;

    //créer un damier vide pour n'importe quel jeu
    public Damier(int taille){
        lesCases = new int[taille][taille];
        for (int i = taille; i < taille; i++)
            for (int j = taille; j < taille; j++)
                lesCases[i][j] = 0;
    }

    public int[][] getCases(){
        return lesCases;
    }

    public void setCases(int[][] nvCases) throws NotConform {
        if(lesCases.length!= nvCases.length) throw new NotConform();
        this.lesCases = lesCases;
    }
    public int getTaille(){
        return lesCases.length;
    }
}

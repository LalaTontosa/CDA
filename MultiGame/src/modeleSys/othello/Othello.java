package modeleSys.othello;

import modeleSys.interfaces.Strategie;
import modeleSys.plateau.Damier;
import modeleSys.interfaces.JeuPlateau;
import modeleSys.exception.InvalidMove;
import modeleSys.exception.NotConform;
import modeleSys.strategie.Minimax;
import modeleSys.strategie.OthelloNaive;

import java.util.ArrayList;
import java.util.List;

public class Othello implements JeuPlateau {
    private static final List<Strategie> lesStrategies = new ArrayList<>();

    //ajouter les strategies possibles
    static{lesStrategies.add(new OthelloNaive());lesStrategies.add(new Minimax());}

    public static Strategie getUnStrategie(int n){
        return lesStrategies.get(n);
    }

    private final Damier damier;
    private int nbPionsNoir = 2;

    private int nbPionsBlanc = 2;

    private int joueurCourant = 1;

    public Othello(){
        this.damier = new Damier(4);
    }
    @Override
    public void jouer() {
        new FacadeOthello(this).jouer();
    }

    // initialise le damier pour pouvoir jouer à othello
    public void initialiserDamier(){
        int t = damier.getTaille();
        int[][] lesCases = damier.getCases();
        lesCases[(t/2)-1][(t/2)-1]=1;
        lesCases[(t/2)-1][(t/2)]=-1;
        lesCases[(t/2)][(t/2)-1]=-1;
        lesCases[(t/2)][(t/2)]=1;
    }

    public void changerJoueurCourant() {
        joueurCourant = (joueurCourant == -1) ? 1 : -1;
    }

    //mettre un pion sur le damier
    public void mettreUnPion(int x, int y) throws InvalidMove, NotConform {
        rentreDansDamier(x,y);
        caseVide(x,y);
        regarderAutour(x,y);
    }

    private void regarderAutour(int x,int y) throws InvalidMove, NotConform {
        boolean pionsPose = false;
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0)
                    continue;
                if (encadrementValide(x, y, i, j)) {
                    if (!pionsPose) {
                        int a = (joueurCourant == -1) ? nbPionsNoir++ : nbPionsBlanc++;
                        pionsPose = true;
                    }
                    damier.setCases(retournerPions(x, y, i, j));
                }
            }
        if (!pionsPose) throw new InvalidMove();
    }

    public void caseVide(int x,int y) throws InvalidMove {
        int[][] lesCases = damier.getCases();
        if(lesCases[x][y]!=0) throw new InvalidMove();
    }
    private void rentreDansDamier(int x, int y) throws InvalidMove {
        if (x < 0 || x > damier.getTaille()-1 || y < 0 || y > damier.getTaille()-1)
            throw new InvalidMove();
    }
    private boolean encadrementValide(int x, int y, int a, int b) {
        int l = a + x;
        int c = b + y;
        int nbPionsAdverse = 0;
        int taille = damier.getTaille();
        int[][] lesCases = damier.getCases();

        while (l >= 0 && l <taille && c >= 0 && c < taille) {
            if (lesCases[l][c] == 0)
                return false;
            if (lesCases[l][c] == joueurCourant)
                return nbPionsAdverse > 0;
            nbPionsAdverse++;
            l += a;
            c += b;
        }
        return false;
    }

    private int[][] retournerPions(int x, int y, int a, int b) {
        int l = a + x;
        int c = b + y;
        int[][] lesCases = damier.getCases();
        if(lesCases[x][y]==0)lesCases[x][y]=joueurCourant;

        while (lesCases[l][c] != joueurCourant) {
            lesCases[l][c] = joueurCourant;

            if (joueurCourant == 1) {
                nbPionsNoir--;
                nbPionsBlanc++;
            } else {
                nbPionsNoir++;
                nbPionsBlanc--;
            }
            l += a;
            c += b;
        }
        return lesCases;
    }

    public List<String> coupPossible() {
        List<String> coupP=new ArrayList<>();
        int taille = damier.getTaille();
        int[][] lesCases = damier.getCases();

        for (int i = 0; i <taille; i++)
            for (int j = 0; j <taille; j++) {
                if (lesCases[i][j] != 0)
                    continue;
                for (int k = -1; k <= 1; k++)
                    for (int l = -1; l <= 1; l++) {
                        if (k == 0 && l == 0)
                            continue;
                        if (encadrementValide(i, j, k, l)){
                            String cp = i+1 + " " + String.valueOf(Character.toChars(j+65));
                            if(!coupP.contains(cp)) coupP.add(cp);
                        }
                    }
            }
        return coupP;
    }
    public int vainqueur(){
        return nbPionsNoir - nbPionsBlanc;
    }
    public int getJCourant(){
        return joueurCourant;
    }

    public Damier getDamier() {
        return damier;
    }

    public int getNbPionsNoir() {
        return nbPionsNoir;
    }

    public int getNbPionsBlanc() {
        return nbPionsBlanc;
    }

    @Override
    public String getNameGame() {
        return "Othello";
    }


    //liste des strategies : pour l'affichage

    //utile???
    public static List<String> getStrats(){
        List<String> listeStrat = new ArrayList<>();
        for(Strategie s : lesStrategies)
            listeStrat.add(s.getName());
        return listeStrat;
    }

}
